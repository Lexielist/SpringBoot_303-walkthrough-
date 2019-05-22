package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import javax.validation.Valid;

@Controller
public class Homecontroller {
    @Autowired
    CourseRepository courseRepository;

    @RequestMapping("/")
    public String listCourses(Model model){
        model.addAttribute("courses", courseRepository.findAll());
        return "list";
    }

    @GetMapping
    public String listCourse(Model model){
        model.addAttribute("course", new Course());
        return "courseform";

    }
    @PostMapping("/process")
    public String processForm(@Valid Course course,
       BindingResult result){
        if (result.hasErrors()){
            return "courseform";

        }
        courseRepository.save(course);
        return "redirect:/";
    }
    @RequestMapping("/detail/{id}")
    public String showCourse(@PathVariable("id") Long id, Model model) {

        model.addAttribute("course", courseRepository.findById(id).get());
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") Long id, Model model)
    {
        model.addAttribute("course", courseRepository.findById(id).get());
        return "courseform";
    }
    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") Long id){
            courseRepository.deleteById(id);
            return "redirect:/";
     }

}
