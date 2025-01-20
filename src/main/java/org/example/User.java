package org.example;

import java.util.ArrayList;
import java.util.List;

// Класс для представления пользователя
public class User {
    private String username; // Логин пользователя
    private String password; // Пароль пользователя
    private Wallet wallet; // Кошелек пользователя

    // Конструктор
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.wallet = new Wallet(); // Создаем новый кошелек при создании пользователя
    }

    // Геттеры
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Wallet getWallet() {
        return wallet;
    }
}
