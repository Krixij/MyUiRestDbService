package ru.kutorov.MyUiRestDbService.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.kutorov.MyUiRestDbService.dao.StudentGroupRepository;
import ru.kutorov.MyUiRestDbService.entity.StudentGroup;

import java.util.Optional;

@Slf4j
@RestController
public class StudentGroupController {
    private final StudentGroupRepository groupRepository;

    public StudentGroupController(StudentGroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @GetMapping("/listGroups")
    public ModelAndView getAllGroups() {
        ModelAndView mv = new ModelAndView("list-groups");
        mv.addObject("groups", groupRepository.findAll());
        return mv;
    }

    @GetMapping("/addGroupForm")
    public ModelAndView addGroupForm() {
        ModelAndView mv = new ModelAndView("add-group-form");
        StudentGroup group = new StudentGroup();
        mv.addObject("group", group);
        return mv;
    }

    @PostMapping("/saveGroup")
    public RedirectView saveGroup(@ModelAttribute StudentGroup group) {
        groupRepository.save(group);
        return new RedirectView("/listGroups");
    }

    @GetMapping("/showUpdateGroupForm")
    public ModelAndView showUpdateGroupForm(@RequestParam Integer groupId) {
        ModelAndView mv = new ModelAndView("add-group-form");
        Optional<StudentGroup> optionalGroup = groupRepository.findById(groupId);
        StudentGroup group = new StudentGroup();
        if(optionalGroup.isPresent()) {
            group = optionalGroup.get();
        }
        mv.addObject("group", group);
        return mv;
    }

    @GetMapping("/deleteGroup")
    public RedirectView deleteGroup(@RequestParam Integer groupId) {
        groupRepository.deleteById(groupId);
        return new RedirectView("/listGroups");
    }
}
