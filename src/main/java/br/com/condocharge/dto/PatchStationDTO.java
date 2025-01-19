package br.com.condocharge.dto;

import br.com.condocharge.enums.StationStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatchStationDTO {
    private Integer number;
    private StationStatus status;
}
