package uz.com.appwarehause.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.com.appwarehause.entity.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

    boolean existsByName(String name);
}
