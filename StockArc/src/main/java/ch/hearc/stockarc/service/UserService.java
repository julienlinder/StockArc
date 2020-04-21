package ch.hearc.stockarc.service;

import ch.hearc.stockarc.model.User;

public interface UserService {
    void save(User user);

    User findByName(String name);

}