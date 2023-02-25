package uz.com.appwarehause.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.com.appwarehause.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

    boolean existsByName(String name);
}
