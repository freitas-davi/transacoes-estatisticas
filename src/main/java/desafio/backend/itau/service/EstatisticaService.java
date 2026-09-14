package desafio.backend.itau.service;

import desafio.backend.itau.dto.EstatisticaResponseDTO;
import desafio.backend.itau.interfaces.TransacaoRepository;
import desafio.backend.itau.mapper.EstatisticaMapper;
import org.springframework.stereotype.Service;
import desafio.backend.itau.domain.Transacao;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;


@Service
public class EstatisticaService {

    private final TransacaoRepository transacaoRepository;
    private final EstatisticaMapper  estatisticaMapper;

    public EstatisticaService(TransacaoRepository transacaoRepository,
                              EstatisticaMapper estatisticaMapper) {
        this.transacaoRepository = transacaoRepository;
        this.estatisticaMapper = estatisticaMapper;
    }

    public EstatisticaResponseDTO getEstatistica() {
        OffsetDateTime dataHoraLimite = OffsetDateTime.now().minusSeconds(60);

        DoubleSummaryStatistics estatisticas =
                transacaoRepository.findAll().stream()
                .filter(entry -> entry.getDataHora().isAfter(dataHoraLimite))
                .mapToDouble(Transacao::getValor)
                .summaryStatistics();

        if (estatisticas.getCount() == 0) {
            return estatisticaMapper.toEstatisticaVazia();
        }

        return estatisticaMapper.toEstatisticaResponse(estatisticas);
    }

}
