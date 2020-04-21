package ch.hearc.stockarc.service;

public interface SecurityService {
    String findLoggedInName();

    void autoLogin(String name, String password);
}