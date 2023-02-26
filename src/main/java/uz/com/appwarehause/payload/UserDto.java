package uz.com.appwarehause.payload;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import uz.com.appwarehause.entity.Warehouse;

import java.util.List;
import java.util.Set;

@Data
public class UserDto {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String password;

    private List<Integer> warehouseId;
}
