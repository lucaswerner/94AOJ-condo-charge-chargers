package br.com.condocharge.dto;

import java.time.LocalDateTime;

import br.com.condocharge.enums.ChargeStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChargeDTO {
    private Long id;
    private Long userId;
    private ChargeStatus status;
    private LocalDateTime beginning;
    private LocalDateTime end;
    private StationDTO station;
}
