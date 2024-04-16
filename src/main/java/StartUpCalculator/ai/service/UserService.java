package StartUpCalculator.ai.service;

import StartUpCalculator.ai.dto.UserDto;
import StartUpCalculator.ai.model.User;
import StartUpCalculator.ai.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService{

  @Autowired
  private UserRepository userRepository;

  public User save(UserDto userDto){
    User user= convertToEntity(userDto);
    return userRepository.save(user);
  }

  public Boolean existsByEmail(UserDto userDto){
    String email=userDto.getEmail();
    System.out.println("----------------"+email);
    boolean res =userRepository.existsByEmail(email); 
    return res;
  }
  private UserDto convertToDTO(User user) {
    UserDto userDTO = new UserDto();
    userDTO.setEmail(user.getEmail());
    userDTO.setPassword(user.getPassword());
    return userDTO;
  }

 
  private User convertToEntity(UserDto userDTO) {
    User user = new User();
    user.setEmail(userDTO.getEmail());
    user.setPassword(userDTO.getPassword());
    return user;
  }

}
