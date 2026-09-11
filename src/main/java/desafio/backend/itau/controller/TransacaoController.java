package desafio.backend.itau.controller;

import desafio.backend.itau.dto.TransacaoRequestDTO;
import desafio.backend.itau.service.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody TransacaoRequestDTO transacaoRequestDTO) {
        transacaoService.salvar(transacaoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletar() {
        transacaoService.limparTransacoes();
        return ResponseEntity.ok().build();
    }

}
