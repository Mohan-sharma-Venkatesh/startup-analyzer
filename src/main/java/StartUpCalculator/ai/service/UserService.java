package StartUpCalculator.ai.service;

import StartUpCalculator.ai.model.User;
import StartUpCalculator.ai.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{

  @Autowired
  private UserRepository userrepository;

  public List<User> getAllUsers(){
    return userrepository.findAll();
  }


}
