package ch.hearc.stockarc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
	
	/*@Autowired
	private PasswordEncoder passwordEncoder;*/

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		
	}

}
