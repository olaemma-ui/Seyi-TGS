import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import java.awt.MenuItem;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Action extends Control implements Inter{

    Action(){
        listFiles();
        for(JCheckBox x:checkBoxes)
            x.addActionListener(this);
        button.addActionListener(this);
        for(Button x:vtSouthButton)
            x.addActionListener(this);
        dialogButton.addActionListener(this);
        tableSouthButton.addActionListener(this);
        validateTable.addMouseListener(this);
        daysCombo.addActionListener(this);
        saveButton.addActionListener(this);
        for(MenuItem x:file)
            x.addActionListener(this);
        listJ.addListSelectionListener(this);
        listJ.addMouseListener(this);
    }

    String text="hello world";
    CardLayout tableViewCrd=(CardLayout) tableView[0].getLayout();
    CardLayout panel1Crd=(CardLayout) panel[1].getLayout();
    CardLayout panel0Crd=(CardLayout) panel[0].getLayout();

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==button){
//            dialog.setVisible(true);
            JOptionPane.showMessageDialog(frame,dialog,"changes",JOptionPane.DEFAULT_OPTION);
        }
        if(e.getSource()==vtSouthButton[1]){
            apply();
            getData();
            button.setVisible(false);
            tableViewCrd.show(tableView[0],"table_view");
        }else if(e.getSource()==vtSouthButton[0]){
//            tableViewCrd
            button.setVisible(false);
            tableViewCrd.show(tableView[0],"table_view");
        }else if(e.getSource()==vtSouthButton[2]){
            resetTable();
        }
        if(e.getSource()==tableSouthButton){
            button.setVisible(true);
            tableViewCrd.show(tableView[0],"Data_View");
        }
        if(e.getSource()==dialogButton){
            apply();
        }
        if(e.getSource()==daysCombo){

        }
        if(e.getSource()==saveButton){
            save();
        }
        if(e.getSource()==file[0]){
            panel1Crd.show(panel[1],"storageRoom1");
            panel0Crd.show(panel[0],"storageRoom0");
        }else if(e.getSource()==file[1]){
            panel1Crd.show(panel[1],"tableView0");
            panel0Crd.show(panel[0],"panel3");
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange()==1){

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getSource()==listJ && e.getClickCount()==2){
            int g=listJ.getSelectedIndex();
            openSavedTable(hm.get(g));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getSource()==validateTable) {
            if (validateTable.getEditingColumn() == 3) {
                int row= validateTable.getEditingRow();
                String data=validateTable.getModel().getValueAt(row,3).toString();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getSource() == listJ ){

        }
    }
}
