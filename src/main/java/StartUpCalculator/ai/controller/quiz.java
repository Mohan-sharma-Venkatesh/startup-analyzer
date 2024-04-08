package StartUpCalculator.ai.controller;
import StartUpCalculator.ai.model.User;
import StartUpCalculator.ai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class quiz{
  
  @GetMapping("/")
  public String getHomePage(Model model)
  {
    model.addAttribute("message", "we will---------------------- win");
    return "index";
  }

    @Autowired
    private UserService userService;
    
    @GetMapping("/users")
    public String getAllItems(Model model) {
        
      List<User> userlist=userService.getAllUsers();
      System.out.print(userlist); 
      model.addAttribute("users",userlist);
      return "users";
    }
}

