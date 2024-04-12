package StartUpCalculator.ai.dto;

import jakarta.validation.constraints.NotBlank;

public class UserDto {
  @NotBlank(message= "Email is mandatory")
  private String email;

  @NotBlank(message = "Password is mandatory")
  private String password;
    
    public void setEmail(String email){
      this.email=email;
    } 

    public String getEmail(){
      return email;
    }

    public void setPassword(String password){
      this.password= password;
    }

    public String getPassword(){
      return password;
    }
}

