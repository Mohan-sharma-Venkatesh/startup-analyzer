package StartUpCalculator.ai.controller;
import StartUpCalculator.ai.dto.UserDto;
import StartUpCalculator.ai.model.User;
import StartUpCalculator.ai.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserRegister{
 
  @Autowired
  private UserService userService;
  
  @RequestMapping(value="/register")
  public String getRegister( @ModelAttribute("user") UserDto userDto){
    return "userRegister";
  }
  
  @PostMapping(value="/register")
  public String register(@Valid @ModelAttribute("user") UserDto userDto, 
      BindingResult result, Model model) {
    if (result.hasErrors()) {
      model.addAttribute("user", userDto);
      System.out.print("--------------------------------------"+result.getAllErrors());
      return "userRegister";
    }
    userService.save(userDto);
    return "index";
}
   
}

