import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.io.FileWriter;

public class Operator {
    private Vector<Phone> ph;
    private Vector<Cash> ca;
    private ArrayList<Chart> chart;
    private String phoneNames;
    private String cashBack;
    private String chartList;
    private int chartTotal;
    private int choiceCount = 0;
    private int  buy = 0;

    public Operator() {
        Vector<Phone> galaxy = new Vector<>();
        ph = galaxy;
        ph.add(new GalaxyS20(0, "Galaxy S20", 3000, 5,128));
        ph.add(new GalaxyS20P(1, "Galaxy S20 Plus", 3500, 5,true));
        ph.add(new Note20(2, "Note 20", 4000, 5,false));
        ph.add(new Note20P(3, "Note 20 Plus", 4500, 5,true));


        Vector<Cash> cash = new Vector<>();
        ca = cash;
        ca.add(new Cash(300));

        chart = new ArrayList();
    } // Vectors !

    public void addPhone(int cho) {
        ph.elementAt(cho).setPstock(ph.elementAt(cho).getPstock() + 1);
    }

    public void remPhone(int cho) {
        ph.elementAt(cho).setPstock(ph.elementAt(cho).getPstock() - 1);
    }

    public String print() {
        return ph.toString();
    }

    public void update(int cho, int count) {
        ph.elementAt(cho).setPstock(count);
    }

    public void search(String txt) {
        phoneNames = "";
        int count = 0;
        for (Phone p : ph) {
            if (p.getPname().contains(txt)) {
                phoneNames = phoneNames + " \n " + (p.getPname());  // fe eshe eno mish 3am beb3ed el S 3an 20 fa ba2daresh ala2e s20
                count++;
            }
        }
        phoneNames = phoneNames + ("\nthere are :" + count + " phones");
    }

    public void sell(int cho, int money) {
        buy =0;
        if (money >= ph.elementAt(cho).getPprice())
        {
            buy =1;
            int ret;
            cashBack = "";   // mish 3am bentbeh eza el mlay fade
            if (checkout() == 1) {
                for (Phone p : ph) {
                    if (p.getPname().equals(ph.elementAt(cho).getPname()))  // equals instead of ==
                    {
                        if (p.getPstock() >= 1) {
                            p.setPstock(p.getPstock() - 1);
                            ca.elementAt(0).setTotal(ca.elementAt(0).getTotal() + p.getPprice());
                            ret = money - p.getPprice();
                            cashBack = cashBack + "give customer " + ret + " back\n" + "u have " + ca.firstElement().getTotal() + " in the case";
                        } else JOptionPane.showMessageDialog(null, "Out of Stock!! Choose Different Phone");
                    }
                }
            }else JOptionPane.showMessageDialog(null, "Aborted !");
        }else JOptionPane.showMessageDialog(null, "Not enough Money");
    }

    public void chartSell(int money) {
        buy =0;
        if (money >= chartTotal)
        {
            buy=1;
            int ret = 0;
            cashBack = "";
            if (checkout() == 1)
            {
                for (int i = 0; i < chart.size(); i++) {
                    for (Phone p : ph) {
                        if (p.getPname().equals(chart.get(i).productName))  // equals instead of ==
                        {
                            if (p.getPstock() >= 1) {
                                p.setPstock(p.getPstock() - 1);
                                ca.elementAt(0).setTotal(ca.elementAt(0).getTotal() + p.getPprice());
                                chart.remove(chart.get(i));
                                chartTotal = chartTotal - p.getPprice();
                                ret = money - p.getPprice();
                                money = money - p.getPprice();

                            } else JOptionPane.showMessageDialog(null, "Out of Stock!! Choose Different Phone");
                        }
                    }
                }
                cashBack = cashBack + "give customer " + ret + " back\n" + "u have " + ca.firstElement().getTotal() + " in the case";
            }else JOptionPane.showMessageDialog(null, "Aborted !");
        }else JOptionPane.showMessageDialog(null, "Not enough Money");
    }

    public int checkout() {
        int confirm = JOptionPane.showConfirmDialog(null, "are u sure?", "confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            return 1;
        } else
            return 0;
    }

    public void extractData(String report) {
        try {
            FileWriter rw = new FileWriter("src\\stockData.txt");
            rw.write("your stock is:\n");
            rw.write(report);  // need to print updated STOCK !!
            rw.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void shopChart(int charCho) {
        chartList = "Your Chart is \n";
        String chartName;
        int chartPrice;
        chartName = ph.elementAt(charCho).getPname();
        chartPrice = ph.elementAt(charCho).getPprice();

        if (chart.size() < 5) {
            chart.add(new Chart(chartName, chartPrice));
            printChart();
        } else {
            JOptionPane.showMessageDialog(null, "Cant Buy more then 5 items at once");
            printChart();
        }
    }

    public void printChart() {
        chartList = "Your Chart is \n";
        chartTotal = 0;
        for (int i = 0; i < chart.size(); i++) {
            chartList = chartList + i + "." + " Product: " + chart.get(i).productName + " Price: " + chart.get(i).productPrice + "\n";
            chartTotal = chartTotal + chart.get(i).productPrice;
            choiceCount++;
        }
        chartList = chartList + "\n" + chartTotal + " NIS in Total";
    }

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void removeChart(int remNum) {
        chart.remove(remNum);
        printChart();
    }

    public void clearAll() {
        chart.clear();

    }

    public String getChartList() {
        return chartList;
    }

    public String getCashBack() {
        return cashBack;
    }

    public String getPhoneNames() {
        return phoneNames;
    }

    public Vector<Phone> getPh() {
        return ph;
    }

    public int getChartTotal() {
        return chartTotal;
    }

    public ArrayList<Chart> getChart() {
        return chart;
    }

    public int getBuyTest() {
        return buy;
    }

}
