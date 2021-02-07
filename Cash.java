import java.util.Vector;

public class Cash {

    private int Pprice = 0;
    private int entryAmount = 300;
    private int paidMoney;
    private int total = 0;


    // ****************  CONSTRUCTOR  *************************//
    public Cash(int total) {
        this.total = total;
    }

    public int getPprice() {
        return Pprice;
    }

    public int getEntryAmount() {
        return entryAmount;
    }

    public int getPaidMoney() {
        return paidMoney;
    }

    public int getTotal() {
        return total;
    }

    public void setPprice(int pprice) {
        Pprice = pprice;
    }

    public void setEntryAmount(int entryAmount) {
        this.entryAmount = entryAmount;
    }

    public void setPaidMoney(int paidMoney) {
        this.paidMoney = paidMoney;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Cash{" +
                "Pprice=" + Pprice +
                ", entryAmount=" + entryAmount +
                ", amount=" + paidMoney +
                ", total=" + total +
                '}';
    }
}
