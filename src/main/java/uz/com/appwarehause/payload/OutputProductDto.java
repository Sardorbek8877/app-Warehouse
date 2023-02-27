package uz.com.appwarehause.payload;

import lombok.Data;

@Data
public class OutputProductDto {

    private Integer productId;

    private Double amount;

    private Double price;

    private Integer outputId;
}
