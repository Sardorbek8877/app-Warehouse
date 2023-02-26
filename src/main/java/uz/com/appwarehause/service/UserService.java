package uz.com.appwarehause.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.com.appwarehause.entity.Product;
import uz.com.appwarehause.entity.User;
import uz.com.appwarehause.entity.Warehouse;
import uz.com.appwarehause.payload.Result;
import uz.com.appwarehause.payload.UserDto;
import uz.com.appwarehause.repository.UserRepository;
import uz.com.appwarehause.repository.WarehouseRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    WarehouseRepository warehouseRepository;

    public Result addUser(UserDto userDto){

        // CHECK PHONE NUMBER
        boolean exists = userRepository.existsByPhoneNumber(userDto.getPhoneNumber());
        if (exists)
            return new Result("Phone number already exist", false);

        //CREATE WAREHOUSE SET
        Set<Warehouse> warehouseSet = new HashSet<>();
        List<Integer> warehouseIdList = userDto.getWarehouseId();
        for (Integer warehouseId : warehouseIdList) {
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouseId);
            if (optionalWarehouse.isEmpty())
                return new Result("Warehouse not found", false);
            Warehouse warehouse = optionalWarehouse.get();
            warehouseSet.add(warehouse);
        }

        User user = new User();
        user.setCode(generateCode());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setWarehouses(warehouseSet);
        userRepository.save(user);
        return new Result("User added", true);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            return new User();
        User user = optionalUser.get();
        return user;
    }

    public Result deleteUser(Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            return new Result("User noty found", false);

        userRepository.deleteById(id);
        return new Result("User successfully deleted", true);
    }

    public Result editUser(Integer id, UserDto userDto){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            return new Result("User not found", false);
        User user = optionalUser.get();

        //CREATE WAREHOUSES SET
        Set<Warehouse> warehouseSet = new HashSet<>();
        List<Integer> warehouseIdList = userDto.getWarehouseId();
        for (Integer warehouseId : warehouseIdList) {
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouseId);
            if (optionalWarehouse.isEmpty())
                return new Result("Warehouse not found", false);
            Warehouse warehouse = optionalWarehouse.get();
            warehouseSet.add(warehouse);
        }

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        user.setWarehouses(warehouseSet);
        return new Result("User successfully edited", true);
    }

    private String generateCode(){
        List<User> userList = userRepository.findAll();
        return String.valueOf(userList.size());
    }
}
