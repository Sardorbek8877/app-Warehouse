package uz.com.appwarehause.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.com.appwarehause.entity.Supplier;
import uz.com.appwarehause.payload.Result;
import uz.com.appwarehause.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    public Result addSupplier(Supplier supplier){
        boolean exists = supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber());
        if (exists)
            return new Result("Phone number already exist", false);

        supplierRepository.save(supplier);
        return new Result("Supplier successfully added", true);
    }

    public List<Supplier> getSuppliers(){
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(Integer id){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isEmpty())
            return new Supplier();
        return optionalSupplier.get();
    }

    public Result deleteSupplier(Integer id){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()){
            supplierRepository.deleteById(id);
            return new Result("Supplier deleted", true);
        }
        return new Result("Supplier not found", false);
    }

    public Result editSupplier(Integer id, Supplier supplier){
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isEmpty())
            return new Result("Supplier not found", false);

        Supplier editingSupplier = optionalSupplier.get();

        //CHECK PHONE NUMBER
        boolean exist = supplierRepository.existsByPhoneNumber(supplier.getPhoneNumber());
        if (exist)
            return new Result("Phone number already exist", false);

        //SAVE
        editingSupplier.setPhoneNumber(supplier.getPhoneNumber());
        editingSupplier.setName(supplier.getName());
        supplierRepository.save(editingSupplier);
        return new Result("Supplier successfully edited", true);
    }

}
