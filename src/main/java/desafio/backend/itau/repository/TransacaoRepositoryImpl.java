package desafio.backend.itau.repository;

import desafio.backend.itau.domain.Transacao;
import desafio.backend.itau.repository.interfaces.TransacaoRepository;
import org.springframework.stereotype.Repository;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Repository
public class TransacaoRepositoryImpl implements TransacaoRepository {

    private final Queue<Transacao> transacoes = new ConcurrentLinkedQueue<>();

    @Override
    public void salvar(Transacao transacao) {
        transacoes.add(transacao);
    }

    @Override
    public void limpar() {
        transacoes.clear();
    }

    @Override
    public Queue<Transacao> findAll() {
        return transacoes;
    }

}
