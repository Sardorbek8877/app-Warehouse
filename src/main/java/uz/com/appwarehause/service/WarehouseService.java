package uz.com.appwarehause.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.com.appwarehause.entity.Warehouse;
import uz.com.appwarehause.payload.Result;
import uz.com.appwarehause.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    public Result addWarehouse(Warehouse warehouse){
        boolean exist = warehouseRepository.existsByName(warehouse.getName());
        if (exist)
            return new Result("Warehouse name already exist", false);
        warehouseRepository.save(warehouse);
        return new Result("Warehouse added", true);
    }

    public List<Warehouse> getWarehouses(){
        return warehouseRepository.findAll();
    }

    public Warehouse getWarehouseById(Integer id){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isEmpty())
            return new Warehouse();
        return optionalWarehouse.get();
    }

    public Result deleteWarehouse(Integer id){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isEmpty())
            return new Result("Warehouse not found", false);
        warehouseRepository.deleteById(id);
        return new Result("Warehouse deleted", true);
    }

    public Result editHouse(Integer id, Warehouse warehouse){
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isEmpty())
            return new Result("Warehouse not found", false);
        Warehouse editingWarehouse = optionalWarehouse.get();
        editingWarehouse.setName(warehouse.getName());
        warehouseRepository.save(editingWarehouse);
        return new Result("Warehouse editing", true);
    }
}
