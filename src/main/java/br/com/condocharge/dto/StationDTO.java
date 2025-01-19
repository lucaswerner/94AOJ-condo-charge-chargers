package br.com.condocharge.dto;

import java.time.LocalDateTime;

import br.com.condocharge.enums.StationStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StationDTO {
    private String id;
    private Integer condominiumId;
    private Integer number;
    private StationStatus status;
    private LocalDateTime creationDt;
    private LocalDateTime lastUpdate;
    private String condominiumCnpj;
}
