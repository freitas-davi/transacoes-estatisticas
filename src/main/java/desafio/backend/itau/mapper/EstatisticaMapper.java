package desafio.backend.itau.mapper;

import desafio.backend.itau.dto.EstatisticaResponseDTO;
import org.springframework.stereotype.Component;

import java.util.DoubleSummaryStatistics;

@Component
public class EstatisticaMapper {

    public EstatisticaResponseDTO toEstatisticaResponse(DoubleSummaryStatistics stats) {
        return new EstatisticaResponseDTO(
                stats.getCount(),
                stats.getSum(),
                stats.getAverage(),
                stats.getMin(),
                stats.getMax()
        );
    }

    public EstatisticaResponseDTO toEstatisticaVazia() {
        return new EstatisticaResponseDTO(
                0,
                0,
                0,
                0,
                0
        );
    }

}
