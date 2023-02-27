package uz.com.appwarehause.payload;

import lombok.Data;

@Data
public class OutputDto {

    private Integer warehouseId;

    private Integer clientId;

    private Integer currencyId;

    private String factureNumber;

    private String code;
}
