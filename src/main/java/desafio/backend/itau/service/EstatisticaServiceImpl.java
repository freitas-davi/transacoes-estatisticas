package desafio.backend.itau.service;

import desafio.backend.itau.dto.EstatisticaResponseDTO;
import desafio.backend.itau.repository.interfaces.TransacaoRepository;
import desafio.backend.itau.mapper.EstatisticaMapper;
import desafio.backend.itau.service.interfaces.EstatisticaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import desafio.backend.itau.domain.Transacao;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;


@Slf4j
@Service
public class EstatisticaServiceImpl implements EstatisticaService {

    private final TransacaoRepository transacaoRepository;
    private final EstatisticaMapper  estatisticaMapper;

    public EstatisticaServiceImpl(TransacaoRepository transacaoRepository,
                                  EstatisticaMapper estatisticaMapper) {
        this.transacaoRepository = transacaoRepository;
        this.estatisticaMapper = estatisticaMapper;
    }

    @Override
    public EstatisticaResponseDTO getEstatistica() {
        OffsetDateTime dataHoraLimite = OffsetDateTime.now().minusSeconds(60);

        log.info("Calculando estatísticas para transações a partir de: {}", dataHoraLimite);

        DoubleSummaryStatistics estatisticas =
                transacaoRepository.findAll().stream()
                .filter(entry -> entry.getDataHora().isAfter(dataHoraLimite))
                .mapToDouble(Transacao::getValor)
                .summaryStatistics();

        if (estatisticas.getCount() == 0) {
            log.info("Não há transações válidas nos últimos 60 segundos.");
            return estatisticaMapper.toEstatisticaVazia();
        }

        log.info("Estatísticas calculadas: {}", estatisticas);

        return estatisticaMapper.toEstatisticaResponse(estatisticas);
    }

}
