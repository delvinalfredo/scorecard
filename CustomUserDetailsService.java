package bvic.apps.scorecards.service;

import bvic.apps.scorecards.model.CustomUserDetails;
import bvic.apps.scorecards.model.User;
import bvic.apps.scorecards.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

		Optional<User> opt = userRepo.findByUserIdWithRoles(userId); // Get User entity with the help of email from
																		// UserRepository.

		if (opt.isPresent()) {
			User user = opt.get();

			// Tambahkan pemeriksaan untuk flagStatus disini
			if (!"active".equalsIgnoreCase(user.getFlagStatus())) {
				throw new UsernameNotFoundException("User with User ID: " + userId + " is not active");
			}

			UserDetails userDetails = new CustomUserDetails(user);

			return userDetails;
		} else {
			throw new UsernameNotFoundException("User with User ID: " + userId + " not found");
		}
	}

}
