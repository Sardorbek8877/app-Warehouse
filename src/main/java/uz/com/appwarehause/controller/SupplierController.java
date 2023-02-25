package uz.com.appwarehause.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.com.appwarehause.entity.Supplier;
import uz.com.appwarehause.payload.Result;
import uz.com.appwarehause.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;

    //GET SUPPLIERS
    @PostMapping
    public Result addSupplier(@RequestBody Supplier supplier){
        return supplierService.addSupplier(supplier);
    }

    //GET SUPPLIERS
    public List<Supplier> getSuppliers(){
        return supplierService.getSuppliers();
    }

    //GET SUPPLIER BY ID
    @GetMapping("/{id}")
    public Supplier getSupplierById(@PathVariable Integer id){
        return supplierService.getSupplierById(id);
    }

    //DELETE SUPPLIER
    public Result deleteSupplier(@PathVariable Integer id){
        return supplierService.deleteSupplier(id);
    }

    //EDIT SUPPLIER
    @PutMapping("/{id}")
    public Result editSupplier(@PathVariable Integer id, @RequestBody Supplier supplier){
        return supplierService.editSupplier(id, supplier);
    }
}
