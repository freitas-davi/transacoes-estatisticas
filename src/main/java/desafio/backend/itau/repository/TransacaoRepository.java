package desafio.backend.itau.repository;

import desafio.backend.itau.domain.Transacao;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TransacaoRepository {

    private final ConcurrentHashMap<Double, OffsetDateTime> transacoes = new ConcurrentHashMap<>();

    public void salvar(Transacao transacao) {
        transacoes.put(
                transacao.getValor(),
                transacao.getDataHora()
        );
    }

}
