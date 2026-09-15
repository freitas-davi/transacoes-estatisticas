package desafio.backend.itau.service;

import desafio.backend.itau.domain.Transacao;
import desafio.backend.itau.dto.TransacaoRequestDTO;
import desafio.backend.itau.exception.InvalidTransactionException;
import desafio.backend.itau.repository.interfaces.TransacaoRepository;
import desafio.backend.itau.mapper.TransacaoMapper;
import desafio.backend.itau.service.interfaces.TransacaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Slf4j
@Service
public class TransacaoServiceImpl implements TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final TransacaoMapper transacaoMapper;

    public TransacaoServiceImpl(TransacaoRepository transacaoRepository,
                                TransacaoMapper transacaoMapper) {
        this.transacaoRepository = transacaoRepository;
        this.transacaoMapper = transacaoMapper;
    }

    @Override
    public void salvar(TransacaoRequestDTO dto) {
        validaValor(dto.valor());
        validaData(dto.dataHora());

        log.info("Recebendo transação: valor={}, dataHora={}", dto.valor(), dto.dataHora());

        Transacao transacao = transacaoMapper.toEntity(dto);
        transacaoRepository.salvar(transacao);
    }

    @Override
    public void limparTransacoes() {
        transacaoRepository.limpar();
        log.info("Todas as transações foram removidas.");
    }

    // HELPERS
    private void validaValor(double valor) throws InvalidTransactionException {
        if (valor < 0) {
            log.atError().log(
                    "Transacao rejeitada - valor invalido: {}",
                    valor);
            throw new InvalidTransactionException();
        }
    }

    private void validaData(OffsetDateTime dataHora) {
        if (dataHora.isAfter(OffsetDateTime.now())) {
            log.atError().log(
                    "Transacao rejeitada - data hora inválida: {}",
                    dataHora
            );
            throw new InvalidTransactionException();
        }
    }

}
