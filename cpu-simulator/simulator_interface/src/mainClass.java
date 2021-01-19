import javax.swing.*;
import java.awt.*;
/*****************
this is program entrance
****************/
public class mainClass {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                main_interface newinterface = new main_interface();
                newinterface.setTable();
                newinterface.setShowPanel();
            }
        });
    }
}
