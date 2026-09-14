package desafio.backend.itau.controller;

import desafio.backend.itau.dto.EstatisticaResponseDTO;
import desafio.backend.itau.service.EstatisticaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    private final EstatisticaService estatisticaService;

    public EstatisticaController(EstatisticaService estatisticaService) {
        this.estatisticaService = estatisticaService;
    }

    @GetMapping
    public EstatisticaResponseDTO getEstatisticaService() {
        return estatisticaService.getEstatistica();
    }

}
