package uz.com.appwarehause.payload;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String password;

    private List<Integer> warehouseId;
}
