package StartUpCalculator.ai.controller;
import StartUpCalculator.ai.dto.UserDto;
import StartUpCalculator.ai.model.User;
import StartUpCalculator.ai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserRegister{
 
  @Autowired
  private UserService userService;
  
  @RequestMapping(value="/register")
  public String getRegister(){
    return "userRegister";
  }
  
  @PostMapping(value="/register")
  public String register(@ModelAttribute("userDto") UserDto userDto){
    userService.save(userDto);
    return "index";
  }
    
}

