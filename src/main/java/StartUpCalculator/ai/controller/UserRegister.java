package StartUpCalculator.ai.controller;
import StartUpCalculator.ai.dto.UserDto;
import StartUpCalculator.ai.service.UserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


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
    String email= userDto.getEmail();
    if (userService.checkEmail(email) == false){
      System.out.print("----------");
      model.addAttribute("email_exits", "email is already registered");
      return "userRegister";
    }
    if (result.hasErrors()) {
      model.addAttribute("user", userDto);
      return "userRegister";
    }
    userService.save(userDto);
    return "index";
  }

}

