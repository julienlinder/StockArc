package ch.hearc.stockarc.service;

import ch.hearc.stockarc.model.User;

public interface IUserService {

    void save(User user);

    User findByName(String name);

    User findByEmail(String email);

    void createPasswordResetTokenForUser(final User user, final String token);

    void changeUserPassword(final User user, final String password);

}