package uz.pdp.lessonjpa.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String name;

//    @OneToMany // One group to many students
//    private List<Student> students;

    @ManyToOne  // many group to one faculty
    private Faculty faculty;




}
