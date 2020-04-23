package ch.hearc.stockarc.service;

import javax.validation.Valid;

import ch.hearc.stockarc.model.NewUser;
import ch.hearc.stockarc.model.User;

public interface IUserService {

    void save(User user);

    User findByName(String name);

    User findByEmail(String email);

    void createPasswordResetTokenForUser(final User user, final String token);

    void createUserCreationTokenForUser(final User user, final String token);

    void changeUserPassword(final User user, final String password);

	User createNewPartialUser(@Valid NewUser newUser);

}