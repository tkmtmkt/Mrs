package mrs.domain.service.user;

import mrs.domain.model.User;
import mrs.domain.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ReservationUserDetailsService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// �����������G���e�B�e�B�ɃZ�b�g
		User probe = new User();
		probe.setUserId(username);

		// ������������Example�C���X�^���X�𐶐�
		Example<User> example = Example.of(probe);
		User user = userRepository.findOne(example).get();

		if (user == null) {
			throw new UsernameNotFoundException(username + " is not found.");
		}
		return new ReservationUserDetails(user);
	}
}
