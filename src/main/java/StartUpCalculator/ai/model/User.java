package StartUpCalculator.ai.model;

import jakarta.persistence.*;

@Entity
@Table(name= "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String password;
    
    public void setId(int id){
      this.id=id;
    }

    public int getId(){
      return id;
    }
    
    public void setName(String name){
      this.name=name;
    } 

    public String getName(){
      return name;
    }

    public void setPassword(String password){
      this.password= password;
    }

    public String getPassword(){
      return password;
    }
}

