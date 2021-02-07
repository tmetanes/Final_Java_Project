import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Frames extends JFrame {
    JButton shopBtn, stockBtn, backBtn;
    JTextField shopTxt, stockTxt;
    Choice choStock, choShop,remChoice,choSort;
    JTextArea reportStock, reportShop;
    JFrame stockWin, shopWin, stockTableWin;
    Operator op;


    //***** Main Window ***************
    public Frames(Operator op) {
        this.op = op;


// ********* Frame *******
        setTitle("Cell Shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500,300,600,300);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setUndecorated(true);  // F11 view

        JLabel CellIcon =new JLabel(new ImageIcon("C:\\Users\\tmeta\\Desktop\\Zefat Collage\\Year 2\\Java Malek\\ProjectVectorCopy\\src\\Badging.jpg"));
        CellIcon.setBounds(575, 300, 315, 310);
        setVisible(true);
        setLayout(null);


        welLabel();
        setButtonShop();
        setButtonStock();
        setButtonBack();
        add(CellIcon);

// SHOP WINDOW *********
        shopWindow();

// STOCK WINDOW *********
        stockWindow();

    }// END FRAMES !

    public void welLabel() {
        JLabel wel = new JLabel("Welcome to Cell-Shop");
        wel.setFont((wel.getFont().deriveFont(24.0f)));
        wel.setBounds(600, 110, 350, 100);
        //wel.setBounds(120, 10, 350, 100);
        add(wel);

        JLabel trademark = new JLabel("© 2021 Shani and Tamer");
        trademark.setFont((trademark.getFont().deriveFont(12.0f)));
        trademark.setBounds(650, 730, 250, 100);
        add(trademark);
    }

    public void setButtonShop() {
        shopBtn = new JButton("Shop");
        shopBtn.setBounds(480+80, 100+100, 100, 50);
        add(shopBtn);

        shopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shopBtn.setBackground(Color.green);
                stockBtn.setBackground(null);
                if (shopWin.isVisible()) {
                    shopWin.dispose();
                } else {
                    shopWin.setVisible(true);
                }
            }
        });
    }

    public void setButtonBack() {
        backBtn = new JButton("Back");
        backBtn.setBounds(480+210, 100+105, 80, 40);
        backBtn.setBackground(Color.red);
        add(backBtn);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shopBtn.setBackground(null);
                stockBtn.setBackground(null);
                stockWin.dispose();
                shopWin.dispose();
            }
        });
    }

    public void setButtonStock() {
        stockBtn = new JButton("Stock");
        stockBtn.setBounds(480+320, 100+100, 100, 50);
        add(stockBtn);

        stockBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stockBtn.setBackground(Color.green);
                shopBtn.setBackground(null);
                if (stockWin.isVisible()) {
                    stockWin.dispose();
                } else {
                    stockWin.setVisible(true);
                }
            }
        });

    }
// ***** END Main Window *****

// ***** Stock Window START *************
    public void stockWindow() {
        stockWin = new JFrame();
        stockWin.setBounds(850, 210, 470, 320);

        addOneBtn();
        removeOneBtn();
        updateBtn();
        searchBtn();
        printBtn();
        setStockTxt();
        setStockChoice();
        setStockTextArea();
        TableBtn();


        Container cont = new Container();
        stockWin.add(cont);
        cont.add(choStock);
        cont.add(stockTxt);
        stockWin.setVisible(false);
    }

    public void addOneBtn() {
        JButton addBtn = new JButton("Add 1");
        addBtn.setBounds(10, 10, 100, 40);
        stockWin.add(addBtn);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                op.addPhone(choStock.getSelectedIndex());
                reportStock.setVisible(false);
                stockTxt.setText(" Update / Search ");
                stockTxt.setBackground(Color.white);
            }
        });
    }

    public void removeOneBtn() {
        JButton removeBtn = new JButton("Remove 1");
        removeBtn.setBounds(120, 10, 100, 40);
        stockWin.add(removeBtn);
        removeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                op.remPhone(choStock.getSelectedIndex());
                reportStock.setVisible(false);
                if (op.getPh().elementAt(choStock.getSelectedIndex()).getPstock() <= 0) {
                    JOptionPane.showMessageDialog(null, "Stock is Empty");
                    stockTxt.setText("Stock is Empty");
                    stockTxt.setBackground(Color.red);
                }
            }
        });
    }

    public void updateBtn() {
        JButton updateBtn = new JButton("Update");
        updateBtn.setBounds(230, 10, 100, 40);
        stockWin.add(updateBtn);
        updateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                stockTxt.setBackground(Color.white);
                String updateTxt;
                reportStock.setVisible(false);
                updateTxt = stockTxt.getText().trim();
                if(op.isNumeric(updateTxt))
                {
                    op.update(choStock.getSelectedIndex(), Integer.parseInt(updateTxt));
                    JOptionPane.showMessageDialog(null,"Stock Updated");
                }
                else
                    JOptionPane.showMessageDialog(null,"Must be a Number");
            }

        });
    }

    public void searchBtn() {
        JButton searchBtn = new JButton("Search");
        searchBtn.setBounds(340, 10, 100, 40);
        stockWin.add(searchBtn);
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                reportStock.setVisible(true);
                op.search(stockTxt.getText().trim());
                reportStock.setText(op.getPhoneNames());
            }
        });

    }

    public void setStockChoice() {   //Jchoice
        choStock = new Choice();
        choStock.setBounds(10, 60, 120, 40);
        //choStock.add("Select Phone");
        choStock.add("Galaxy S20");
        choStock.add("Galaxy S20+");
        choStock.add("Note 20");
        choStock.add("Note 20 Ultra");
        choStock.setVisible(true);
    }

    public void printBtn() {
        JButton printBtn = new JButton("Show");
        printBtn.setBounds(120, 60, 100, 40);
        stockWin.add(printBtn);
        printBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                reportStock.setVisible(true);
                reportStock.setText(op.print());
            }
        });
    }

    public void TableBtn() {

        JButton TableBtn = new JButton("Table");
        TableBtn.setBounds(230, 60, 100, 40);
        stockWin.add(TableBtn);
        TableBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                    //stockTable(); // hay asleye
                    //stockTableWin.setVisible(true);

                    stockTableWin();
                    sorter(0);
                    stockTableWin.setVisible(true);
            }
        });
    }

    public void setStockTxt() {
        stockTxt = new JTextField(" Update / Search ");
        stockTxt.setBounds(340, 60, 100, 25);
    }

    public void setStockTextArea() {
        reportStock = new JTextArea();
        reportStock.setBounds(50, 120, 350, 150);
        reportStock.setVisible(false);
        stockWin.add(reportStock);
    }

/* ************* TABLE WINDOW *************/
    public void stockTableWin() {
        stockTableWin = new JFrame();
        stockTableWin.setVisible(false);
        stockTableWin.setBounds(150, 200, 470, 320);
        extractBtn();
        sortByCho();
        sortBtn();
    }

    public void extractBtn() {
        String report = op.print();
        JButton extract = new JButton("Extract Data");
        extract.setBounds(50, 200, 150, 50);
        stockTableWin.add(extract);
        extract.setVisible(true);
        extract.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                op.extractData(report);
                JOptionPane.showMessageDialog(null, "file Created");
            }
        });
    }

    public void sortBtn() {
        JButton sortBtn = new JButton("Sort");
        sortBtn.setBounds(260, 200, 150, 50);
        stockTableWin.add(sortBtn);
        sortBtn.setVisible(true);
        sortBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                int Selection = choSort.getSelectedIndex();
                sorter(Selection);
            }
        });
    }

    public void sortByCho() {
        choSort = new Choice();
        choSort.setBounds(200, 150, 120, 40);
        choSort.add("0.By Name");
        choSort.add("1.By Price");
        choSort.add("2.By Stock");
        stockTableWin.add(choSort);
        choSort.setVisible(true);
    }

    public void sorter(int sortIndex) {
        int order []= new int[4];  // sortKEY
        switch (sortIndex)
        {
            case 0:  // by name
                //SORT NAMES
                String[] names = {op.getPh().elementAt(0).getPname(),op.getPh().elementAt(1).getPname(),op.getPh().elementAt(2).getPname(),op.getPh().elementAt(3).getPname()};
                Arrays.sort(names);
                for(int i = 0; i< names.length; i++)
                {
                    for(int j=0;j< names.length;j++)
                    {
                        if(names[i]== op.getPh().elementAt(j).getPname())
                            order[i] = j;
                    }
                }
                break;
            case 1:  // by Price
                int[] price = {op.getPh().elementAt(0).getPprice(),op.getPh().elementAt(1).getPprice(),op.getPh().elementAt(2).getPprice(),op.getPh().elementAt(3).getPprice()};
                Arrays.sort(price);
                for(int i = 0; i< price.length; i++)
                {
                    for(int j=0;j< price.length;j++)
                    {
                        if(price[i]== op.getPh().elementAt(j).getPprice())
                            order[i] = j;
                    }
                }
                break;
            case 2:  // by stock
                int[] stock = {op.getPh().elementAt(0).getPstock(),op.getPh().elementAt(1).getPstock(),op.getPh().elementAt(2).getPstock(),op.getPh().elementAt(3).getPstock()};
                Arrays.sort(stock);
                for(int i = 0; i< stock.length; i++)
                {
                    for(int j=0;j< stock.length;j++)
                    {
                        if(stock[i]== op.getPh().elementAt(j).getPstock())
                        {
                            int PhoneNr = j;
                                boolean result = IntStream.of(order).anyMatch(x -> x == PhoneNr);
                                if (!result)
                                {
                                    order[i] = j;
                                }
                        }
                    }
                }
                break;
        }
        stockTable(order);
    }

    public void stockTable(int[] order) {
        String[] title = {"Name", "Price", "Stock"};
        Object data[][] = {{"Name", "Price", "Stock"}};

        DefaultTableModel newTable = new DefaultTableModel(data,title);
        JTable table = new JTable(newTable);

        table.setAutoCreateRowSorter(true);

        newTable.getDataVector().removeAllElements();
        newTable.setRowCount(0);
        table.setVisible(false);
        Object[] line;
        for(int i =0;i<order.length;i++){
            line = new Object[]{op.getPh().elementAt(order[i]).getPname()
                    ,Integer.toString(op.getPh().elementAt(order[i]).getPprice())
                    ,Integer.toString(op.getPh().elementAt(order[i]).getPstock())};
            newTable.addRow(line);
            line = null;  // 3ala el fade betsa3edesh

        }
        table.setVisible(true);
        stockTableWin.add(table);

    }  // 3ende moshekle bel TABLE bte3malesh REWRITE bedalha t7ot kaman table 3ala yamenha


/* ***** END Stock Window *******/


/* **** Shop Window **********/

    public void shopWindow() {
        shopWin = new JFrame();
        shopWin.setBounds(150, 210, 470, 320);

        shopBtn();
        setShopChoice();
        setShopText();
        shopLabel();
        setShopTextArea();
        chartBtn();
        chartRemoveBtn();
        remChoice();
        clearAll();

        Container shopCont = new Container();
        shopWin.add(shopCont);
        shopCont.add(shopTxt);
        shopCont.add(reportShop);
        shopCont.add(remChoice);
    }

    public void setShopText() {
        shopTxt = new JTextField("0");
        shopTxt.setVisible(true);
        shopTxt.setBounds(180, 50, 100, 25);

    }

    public void shopLabel() {
        JLabel shopLabel = new JLabel("Enter Cash");
        shopLabel.setBounds(180, 20, 120, 40);
        shopWin.add(shopLabel);
    }

    public void setShopChoice() {
        choShop = new Choice();
        choShop.add("Galaxy S20");
        choShop.add("Galaxy S20+");
        choShop.add("Note 20");
        choShop.add("Note 20 Ultra");
        choShop.setBounds(50, 50, 120, 40);
        shopWin.add(choShop);
    }

    public void shopBtn() {   // lazim a3mal checkout lal Chart KEEEF !?

        JButton shopBtn = new JButton("Shop");
        shopBtn.setBounds(180,80,100,40);
        shopWin.add(shopBtn);
        shopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int chartMoney = op.getChartTotal();
                // **** Single bought *******
                    if(chartMoney ==0)
                    {
                        op.sell(choShop.getSelectedIndex(), Integer.parseInt(shopTxt.getText()));
                        if(op.getBuyTest()!=0)
                        {
                            reportShop.setText(op.getCashBack());
                            reportShop.setVisible(true);
                        }
                // **** Multi bought *******
                    }
                    if(op.getChart().size() != 0)
                    {
                        op.chartSell(Integer.parseInt(shopTxt.getText()));
                        if(op.getBuyTest()!=0)
                        {
                            reportShop.setText(op.getCashBack());
                            reportShop.setVisible(true);
                        }
                    }
            }
        });

    }

    public void setShopTextArea() {
        reportShop = new JTextArea();
        reportShop.setVisible(false);
        reportShop.setBounds(130, 130, 300, 150);

    }

    public void chartBtn() {
        JButton ChartBtn = new JButton("Add Chart");
        ChartBtn.setBounds(50,80,100,40);
        shopWin.add(ChartBtn);
        ChartBtn.setVisible(true);
        ChartBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                op.shopChart(choShop.getSelectedIndex());
                reportShop.setText(op.getChartList());
                reportShop.setVisible(true);
            }
        });
    }

    public void chartRemoveBtn() {
        JButton charRemoveBtn = new JButton("Remove");
        charRemoveBtn.setBounds( 310, 80, 100, 40);
        shopWin.add(charRemoveBtn);
        charRemoveBtn.setVisible(true);
        charRemoveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(op.getChart().size()!=0)
                {
                    op.removeChart(remChoice.getSelectedIndex());
                    reportShop.setText(op.getChartList());
                    reportShop.setVisible(true);
                }
                else reportShop.setVisible(false);

            }
        });
    }

    public void remChoice() {
        remChoice = new Choice();
        remChoice.add("0");
        remChoice.add("1");
        remChoice.add("2");
        remChoice.add("3");
        remChoice.add("4");
        remChoice.setBounds(310, 50, 120, 40);

    }  // hay mish 3agbetne lazim yekon fe 7al tani a7san !!!

    public void clearAll() {
        JButton clearBtn = new JButton("Clear");
        clearBtn.setBounds(50,130,70,40);
        clearBtn.setBackground(Color.red);
        shopWin.add(clearBtn);
        clearBtn.setVisible(true);
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                    if(op.checkout() ==1 && op.getChart().size() >0)
                    {
                        op.clearAll();
                        JOptionPane.showMessageDialog(null,"All Cleared!");
                        reportShop.setVisible(false);
                    }else
                        JOptionPane.showMessageDialog(null,"Aborted or Chart is Empty!");
            }
        });
    }


// *** END Shop Window *********

// End Frames ! ****
}



// **** NICE WAY TO MAKE TABLE ! ********
   /* String[] title = {"Name", "Price", "Stock"};
                Object[][]data ={{"Name", "Price", "Stock"}};
                DefaultTableModel dt = new DefaultTableModel(data,title);


                JTable test = new JTable(dt);
                Vector v;
                for(int i =0; i<op.ph.size();i++)
                {
                    v = new Vector();
                    v.add(op.ph.elementAt(i).getPname());
                    v.add(op.ph.elementAt(i).getPprice());
                    v.add(op.ph.elementAt(i).getPstock());
                    dt.addRow(v);
                    //dt.insertRow(0,v);
                }
                stockTableWin.add(test);
                stockTableWin.setVisible(true);*/

// Original Table
    /*
    public void stockTable() {

        String[] title = {"Name", "Price", "Stock"};
        Object data[][] = {{"Name", "Price", "Stock"}};

        DefaultTableModel newTable = new DefaultTableModel(data,title);
        JTable table = new JTable(newTable);
        table.setAutoCreateRowSorter(true);

        Object[] line;
        for(int i =0;i<op.getPh().size();i++){
            line = new Object[]{op.getPh().elementAt(i).getPname()
                    ,Integer.toString(op.getPh().elementAt(i).getPprice())
                    ,Integer.toString(op.getPh().elementAt(i).getPstock())};
            newTable.addRow(line);
        }
        stockTableWin.add(table);
    } */