package uz.pdp.lessonjpa.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lessonjpa.entity.Faculty;
import uz.pdp.lessonjpa.entity.University;
import uz.pdp.lessonjpa.payload.FacultyDto;
import uz.pdp.lessonjpa.repository.FacultyRepository;
import uz.pdp.lessonjpa.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

/**
 * @Created by Sardor Dev
 * @Date 20:42, 08.08.2023
 */
@RestController
@RequestMapping("/faculty")
public class facultyController {

    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    UniversityRepository universityRepository;

    //CREATE
    @PostMapping
    public String addFaculty(@RequestBody FacultyDto facultyDto) {
        boolean exists = facultyRepository.existsByNameAndUniversityId(facultyDto.getName(), facultyDto.getUniversityId());
        if (exists)
            return "This university such faculty exists";

        Faculty faculty = new Faculty();
        faculty.setName(facultyDto.getName());
        Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
        if (optionalUniversity.isPresent())
            return "University not found";
        faculty.setUniversity(optionalUniversity.get());
        facultyRepository.save(faculty);
        return "Faculty saved";
    }

    //READ
    @GetMapping
    public List<Faculty> getFaculty() {
        List<Faculty> facultyList = facultyRepository.findAll();
        return facultyList;
    }

    //BARCHA VAZIRLIK UCHUN
    @GetMapping()
    public List<Faculty> getFaculties() {
        return facultyRepository.findAll();
    }


    //UNIVERSITET XODIMI UCHUN
    @GetMapping("/byUniversityId/{universityId}")
    public List<Faculty> getFacultiesByUniversityId(@PathVariable Integer universityId) {
        List<Faculty> allByUniversityId = facultyRepository.findAllByUniversityId(universityId);
        return allByUniversityId;
    }

    @DeleteMapping("/{id}")
    public String deleteFaculty(@PathVariable Integer id) {
        try {
            facultyRepository.deleteById(id);
            return "Faculty deleted";
        } catch (Exception e) {
            return "Error in deleting";
        }

    }

    @PutMapping("/{id}")
    public String editFaculty(@PathVariable Integer id, @RequestBody FacultyDto facultyDto) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()) {
            Faculty faculty = optionalFaculty.get();
            faculty.setName(facultyDto.getName());
            Optional<University> optionalUniversity = universityRepository.findById(a = facultyDto.getUniversityId());
            if (!optionalUniversity.isPresent()) {
                return "University not found";
            }
            faculty.setUniversity(optionalUniversity.get());
            facultyRepository.save(faculty);
            return "Faculty edited";
        }
        return "Facultet not found";
    }

}
