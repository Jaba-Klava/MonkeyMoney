package org.example;

import java.util.HashMap;
import java.util.Map;

// Класс для представления кошелька пользователя
public class Wallet {
    private Map<String, Double> incomes; // Доходы
    private Map<String, Double> expenses; // Расходы
    private Map<String, Double> budgets; // Бюджеты по категориям

    // Конструктор
    public Wallet() {
        incomes = new HashMap<>();
        expenses = new HashMap<>();
        budgets = new HashMap<>();
    }

    // Метод для добавления дохода
    public void addIncome(String category, double amount) {
        incomes.put(category, incomes.getOrDefault(category, 0.0) + amount);
    }

    // Метод для добавления расхода
    public void addExpense(String category, double amount) {
        expenses.put(category, expenses.getOrDefault(category, 0.0) + amount);
    }

    // Метод для установки бюджета
    public void setBudget(String category, double amount) {
        budgets.put(category, amount);
    }

    // Метод для получения общего дохода
    public double getTotalIncome() {
        return incomes.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    // Метод для получения общего расхода
    public double getTotalExpense() {
        return expenses.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    // Метод для получения бюджета по категориям
    public Map<String, Double> getBudgets() {
        return budgets;
    }

    // Метод для получения расходов по категориям
    public Map<String, Double> getExpenses() {
        return expenses;
    }

    public Map<String, Double> getIncomes() {
        return incomes;
    }
}