

//Класс для распечатки отчета через библиотеку itext
/*package org.example;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

public class PdfGenerator {
    public void generatePdf(String path, User user) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();

            document.add(new Paragraph(" Отчет о состоянии финансов "));
            document.add(new Paragraph("Общий доход: " + user.getWallet().getTotalIncome()));

            document.add(new Paragraph("Общий доход по категориям:"));
            for (Map.Entry<String, Double> entry : user.getWallet().getIncomes().entrySet()) {
                document.add(new Paragraph("Категория: " + entry.getKey() + ", Сумма: " + entry.getValue()));
            }

            document.add(new Paragraph("Общие расходы: " + user.getWallet().getTotalExpense()));
            document.add(new Paragraph("Бюджет по категориям:"));
            for (String category : user.getWallet().getBudgets().keySet()) {
                double totalExpense = user.getWallet().getExpenses().getOrDefault(category, 0.0);
                double budget = user.getWallet().getBudgets().get(category);
                double remainingBudget = budget - totalExpense;
                document.add(new Paragraph(category + ": " + totalExpense + ", Оставшийся бюджет: " + remainingBudget));
            }

            document.add(new Paragraph(" Отчет окончен "));
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}*/


