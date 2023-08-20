package uz.pdp.lessonjpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.lessonjpa.entity.Student;

/**
 * @Created by Sardor Dev
 * @Date 21:08, 20.08.2023
 */
@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    Page<Student> findAllByGroup_Faculty_UniversityId(Integer group_faculty_university_id, Pageable pageable);

}
