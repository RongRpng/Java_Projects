package cn.Esther.controller;


import cn.Esther.pojo.Content;
import cn.Esther.pojo.Student;
import cn.Esther.service.ContentService;
import cn.Esther.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class TestController {
    @Autowired
    private ContentService contentService;

    @Autowired
    private StudentService studentService;

    @RequestMapping("/a/index")
    public String getIndex(Model model){
        List<Content> contentList = contentService.selectByParam(2);
        List<Student> studentList = studentService.selectByParam("1");
        model.addAttribute("contentList", contentList);
        model.addAttribute("studentList", studentList);
        return "index";

    }
}
