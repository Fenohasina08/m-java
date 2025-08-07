import lombok.*;

import java.time.LocalDate;
@Getter

public class Expense {
    private String label;
    private double amount;
    private LocalDate date;
    public Expense(String label, double amount, LocalDate date) {
        this.label = label;
        this.amount = amount;
        this.date = date;
    }
    public boolean isLargeExpense() {
        return amount > 100.0;
    }

    @Override
    public String toString() {
        return "Expense{" + ", amount=" + amount + '}';
    }
}
