package ch.hearc.stockarc.config;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ch.hearc.stockarc.model.Role;
import ch.hearc.stockarc.model.User;
import ch.hearc.stockarc.repository.PersonRepository;
import ch.hearc.stockarc.repository.RoleRepository;
import ch.hearc.stockarc.repository.UserRepository;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PersonRepository personRepository;

	/*
	 * @Autowired private PasswordEncoder passwordEncoder;
	 */

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (alreadySetup) {
			return;
		}
		createRoleIfNotFound("ROLE_ADMIN");
		createRoleIfNotFound("ROLE_USER");
		Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");
		User user = new User();
		user.setName("test");
		user.setPassword("test");
		user.setEmail("test@test.test");
		user.setRoles(Arrays.asList(roleAdmin));
		userRepository.save(user);
		
		alreadySetup = true;
	}

	@Transactional
	private Role createRoleIfNotFound(String name) {
		Role role = roleRepository.findByName(name);
		if(role == null) {
			role = new Role();
			role.setName(name);
			roleRepository.save(role);
		}
		return role;
	}

}
