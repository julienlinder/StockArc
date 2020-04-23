package ch.hearc.stockarc.service;

public interface ISecurityService {
    String findLoggedInName();

    void autoLogin(String name, String password);
}