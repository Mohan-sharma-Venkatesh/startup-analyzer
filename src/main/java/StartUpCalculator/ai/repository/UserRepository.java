package StartUpCalculator.ai.repository;
import StartUpCalculator.ai.model.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long>  {
  List<User> findAll();
  
  save();

  @Query("select 1 from users where email= ?1)
  boolean existsByEmail(String email);

}
