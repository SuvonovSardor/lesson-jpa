package uz.pdp.lessonjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.lessonjpa.entity.University;

@Repository
public interface UniversityRepository extends JpaRepository<University,Integer> {
}
