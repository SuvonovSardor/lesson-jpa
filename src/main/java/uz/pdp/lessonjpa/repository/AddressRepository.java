package uz.pdp.lessonjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lessonjpa.entity.Address;


public interface AddressRepository extends JpaRepository<Address,Integer> {

}
