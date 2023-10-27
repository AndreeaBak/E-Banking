import java.io.Serializable;
import java.time.LocalDate;

public class Transaction implements Serializable {
    private int transactionNo = 0;
    private String type;
    private double amount;
    private LocalDate date;

    public Transaction(String type, double amount) {
        ++this.transactionNo;
        this.type = type;
        this.amount = amount;
        this.date = LocalDate.now();
    }

    public String toString() {
        return "Transaction\n {transactionNo=" + this.transactionNo + ", type='" + this.type + "', amount=" + this.amount + ", date=" + this.date + "}\n";
    }
}
