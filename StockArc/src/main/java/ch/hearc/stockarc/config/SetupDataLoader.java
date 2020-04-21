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
import ch.hearc.stockarc.repository.RoleRepository;
import ch.hearc.stockarc.repository.UserRepository;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (alreadySetup) {
			return;
		}
		createRoleIfNotFound("ROLE_ADMIN");
		createRoleIfNotFound("ROLE_USER");
		User mainAdmin = userRepository.findByName("mainAdmin");
		if(mainAdmin == null) {
			Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");
			mainAdmin = new User();
			mainAdmin.setName("mainAdmin");
			mainAdmin.setPassword(passwordEncoder.encode("yxcvbnm,.-"));
			mainAdmin.setEmail("test@test.test");
			mainAdmin.setRoles(Arrays.asList(roleAdmin));
			userRepository.save(mainAdmin);
		}

		alreadySetup = true;
	}

	@Transactional
	private Role createRoleIfNotFound(String name) {
		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role();
			role.setName(name);
			roleRepository.save(role);
		}
		return role;
	}

}
