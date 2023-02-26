package uz.com.appwarehause.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.com.appwarehause.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    boolean existsByPhoneNumber(String phoneNumber);
}
