import java.time.LocalDate;

public class RecurringExpense extends Expense {
 private Recurrence recurrence;
 public RecurringExpense(String label, double amount, LocalDate date,Recurrence recurrence) {
super(label, amount, date);
this.recurrence = recurrence;
 }


 @Override
 public String toString() {
  return "RecurringExpense [recurrence=" + recurrence + ", getLabel()=" + getLabel() + ", getAmount()="
          + getAmount() + ", getDate()=" + getDate() + "]";
 }
}
