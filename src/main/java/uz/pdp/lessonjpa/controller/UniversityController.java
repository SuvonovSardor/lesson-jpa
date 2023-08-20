package uz.pdp.lessonjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.lessonjpa.entity.Address;
import uz.pdp.lessonjpa.entity.University;
import uz.pdp.lessonjpa.payload.UniversityDto;
import uz.pdp.lessonjpa.repository.AddressRepository;
import uz.pdp.lessonjpa.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {

    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    AddressRepository addressRepository;

    //READ UNIVERSITY
    @RequestMapping(value = "/university", method = RequestMethod.GET)
    public List<University> getUniversity() {
        List<University> universityList = universityRepository.findAll();
        return universityList;
    }

    //CREAT
    @RequestMapping(value = "/university", method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniversityDto universityDto) {
        //Yangi adress ochib oldik
        Address address = new Address(universityDto.getCity(), universityDto.getDistrict(), universityDto.getStreet());
        //yasab olgan address objectimizni db ga saqladik va u bizga saqlangan adressni berdi
        Address savedAddress = addressRepository.save(address);


        //yangi universitet yasab oldik
        University university = new University();
        university.setName(universityDto.getName());
        university.setAddress(savedAddress);
        universityRepository.save(university);

        return "University added";

    }

    //UPDATE
    @RequestMapping(value = "/university/{id}", method = RequestMethod.PUT)
    public String editUniversity(@PathVariable Integer id, @RequestBody UniversityDto universityDto) {
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isPresent()) {
            University university = optionalUniversity.get();
            university.setName(universityDto.getName());
            Address address = university.getAddress();
            address.setCity(universityDto.getCity());
            address.setDistrict(universityDto.getDistrict());
            address.setStreet(universityDto.getStreet());
            addressRepository.save(address);

            universityRepository.save(university);
            return "University edited";
        }
        return "University not found";
    }

    //DELETED

    @RequestMapping(value = "/university/{id}", method = RequestMethod.DELETE)
    public String deleteUniversity(@PathVariable Integer id) {
        universityRepository.deleteById(id);
        return "University deleted";
    }

}
