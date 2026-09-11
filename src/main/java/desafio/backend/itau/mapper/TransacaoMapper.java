package desafio.backend.itau.mapper;

import desafio.backend.itau.domain.Transacao;
import desafio.backend.itau.dto.TransacaoRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class TransacaoMapper {

    public Transacao toEntity(TransacaoRequestDTO dto) {
        Transacao transacao = new Transacao();
        transacao.setValor(dto.valor());
        transacao.setDataHora(dto.dataHora());
        return transacao;
    }

}
