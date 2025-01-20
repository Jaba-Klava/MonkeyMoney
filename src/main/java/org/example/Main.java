package org.example;

import java.util.Scanner; // Импортируем класс Scanner для чтения ввода пользователя

// Основной класс приложения
public class Main {
    public static void main(String[] args) {
        FinanceManager financeManager = new FinanceManager(); // Создаем экземпляр менеджера финансов
        Scanner scanner = new Scanner(System.in); // Создаем объект Scanner для чтения ввода
        User currentUser = null; // Переменная для хранения текущего пользователя
        System.out.println();
        System.out.println("Добро пожаловать в приложение управления финансами!");
        System.out.println("Для использования приложения вводите в консоль номер нужной команды");
        System.out.println();

        // Основной цикл приложения
        label:
        while (true) {
            System.out.println("Введите номер нужной команды:\n1 - зарегистрироваться\n2 - войти в учетную запись\n3 - выход из приложения");
            String command = scanner.nextLine(); // Читаем команду от пользователя

            // Обработка команды регистрации
            switch (command) {
                case "1": {
                    System.out.println("Придумайте логин:");
                    String username = scanner.nextLine(); // Читаем логин
                    System.out.println("Придумайте пароль:");
                    String password = scanner.nextLine();
                    financeManager.registerUser(username, password); // Регистрируем пользователя
                    System.out.println("Пользователь зарегистрирован.");
                    break;
                }
                case "2": {
                    System.out.println("Введите логин:");
                    String username = scanner.nextLine(); // Читаем логин
                    System.out.println("Введите пароль:");
                    String password = scanner.nextLine(); // Читаем пароль
                    currentUser = financeManager.authenticateUser(username, password); // Аутентификация пользователя

                    // Проверка успешности входа
                    if (currentUser != null) {
                        System.out.println("Вход выполнен успешно.");
                        financeManager.manageFinances(currentUser, scanner);
                    } else {
                        System.out.println("Неверный логин или пароль.");
                    }
                    break;
                }
                case "3":
                    System.out.println("Выход из приложения.");
                    break label;
                default:
                    System.out.println("Неизвестная команда. Пожалуйста, попробуйте снова.");
                    break;
            }
        }
        scanner.close();
    }
}