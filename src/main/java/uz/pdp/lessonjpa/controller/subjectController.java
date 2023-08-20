package uz.pdp.lessonjpa.controller;


import org.hibernate.mapping.Subclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lessonjpa.entity.Subject;
import uz.pdp.lessonjpa.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/subject")
public class subjectController {
    @Autowired
    SubjectRepository subjectRepository;

    //create
    @RequestMapping(method = RequestMethod.POST)
    public String addSubject(@RequestBody Subject subject) {
        boolean existsByName = subjectRepository.existsByName(subject.getName());
        if (existsByName)
            return "This subject already exist";

        subjectRepository.save(subject);
        return "Subject added";

    }

    //READ
    @GetMapping
    public List<Subject> getSubjects() {
        List<Subject> subjectList = subjectRepository.findAll();
        return subjectList;
    }


    //UPDATE
    @PutMapping("/{id}")
    public String updateSubject(@PathVariable Integer id, @RequestBody Subject subject) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()) {
            Subject editedSubject = optionalSubject.get();
            editedSubject.setName(subject.getName());
            subjectRepository.save(editedSubject);
            return "Subject updated";
        }
        return "Subject not found";
    }

    //DELETE
    @DeleteMapping("/{id}")
    public String deleteSubject(@PathVariable Integer id) {
        subjectRepository.deleteById(id);
        return "Subject deleted";
    }
}
