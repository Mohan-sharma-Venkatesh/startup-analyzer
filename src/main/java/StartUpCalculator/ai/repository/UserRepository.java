package StartUpCalculator.ai.repository;
import StartUpCalculator.ai.model.User;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>  {
  List<User> findAll();
  
  @Query(value=" select count(1) from users where email= ?1 ", nativeQuery= true)
  int existsByEmail(String email);


}

