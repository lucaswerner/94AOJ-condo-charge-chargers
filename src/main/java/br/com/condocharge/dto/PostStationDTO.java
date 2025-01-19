package br.com.condocharge.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostStationDTO {

    @Min(1)
    @NotNull
    private Integer condominiumId;

    @Min(1)
    @NotNull
    private Integer number;

}
