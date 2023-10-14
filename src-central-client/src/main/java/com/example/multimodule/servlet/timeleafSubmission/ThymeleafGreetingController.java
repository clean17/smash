package com.example.multimodule.servlet.timeleafSubmission;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ThymeleafGreetingController {

  @GetMapping("/submission")
  public String greetingForm(Model model) {
    model.addAttribute("greeting", new Greeting());
    return "greeting";
  }

  /**
   * ModelAttribute를 통해서 데이터를 그대로 result뷰로 전달
   * @param greeting 
   * @param model
   * @return
   */
  @PostMapping("/submission")
  public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
    model.addAttribute("greeting", greeting);
    return "result";
  }

}