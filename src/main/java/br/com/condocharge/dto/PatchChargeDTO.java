package br.com.condocharge.dto;

import java.time.LocalDateTime;

import br.com.condocharge.enums.ChargeStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatchChargeDTO {
    private LocalDateTime chargeEndPreview;
    private LocalDateTime chargeEnd;
    private Integer energyConsumption;
    private ChargeStatus status;
}
