package com.example.springbreaking.taglib;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JSTLController {

    @GetMapping(value = "/jsp")
    public String jspTrial(ModelMap model){
        model.addAttribute("scoop", "전달");
        return "trial";
    }
}
