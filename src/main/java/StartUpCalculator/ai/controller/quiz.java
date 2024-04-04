package StartUpCalculator.ai.controller;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class quiz{
  
  @GetMapping("/")
  public String getHomePage(Model model)
  {
    model.addAttribute("message", "we will win");
    return "index";
  }
}

