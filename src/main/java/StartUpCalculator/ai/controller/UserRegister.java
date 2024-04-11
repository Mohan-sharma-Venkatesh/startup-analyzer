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
  public String getRegister(){
    return "userRegister";
  }
  
 @PostMapping(value="/register")
public ModelAndView register(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult result, Model model) {
    ModelAndView modelAndView = new ModelAndView();
    if (result.hasErrors()) {
        System.out.print("--------------------------------------"+result);
          List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors) {
                System.out.println("Field: " + error.getField() + ", Error: " + error.getDefaultMessage());
            }
            // Add errors to model
            model.addAttribute("errors", errors);
        modelAndView.setViewName("userRegister");
        return modelAndView;
    }
    userService.save(userDto);
    modelAndView.setViewName("index");
    return modelAndView;
}
   
}

