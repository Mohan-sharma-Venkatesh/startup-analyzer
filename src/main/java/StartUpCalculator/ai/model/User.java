package StartUpCalculator.ai.model;

import jakarta.persistence.*;

@Entity
@Table(name= "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String password;
    
    public void setId(int id){
      this.id=id;
    }

    public int getId(){
      return id;
    }
    
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

