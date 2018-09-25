package co.descubra.descubraapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.descubra.descubraapi.models.User;

@Repository
public interface UserService extends JpaRepository<User, Long>{

	User findByEmailAndPassword(String email, String password);
}
