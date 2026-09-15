package desafio.backend.itau.repository.interfaces;

import desafio.backend.itau.domain.Transacao;

import java.util.Queue;

public interface TransacaoRepository {

    void salvar(Transacao transacao);

    void limpar();

    Queue<Transacao> findAll();

}
