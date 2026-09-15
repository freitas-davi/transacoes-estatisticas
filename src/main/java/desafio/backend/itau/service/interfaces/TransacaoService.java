package desafio.backend.itau.service.interfaces;

import desafio.backend.itau.dto.TransacaoRequestDTO;

public interface TransacaoService {

    void salvar(TransacaoRequestDTO dto);

    void limparTransacoes();

}
