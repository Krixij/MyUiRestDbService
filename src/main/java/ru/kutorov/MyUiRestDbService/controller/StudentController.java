package ru.kutorov.MyUiRestDbService.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.kutorov.MyUiRestDbService.dao.StudentRepository;
import ru.kutorov.MyUiRestDbService.entity.Student;

import java.util.Optional;

@Slf4j
@RestController
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/list")
    public ModelAndView getAllStudents() {
        ModelAndView mv = new ModelAndView("list-students");
        mv.addObject("students", studentRepository.findAll());
        return mv;
    }

    @GetMapping("/addStudentForm")
    public ModelAndView addStudentForm() {
        ModelAndView mv = new ModelAndView("add-student-form");
        Student student = new Student();
        mv.addObject("student", student);
        return mv;
    }

    @PostMapping("/saveStudent")
    public RedirectView saveStudent(@ModelAttribute Student student) {
        studentRepository.save(student);
        return new RedirectView("/list");
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Integer studentId) {
        ModelAndView mv = new ModelAndView("add-student-form");
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        Student student = new Student();
        if(optionalStudent.isPresent()) {
            student = optionalStudent.get();
        }
        mv.addObject("student", student);
        return mv;
    }

    @GetMapping("/deleteStudent")
    public RedirectView deleteStudent(@RequestParam Integer studentId, ModelAndView model) {
        studentRepository.deleteById(studentId);
        return new RedirectView("/list");
    }
}
