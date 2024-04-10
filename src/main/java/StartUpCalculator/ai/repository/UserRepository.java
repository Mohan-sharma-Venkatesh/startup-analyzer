package StartUpCalculator.ai.repository;
import StartUpCalculator.ai.model.User;
import java.util.List;

import StartUpCalculator.ai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends CrudRepository<User,Long>  {

  List<User> findAll();


}
