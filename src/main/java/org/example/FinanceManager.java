package org.example;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Класс для управления финансами
public class FinanceManager {
    private final Map<String, User> users; // Хранит пользователей

    // Конструктор
    public FinanceManager() {
        users = new HashMap<>();
    }

    // Метод для регистрации пользователя
    public void registerUser(String username, String password) {
        users.put(username, new User(username, password));
    }

    // Метод для авторизации пользователя
    public User authenticateUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user; // Возвращаем пользователя, если пароль верный
        }
        return null; // Если пользователь не найден или пароль неверный
    }

    public void manageFinances(User user, Scanner scanner) {
        label:
        while (true) {
            System.out.println("Введите команду для управления финансами:\n" +
                    "1 - указать свой доход\n" +
                    "2 - указать свои расходы\n" +
                    "3 - установить бюджет\n" +
                    "4 - показать информацию\n" +
                    "5 - получить отчет\n" +
                    "6 - сменить пользователя");
            String command = scanner.nextLine();

            switch (command) {
                case "6":  // Выход
                    System.out.println("Вы вышли из своей учетной записи.");
                    break label; // Выход из управления финансами
                case "1": { // Указать свой доход
                    System.out.println("Введите категорию дохода:");
                    String category = scanner.nextLine();
                    System.out.println("Введите сумму дохода:");
                    try {
                        double amount = Double.parseDouble(scanner.nextLine());
                        user.getWallet().addIncome(category, amount);
                        System.out.println("Доход добавлен.");
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: введите корректное число для суммы.");
                    }
                    break;
                }
                case "2": { // Указать свои расходы
                    System.out.println("Введите категорию расхода:");
                    String category = scanner.nextLine();
                    System.out.println("Введите сумму расхода:");
                    try {
                        double amount = Double.parseDouble(scanner.nextLine());
                        user.getWallet().addExpense(category, amount);
                        System.out.println("Расход добавлен.");
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: введите корректное число для суммы.");
                    }
                    break;
                }
                case "3": { // Установить бюджет
                    for (String expenseCategory : user.getWallet().getExpenses().keySet()) {
                        System.out.println(expenseCategory); // Выводим каждую категорию расходов на экран
                    }
                    System.out.println("Выберите категорию бюджета из списка выше:");
                    String category = scanner.nextLine();

                    if (user.getWallet().getExpenses().containsKey(category)) {
                        System.out.println("Введите сумму бюджета:");
                        try {
                            double amount = Double.parseDouble(scanner.nextLine());
                            user.getWallet().setBudget(category, amount);
                            System.out.println("Бюджет установлен.");
                        } catch (NumberFormatException e) {
                            System.out.println("Ошибка: введите корректное число для суммы бюджета.");
                        }
                    } else {
                        System.out.println("Ошибка: указанная категория расходов не существует.");
                    }
                    break;
                }
                case "4":  // Показать информацию
                    System.out.println("Ваши доходы: " + user.getWallet().getIncomes());
                    System.out.println("Ваши расходы: " + user.getWallet().getExpenses());
                    System.out.println("Ваши бюджеты: " + user.getWallet().getBudgets());
                    System.out.println("Общий доход: " + user.getWallet().getTotalIncome());
                    System.out.println("Общий расход: " + user.getWallet().getTotalExpense());
                    break;
                case "5":  // Получить отчет о состоянии финансов
                    generateFinancialReport(user);
                    break;
                default:
                    System.out.println("Неверная команда. Пожалуйста, попробуйте снова.");
                    break;
            }
        }
    }

    private void generateFinancialReport(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("**** Отчет о состоянии финансов ****");
        System.out.println("Пользователь:" + user.getUsername());
        System.out.println("Общий доход: " + user.getWallet().getTotalIncome());

        System.out.println("Общий доход по категориям:");
        for (Map.Entry<String, Double> entry : user.getWallet().getIncomes().entrySet()) {
            System.out.println("Категория: " + entry.getKey() + ", Сумма: " + entry.getValue());
        }

        System.out.println("Общие расходы: " + user.getWallet().getTotalExpense());

        System.out.println("Бюджет по категориям:");
        for (String category : user.getWallet().getBudgets().keySet()) {
            double totalExpense = user.getWallet().getExpenses().getOrDefault(category, 0.0);
            double budget = user.getWallet().getBudgets().get(category);
            double remainingBudget = budget - totalExpense;
            System.out.println(category + ": " + totalExpense + ", Оставшийся бюджет: " + remainingBudget);
        }
        System.out.println("**** Отчет окончен ****");
        System.out.println();

        //Метод для автоматического вывода в печать отчета. Он работает, но вывод в ПДФ делает некорректно

        /*System.out.println("Распечатать отчет?(да/нет)");
        String command = scanner.nextLine();
        String path = "Финансовый_отчет.pdf"; // Здесь можно указать путь к PDF

        switch (command) {
            case "да":
                PdfGenerator pdfGenerator = new PdfGenerator();
                pdfGenerator.generatePdf(path, user);
                System.out.println("Отчет успешно распечатан.");
                try {
                    File pdfFile = new File(path);
                    if (pdfFile.exists()) {
                        // Открытие PDF файла
                        Desktop.getDesktop().browse(pdfFile.toURI());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "нет":
                System.out.println("Отчет не распечатан.");
                break;
            default:
                System.out.println("Неверная команда. Пожалуйста, введите 'да' или 'нет'.");
                break;
        }*/
    }
}