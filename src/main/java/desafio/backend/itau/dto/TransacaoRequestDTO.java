package desafio.backend.itau.dto;

import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public record TransacaoRequestDTO(

        @NotNull
        double valor,

        @NotNull
        OffsetDateTime dataHora

) { }
