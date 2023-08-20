package uz.pdp.lessonjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lessonjpa.entity.Address;
import uz.pdp.lessonjpa.entity.Subject;


public interface SubjectRepository extends JpaRepository<Subject,Integer> {

    boolean existsByName(String name);

}
