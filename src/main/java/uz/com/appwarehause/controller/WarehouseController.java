package uz.com.appwarehause.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.com.appwarehause.entity.Warehouse;
import uz.com.appwarehause.payload.Result;
import uz.com.appwarehause.service.WarehouseService;

import javax.swing.plaf.PanelUI;
import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    //ADD WAREHOUSE
    @PostMapping
    public Result addWarehouse(@RequestBody Warehouse warehouse){
        return warehouseService.addWarehouse(warehouse);
    }

    //GET WAREHOUSES
    @GetMapping
    public List<Warehouse> getWarehouses(){
        return warehouseService.getWarehouses();
    }

    //GET WAREHOUSE BY ID
    @GetMapping("/{id}")
    public Warehouse getWarehouseById(@PathVariable Integer id){
        return warehouseService.getWarehouseById(id);
    }

    //DELETE WAREHOUSE
    @DeleteMapping("/{id}")
    public Result deleteWarehouse(@PathVariable Integer id){
        return warehouseService.deleteWarehouse(id);
    }

    //EDIT WAREHOUSE
    @PutMapping("/{id}")
    public Result editWarehouse(@PathVariable Integer id, @RequestBody Warehouse warehouse){
        return warehouseService.editHouse(id, warehouse);
    }
}
