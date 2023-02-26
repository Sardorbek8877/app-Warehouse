package uz.com.appwarehause.payload;

import lombok.Data;

@Data
public class InputDto {

    private Integer warehouseId;

    private Integer supplierId;

    private Integer currencyId;

    private String factureNumber;

    private String code;
}
