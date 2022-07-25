import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public interface Inter extends ActionListener,ItemListener, MouseListener, KeyListener, ListSelectionListener {

    public void getData();
    public void apply();
}
