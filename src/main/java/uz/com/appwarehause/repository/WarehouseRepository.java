package uz.com.appwarehause.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.com.appwarehause.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

}
