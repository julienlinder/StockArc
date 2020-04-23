package ch.hearc.stockarc.service;

import java.util.ArrayList;
import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ch.hearc.stockarc.model.NewUser;
import ch.hearc.stockarc.model.PasswordResetToken;
import ch.hearc.stockarc.model.Person;
import ch.hearc.stockarc.model.Role;
import ch.hearc.stockarc.model.User;
import ch.hearc.stockarc.model.UserCreationToken;
import ch.hearc.stockarc.repository.PasswordResetTokenRepository;
import ch.hearc.stockarc.repository.PeopleRepository;
import ch.hearc.stockarc.repository.RoleRepository;
import ch.hearc.stockarc.repository.UserCreationTokenRepository;
import ch.hearc.stockarc.repository.UserRepository;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private PasswordResetTokenRepository passwordTokenRepository;

    @Autowired
    private UserCreationTokenRepository creationTokenRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new ArrayList<>(roleRepository.findAll()));
        user.setRoles(Arrays.asList(roleUser));

        userRepository.save(user);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
    }

    @Override
    public void createUserCreationTokenForUser(User user, String token) {
        final UserCreationToken myToken = new UserCreationToken(token, user);
        creationTokenRepository.save(myToken);
    }

    @Override
    public void changeUserPassword(final User user, final String password) {
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public void changeUserName(final User user, final String name) {
        user.setName(name);
        userRepository.save(user);
    }

    @Override
    public User createNewPartialUser(@Valid NewUser newUser) {
        Role roleUser = roleRepository.findByName("ROLE_USER");

        User user = new User();
        user.setEmail(newUser.getEmail());
        user.setRoles(Arrays.asList(roleUser));

        User savedUser = userRepository.save(user);

        if (newUser.getExistingPerson() == "Yes") {
            Person person = peopleRepository.findById(newUser.getPerson().getId()).get();
            person.setUser(savedUser);
            peopleRepository.save(person);
        }

        return savedUser;
    }

}