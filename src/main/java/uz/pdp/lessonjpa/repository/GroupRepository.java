package uz.pdp.lessonjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lessonjpa.entity.Address;
import uz.pdp.lessonjpa.entity.Group;


public interface GroupRepository extends JpaRepository<Group,Integer> {

}
