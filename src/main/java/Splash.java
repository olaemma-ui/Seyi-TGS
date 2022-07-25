import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;

public class Splash extends Frame{

    JLabel[] label={new JLabel("MOSHOOD ABIOLA POLYTHECNIC"),new JLabel("DEPARTMENT OF SCIENCE AND TECHNOLOGY"),new JLabel("TIME-TABLE GENERATING SOFTWARE")};
    static JProgressBar jpb=new JProgressBar();
    JPanel panel=new JPanel();
    int y;

    Splash(){
        jpb.setMaximum(100);
        jpb.setBounds(175,180,150,5);
        jpb.setForeground(Color.blue);

        y=50;

        for(int a=0;a<label.length;a++){
            label[a].setHorizontalAlignment(JLabel.CENTER);
            label[a].setBounds(SwingConstants.CENTER,y,500,10);
            label[a].setForeground(Color.white);
            panel.add(label[a]);
            y+=30;
        }

        panel.setBackground(Color.blue);
        panel.setForeground(Color.white);
        panel.setLayout(null);
        panel.add(jpb);

        setSize(500,200);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setUndecorated(true);
        add(panel,BorderLayout.CENTER);
        setVisible(true);
    }

    public void start(){
//        Splash splash=new Splash();
        try{
            for(int a=0;a<100;a++){
                Thread.sleep(100);
                jpb.setValue(a);
            }
            this.dispose();
            new Action();
        }catch(Exception e){/**/}
    }
}
