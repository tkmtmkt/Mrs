package mrs.domain.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mrs.domain.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	User findOne(String username);

}