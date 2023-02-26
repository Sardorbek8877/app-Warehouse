package uz.com.appwarehause.payload;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import uz.com.appwarehause.entity.Input;
import uz.com.appwarehause.entity.Product;

import java.sql.Date;

@Data
public class InputProductDto {

    private Integer productId;

    private Double amount;

    private Double price;

    private Date expireDate;

    @ManyToOne
    private Integer inputId;
}
