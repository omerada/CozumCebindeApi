package com.omerada.cozumcebinde.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BaseRequest {

    @Min(value = 1,message = "İl kodu Minimum 1 olmalıdır")
    @Max(value = 999,message = "İl kodu Maximum 999 olmalıdır.")
    private Long ilKod;
    private Long kartNo;

    @Min(value = 1,message = "Yeni şube kodu Minimum 1 olmalıdır")
    @Max(value = 99,message = "Yeni şube kodu Maximum 99 olmalıdır.")
    private Long yeniSubeKod;

    @Min(value = 1,message = "Eski şube kodu Minimum 1 olmalıdır")
    @Max(value = 99,message = "Eski şube kodu Maximum 99 olmalıdır.")
    private Long eskiSubeKod;

    private Long takipYil;
    private Long takipNo;

    @Min(value = 1,message = "Tescil Sıra No Minimum 1 olmalıdır")
    @Max(value = 9999999,message = "Tescil Sıra No  Maximum 9999999 olmalıdır.")
    private Long tescilSiraNo;

    @Min(value = 1,message = "Kontrol değeri Minimum 1 olmalıdır")
    @Max(value = 9999999,message = "Kontrol değeri 99 olmalıdır.")
    private Long checkDigit;
}
