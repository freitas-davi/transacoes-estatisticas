package desafio.backend.itau.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class Transacao {

    private double valor;

    private OffsetDateTime dataHora;

}
