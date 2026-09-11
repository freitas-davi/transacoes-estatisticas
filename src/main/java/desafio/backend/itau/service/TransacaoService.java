package desafio.backend.itau.service;

import desafio.backend.itau.domain.Transacao;
import desafio.backend.itau.dto.TransacaoRequestDTO;
import desafio.backend.itau.exception.InvalidTransactionException;
import desafio.backend.itau.mapper.TransacaoMapper;
import desafio.backend.itau.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final TransacaoMapper transacaoMapper;

    public TransacaoService(TransacaoRepository transacaoRepository,
                            TransacaoMapper transacaoMapper) {
        this.transacaoRepository = transacaoRepository;
        this.transacaoMapper = transacaoMapper;
    }

    public void salvar(TransacaoRequestDTO dto) {
        validaValor(dto.valor());
        validaData(dto.dataHora());

        Transacao transacao = transacaoMapper.toEntity(dto);
        transacaoRepository.salvar(transacao);
    }

    // Helpers

    private void validaValor(double valor) {
        if (valor < 0) {
            throw new InvalidTransactionException();
        }
    }

    private void validaData(OffsetDateTime dataHora) {
        if (dataHora.isAfter(OffsetDateTime.now())) {
            throw new InvalidTransactionException();
        }
    }

}
