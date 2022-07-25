import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.MenuShortcut;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.event.KeyEvent;

public class Gui extends Store{

    Gui(){
        savedTable=new JTable(tableData,tableColumItem);

        savedTableScroll=new JScrollPane(savedTable);

        listJ.setBounds(10,10,1000,1000);
        listJ.setFixedCellHeight(30);

        storageRoom[0].setLayout(null);
        storageRoom[0].setBackground(Color.white);
        storageRoom[0].add(listJ);

        storageRoom[1].setLayout(new BorderLayout());
        storageRoom[1].add(savedTableScroll, BorderLayout.CENTER);
        storageRoom[1].setBackground(Color.white);

        tableSouthButton.setBackground(Color.BLUE);
        tableSouthButton.setForeground(Color.white);
        saveButton.setForeground(Color.white);
        saveButton.setBackground(Color.blue);

        tableSouth.setPreferredSize(new Dimension(0,50));
        FlowLayout flowLayout=new FlowLayout();
        flowLayout.setHgap(40);
        tableSouth.setLayout(flowLayout);
        tableSouth.setBackground(Color.blue);
        tableSouth.add(tableSouthButton);
        tableSouth.add(saveButton);

        tableView[2].setLayout(new BorderLayout());
        tableView[2].setBackground(Color.blue);

        tableView[2].add(tableSouth,BorderLayout.SOUTH);
        for(int a=0;a<vtSouthButton.length;a++){
            vtSouthButton[a].setBackground(Color.BLUE);
            vtPane.add(vtSouthButton[a]);
        }

        //working on dialogs///////////////////////////////////////////////////////////////////////////////////////////////
        dialogButton.setBounds(300,210,80,20);
        dialogButton.setBackground(Color.BLUE);
        dialogButton.setForeground(Color.white);
        dialog.add(dialogButton);

        seperator.setBounds(0,200,400,2);
        seperator.setForeground(Color.white);
        dialog.add(seperator);

        xx=20;
        y=120;
        for(int a=0;a< checkBoxes.length;a++){
            checkBoxes[a]=new JCheckBox();
            checkBoxes[a].setText(checkBoxesItem[a]);
            checkBoxes[a].setBounds(xx,y,100,20);
            checkBoxes[a].setForeground(Color.white);
            checkBoxes[a].setBackground(Color.blue);
            y+=17;
            if(a==3){
                xx+=100;
                y=120;
            }
            dialog.add(checkBoxes[a]);
        }
    int timer=00;
    String strinTime="00";
    int secTimer=15;
        for(int a=1;a<24;a++){
            for(int b=0;b<7;b++){
                if(b!=2){
                    for(int c=0;c<4;c++) {
                        dialogComboBox[b].addItem(a + ":" + strinTime);
                        timer+=15;
                        strinTime=String.valueOf(timer);
                    }
                    timer=00;
                    strinTime=timer+"0";
                }
            }
            dialogComboBox[7].addItem(a);
        }
        for(int a=0;a<10;a++){
            dialogComboBox[2].addItem(secTimer);
            secTimer+=15;
        }

        xx=20;
        y=65;
        for(int a=0;a< dialogComboBox.length;a++){
            dialogComboBox[a].setBounds(xx,y,60,20);
            dialog.add(dialogComboBox[a]);
            if(a<1) {
                xx += 100;
            } else if(a==1){
                xx+=150;
                y=65;
            }
        }

        xx=50;
        y=20;
        int width=200;
        for(int a=0;a<dialogLabel.length;a++){
            dialogLabel[a].setBounds(xx,y,width,20);
            if(a==1 || a==7 || a==9 || a==11){
                xx=20;
                width=100;
            }
            if(a==0){
                xx+=200;
            }else if(a==1){
                y=100;
            }else if(a==2){
                    xx=120;
                    y=45;
            }
            dialog.add(dialogLabel[a]);
        }

        xx=20;
        y=20;
        for(int a=0;a< cb.length;a++){
            cb[a]=new JCheckBox();
            cb[a].setText(cbi[a]);
            cb[a].setBounds(xx,y,100,20);
            y+=17;
            if(a==3){
                xx+=100;
                y=270;
            }
            select.add(cb[a]);
        }

        select.setSize(400,450);
        select.setLayout(new FlowLayout());
        button.setBounds(160,250,100,20);
        button.setText("Set Data");
        dialog.setSize(400,240);
        dialog.setLayout(null);
        dialog.setBackground(Color.blue);
        dialog.setForeground(Color.white);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        y=10;
        xx=10;

        for(int a=0;a< TableViewLeftDataLabel.length;a++){
            TableViewLeftDataLabel[a].setBounds(xx,y,250,20);
            TableViewLeftDataLabel[a].setForeground(Color.white);
                y+=80;
        }

        xx=20;
        y=30;
        for(int a=0;a< TableViewLeftDataTextField.length;a++){
            TableViewLeftDataTextField[a].setBounds(xx,y,200,40);
            TableViewLeftDataTextField[a].setLineWrap(true);
                y+=80;
        }

        daysCombo.setModel(new DefaultComboBoxModel(selection));

        y=290;
        xx=30;

        int counter=0,b=0;

        for(int a=0;a<120;a++){
            vtdata[counter][b]="";
            if(b==2){
                b=0;
            }
            b++;
        }

        table=new JTable(tableData,tableColumItem);

        tableScroll=new JScrollPane(table);
        tableView[2].add(tableScroll,BorderLayout.CENTER);

        validateTable=new JTable(vtdata,vtColumn);
        validateTable.setRowSelectionAllowed(false);
        validateTable.setBackground(Color.white);
        counter=0;
        b=0;

        vtPane.setPreferredSize(new Dimension(0,50));
        vtPane.setLayout(new FlowLayout());
        vtPane.setBackground(Color.blue);
        vtPane.setForeground(Color.white);

        validateTable.setRowHeight(30);
        TableColumn col=validateTable.getColumnModel().getColumn(2);
        col.setCellEditor(new DefaultCellEditor(daysCombo));
        scroll=new JScrollPane(validateTable);
        scroll.setForeground(Color.blue);
        scroll.setBackground(Color.blue);
        tableView[1].setLayout(new BorderLayout());
        tableView[1].setBackground(Color.blue);
        tableView[1].add(scroll,BorderLayout.CENTER);
        tableView[1].add(vtPane,BorderLayout.SOUTH);

        DefaultCellEditor single=new DefaultCellEditor(new JTextField());
        single.setClickCountToStart(1);
        for(int a=0;a<validateTable.getColumnCount();a++){
            validateTable.setDefaultEditor(validateTable.getColumnClass(a),single);
            System.out.println(a);
        }

        tableView[0].setLayout(new CardLayout());
        tableView[0].add(tableView[1],"Data_View");
        tableView[0].add(tableView[2],"table_view");

        y=1;
        xx=170;

        for(int a=0;a<frontLabel.length;a++){
            if(a==0){
                frontLabel[a].setBounds(175,y,150,20);
                y=50;
            }else{
                frontLabel[a].setBounds(xx,y,70,70);
                frontLabel[a].setBorder(BorderFactory.createLineBorder(Color.black,2));
                xx+=100;
                if(a==2){
                    xx=170;
                    y=150;
                }
            }
        }

        pane.setLayout(null);
        pane.setBounds(150,100,500,300);
        for(JLabel x: frontLabel)
            pane.add(x);

        centerView[0].setLayout(null);
        centerView[0].add(pane);

        panel[1].setLayout(new CardLayout());
        panel[1].add(tableView[0], "tableView0");
        panel[1].add(storageRoom[1], "storageRoom1");

        panel[2].setBackground(Color.blue);
        panel[2].setPreferredSize(new Dimension(0,25));

        panel[3].setBackground(Color.blue);
        panel[3].setLayout(null);
        for(JLabel x:TableViewLeftDataLabel)
            panel[3].add(x);
        for(JTextArea x:TableViewLeftDataTextField)
            panel[3].add(x);
        panel[3].add(button);

        panel[0].setBackground(Color.red);
        panel[0].setPreferredSize(new Dimension(300,0));
        panel[0].setMinimumSize(new Dimension(200,0));
        panel[0].setLayout(new CardLayout());
        panel[0].add(panel[3], "panel3");
        panel[0].add(storageRoom[0],"storageRoom0");

        file[0].setShortcut(new MenuShortcut(KeyEvent.VK_F,true));
        file[1].setShortcut(new MenuShortcut(KeyEvent.VK_F,false));

        for(MenuItem x:file) {
            menu[0].addSeparator();
            menu[0].add(x);
        }

        for(Menu x: menu)
            menuBar.add(x);

        frame.setSize(500,500);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel[0],BorderLayout.WEST);
        frame.add(panel[1],BorderLayout.CENTER);
        frame.add(panel[2],BorderLayout.NORTH);
        frame.setMenuBar(menuBar);
        frame.setVisible(true);
    }
}
