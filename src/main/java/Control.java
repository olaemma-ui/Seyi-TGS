import javax.swing.JOptionPane;
import  javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Random;
import java.util.Vector;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.ArrayList;
import java.util.Iterator;

public class Control extends Gui{

    private Vector[] vec={new Vector(),new Vector(),new Vector(),new Vector()};
    private Vector[] period={new Vector<Integer>(),new Vector<Integer>(),new Vector<Integer>(),new Vector<Integer>()};
    private String[] data={"","","","","","","","","",""};
    private Vector<Integer> daysNumber=new Vector();
    private String[] map;
    private String[][] splitMap;
    private String[] lecturers;
    HashMap<Integer,String> rowMap=new HashMap<>();
    LinkedHashSet[] sets;
    int rowCounter=0,getRowCounterRef;
    ArrayList list=new ArrayList();
    int periodAddition=0;

    public void getData(){
        rowCounter=0;
        try {
            for (int a = 0; a < validateTable.getRowCount(); a++) {
                if (validateTable.getModel().getValueAt(a, 0) == null) {
                    break;
                }
                if (validateTable.getModel().getValueAt(0, 2) == null) {
                    validateTable.getModel().setValueAt("1", 0, 2);
                }
                if (validateTable.getModel().getValueAt(a, 2) == null) {
                    validateTable.getModel().setValueAt("1", a, 2);
                }
                vec[0].add(validateTable.getModel().getValueAt(a, 0));
                vec[1].add(validateTable.getModel().getValueAt(a, 1));
                vec[2].add(validateTable.getModel().getValueAt(a, 2));
                period[0].add(validateTable.getModel().getValueAt(a, 0));
                period[1].add(validateTable.getModel().getValueAt(a, 1));
                period[2].add(validateTable.getModel().getValueAt(a, 2));
                rowCounter++;
            }

            getRowCounterRef = 0;
            getRowCounterRef += rowCounter;
            lecturers = new String[rowCounter];

            rowMap.clear();
            list.clear();
            for (int a = 0; a < rowCounter; a++) {
                rowMap.put(a, String.valueOf(vec[0].get(a)));
                for (int b = 0; b < Integer.parseInt(String.valueOf(validateTable.getModel().getValueAt(a, 2))); b++) {
                    list.add(String.valueOf(a));
                }
                periodAddition += Integer.parseInt(String.valueOf(validateTable.getModel().getValueAt(a, 2)));
            }

            tableData();
        }catch (Exception e){
            for(int a=0;a< sets.length;a++){
                sets[a].clear();
            }
            e.printStackTrace();
            rowCounter=0;
            vec[0].clear();
            vec[1].clear();
            vec[2].clear();
            vec[3].clear();
            period[0].clear();
            period[1].clear();
            period[2].clear();
            period[3].clear();
            list.clear();
            periodAddition=0;
            rowCounter=0;
            getRowCounterRef=0;
            periodAddition=0;
            JOptionPane.showMessageDialog(null,"The system was unable to process the request, Make sure the data is structured well, you can check\" +\n" +
                    "\" the help page to learn more..");
        }
    }

    public void apply(){
        data[0]=dialogComboBox[0].getSelectedItem().toString();//Time per day FROM
        data[1]=dialogComboBox[1].getSelectedItem().toString();//Time per day TO
        data[2]=dialogComboBox[2].getSelectedItem().toString();//minute per period
        if(confirm[0].getState()==true) {
            data[3] = dialogComboBox[3].getSelectedItem().toString();//break 1 FROM
            data[4] = dialogComboBox[4].getSelectedItem().toString();//break 1 TO
            data[8] = dialogTextField[0].getText();
        }
        if(confirm[1].getState()==true) {
            data[5] = dialogComboBox[5].getSelectedItem().toString();//break 2 FROM
            data[6] = dialogComboBox[6].getSelectedItem().toString();//break 2 TO
            data[9] = dialogTextField[1].getText();
        }
        data[7]=dialogComboBox[7].getSelectedItem().toString();//number of weeks
        daysNumber.clear();
        for(int a=0;a< checkBoxesItem.length;a++){
            if(checkBoxes[a].isSelected()){
                daysNumber.add(a);
            }
        }

        String[] periodArray=new String[daysNumber.size()];
        for(int a=0;a<periodArray.length;a++){
            periodArray[a]= String.valueOf(a+1);
        }
        daysCombo.setModel(new DefaultComboBoxModel(periodArray));

        createAlgorithm();
    }

    public void createAlgorithm(){
        String interval;
        LocalTime from,to,dif;
        Vector<String> dur=new Vector();

        if(data[0].length()==4){
            from = LocalTime.parse("0"+data[0]);
        }else {
            from = LocalTime.parse(data[0]);
        }
        if(data[1].length()==4){
            to = LocalTime.parse("0"+data[1]);
        }else {
            to = LocalTime.parse(data[1]);
        }
        interval=data[2];
        int j=0;

        dif=from.plusMinutes(Integer.parseInt(interval));
        dur.add(from.toString());
        Duration duration=Duration.between(from,to);
        long minutes=duration.toMinutes();
        Duration minGet;
        int minGetNum=0;
        boolean fact=false;
        String mani=to.toString();
        if(from!=to && Integer.parseInt(to.toString().substring(0,to.toString().indexOf(':'))) >= Integer.parseInt(from.toString().substring(0,from.toString().indexOf(':')))) {
            dif=from;
            while (fact==false && minGetNum<minutes) {
                dif = from.plusMinutes(Integer.parseInt(interval));
                minGet=Duration.between(from,dif);
                minGetNum+=minGet.toMinutes();
                from=dif;
                if(minGetNum>minutes){
                    long difference=minGetNum-minutes;
                    dif=dif.minusMinutes(Integer.parseInt(String.valueOf(difference)));
                    dur.add(dif.toString());
                    break;
                }
                dur.add(dif.toString());
                String name=dif.toString();
                if(name.equals(mani)){
                    fact=true;
                }
                j++;
            }
        }

        map=new String[dur.size()-1];

        for(int a=0;a<map.length;a++){
            map[a]="";
        }

        int count=0,c=0;
        for(int a=0;a<map.length;a++){
            for(int b=0;b<2;b++) {
                map[a] += dur.get(count);
                    if(b==0) {
                        count++;
                        map[a]+="--";
                    }
            }
        }
    }

    public void tableData(){
        int groupCount=0;
        int lectLength=0;
        int periodNumber;
        boolean check=true;
        sets=new LinkedHashSet[daysNumber.size()];
        for(int a=0;a< sets.length;a++){
            sets[a]=new LinkedHashSet();
        }

        int freePeriod=rowMap.size();
        periodNumber=map.length* daysNumber.size();
        groupCount+=periodNumber*5;
        if(periodAddition>periodNumber){
            check=false;
        } else if(periodAddition<periodNumber){
            getRowCounterRef++;
            for(int a=0;a<periodNumber-periodAddition;a++){
                list.add(String.valueOf(freePeriod));
                rowMap.put(rowMap.size(),"--");
            }
        }

        if(check==true) {
            Random rand = new Random();
            for (int c = 0; c < daysNumber.size(); c++) {
                for (int d = 0; d < map.length; d++) {
                    int adder = 0 + rand.nextInt(getRowCounterRef);
                    if (list.contains(String.valueOf(adder)) && !sets[c].contains(adder)) {
                        sets[c].add(adder);
                        list.remove(String.valueOf(adder));
                    }
                }
                if (sets[c].size() != map.length) {
                    int length = sets[c].size();
                    while (length != map.length) {
                        int adder = 0 + rand.nextInt(getRowCounterRef);
                        if (list.contains(String.valueOf(adder)) && !sets[c].contains(adder)) {
                            sets[c].add(adder);
                            list.remove(String.valueOf(adder));
                            length = sets[c].size();
                        }
                      lectLength++;
                        if(lectLength>=groupCount){
                            sets[c].add(freePeriod);
                            rowMap.put(freePeriod,"--");
                            length = sets[c].size();
                            freePeriod++;
                        }
                    }
                }
            }

            createTable();
        }else{
            for(int a=0;a< sets.length;a++){
                sets[a].clear();
            }
            rowCounter=0;
            vec[0].clear();
            vec[1].clear();
            vec[2].clear();
            vec[3].clear();
            period[0].clear();
            period[1].clear();
            period[2].clear();
            period[3].clear();
            list.clear();
            periodAddition=0;
            rowCounter=0;
            getRowCounterRef=0;
            periodAddition=0;
            JOptionPane.showMessageDialog(null,"The system was unable to process the request, Make sure the data is structured well, you can check" +
                    " the help page to learn more..");
        }
    }

    String[] column;
    public void createTable() {

        column = new String[map.length + 1];
        int aa = 0;
        for (int b = 0; b < column.length; b++) {
            if (b == 0) {
                column[b] = "---";
            } else {
                column[b] = map[aa];
                aa++;
            }
        }

        int mes = column.length / daysNumber.size();
        int c = 0;
        splitMap = new String[daysNumber.size()][column.length];
        for (int a = 0; a < daysNumber.size(); a++) {
            for (int b = 0; b < mes; b++) {
                splitMap[a][b] = column[c];
                c++;
            }
        }

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setRowCount(daysNumber.size());
        table.setRowHeight(40);
        table.setModel(tableModel);
        for (int a = 0; a < column.length; a++) {
            tableModel.addColumn(column[a]);
        }
        int counter=0;
        int[] helper=new int[sets[0].size()*sets.length];
        int[] main=new int[sets[0].size()*sets.length];
        for(int b=0;b<daysNumber.size();b++) {
            Iterator itr=sets[b].iterator();
            while (itr.hasNext()){
                helper[counter]= (int) itr.next();
                counter++;
            }
        }

        counter=0;
        for(int a=0;a< daysNumber.size();a++){
            for(int b=1;b<table.getColumnCount();b++){
                table.getModel().setValueAt(rowMap.get(helper[counter]), a, b);
                counter++;
            }
        }

        for (int a = 0; a < daysNumber.size(); a++) {
            table.getModel().setValueAt(checkBoxesItem[daysNumber.get(a)], a, 0);
        }

        for(int a=0;a< sets.length;a++){
            sets[a].clear();
        }
        rowCounter=0;
        vec[0].clear();
        vec[1].clear();
        vec[2].clear();
        vec[3].clear();
        period[0].clear();
        period[1].clear();
        period[2].clear();
        period[3].clear();
        list.clear();
        periodAddition=0;
        rowCounter=0;
        getRowCounterRef=0;
        periodAddition=0;
    }


    public void resetTable() {
        for(int a=0;a<validateTable.getRowCount();a++){
            for(int b=0;b<validateTable.getColumnCount();b++){
                validateTable.getModel().setValueAt(null,a,b);
            }
        }

    }

    public void save(){
        Random rand=new Random();
        String header="",columnData="",firstColumn="";
        String name=JOptionPane.showInputDialog(null,"File name");
        try {
            for(int a=0;a<column.length;a++){
                header+=column[a]+",";
            }
            header+="\n";
            for(int a=0;a<table.getRowCount();a++){
                for(int b=1;b<table.getColumnCount();b++){
                    columnData+=table.getModel().getValueAt(a,b)+",";
                }
            }
            columnData+="\n";
            for (int a = 0; a < daysNumber.size(); a++) {
                firstColumn+=checkBoxesItem[daysNumber.get(a)]+",";
            }
            firstColumn+="\n";
            File file = new File("src\\main\\resources\\"+name+""+rand.nextInt(10000) );
            FileOutputStream fos = new FileOutputStream(file.getAbsolutePath());
            BufferedOutputStream bos=new BufferedOutputStream(fos);
            byte b[]=header.getBytes();
            byte d[]=firstColumn.getBytes();
            byte c[]=columnData.getBytes();
            bos.write(b);
            bos.write(c);
            bos.write(d);
            bos.close();
            fos.close();

            listFiles();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void listFiles(){
        hm.clear();
        File file = new File("src\\main\\resources");
        File txt[]=file.listFiles();
        int fileCounter=0;
        for(File x:txt)
            fileCounter++;
        String word[]=new String[fileCounter];
        fileCounter=0;
        for(File x:txt){
            word[fileCounter]=x.getName();
            hm.put(fileCounter,word[fileCounter]);
            fileCounter++;
        }

        listJ.setListData(word);
        storageRoom[0].repaint();

    }


    public void openSavedTable(String fName){
        DefaultTableModel model=new DefaultTableModel();
        model.setRowCount(0);
        savedTable.setModel(model);

        String word="";
        ArrayList header=new ArrayList();
        ArrayList column0=new ArrayList();
        ArrayList restColumn=new ArrayList();
        int alCounter=0;
        try{
            FileInputStream fis=new FileInputStream("src\\main\\resources\\"+fName);
            BufferedInputStream bis=new BufferedInputStream(fis);
            int a= 0;
            while((a=bis.read())!=-1){
                if((char) a!=',') {
                    word += (char) a;
                }
                if((char) a == ','){
                    if(alCounter==0){
                        header.add(word);
                        word="";
                    }else if(alCounter==1){
                        restColumn.add(word);
                        word="";
                    }else if(alCounter==2){
                        column0.add(word);
                        word="";
                    }
                }else if((char) a == '\n'){
                    alCounter++;
                }
            }
            bis.close();
            fis.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        String[] head=new String[header.size()];
        for(int a=0;a<head.length;a++){
            head[a]= String.valueOf(header.get(a));
        }

        model=new DefaultTableModel(head,column0.size());
        savedTable.setModel(model);
        savedTable.setRowHeight(50);
        for(int a=0;a<column0.size();a++){
            savedTable.getModel().setValueAt(column0.get(a),a,0);
        }

        int thisRowCounter=0;
        for(int a=0;a<column0.size();a++){
            for(int b=1;b<savedTable.getColumnCount();b++) {
                savedTable.getModel().setValueAt(restColumn.get(thisRowCounter),a,b);
                thisRowCounter++;
            }
        }
    }
}
