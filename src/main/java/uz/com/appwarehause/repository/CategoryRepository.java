package uz.com.appwarehause.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.com.appwarehause.entity.Category;
import uz.com.appwarehause.entity.Measurement;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    boolean existsByName(String name);
}
