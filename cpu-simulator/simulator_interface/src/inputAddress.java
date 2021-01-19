import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class inputAddress {

    private JTextField decimalTextField;
    public JTextField binaryTextField;
    private JButton cancelButton;
    private JButton OKButton;
    private JPanel inputAddress;
    private JLabel Decimal;
    private JLabel Binary;
    private main_interface main_interface;
    JFrame jFrame = new JFrame();

    public inputAddress(main_interface main_interface){
        this.main_interface = main_interface;
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.add(inputAddress);
        jFrame.setSize(300,150);
        decimalTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                int keyChar = e.getKeyChar();
                if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){

                }else{
                    e.consume();
                }
            }
        });

        decimalTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String string = decimalTextField.getText();
                setValue(string);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String string = decimalTextField.getText();
                setValue(string);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String string = decimalTextField.getText();
                setValue(string);
            }
        });
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main_interface.data.hardware.setPC(Integer.parseInt(decimalTextField.getText()));//refresh the interface in the stepRun function
                main_interface.stepRun();
                jFrame.dispose();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
            }
        });
    }

    public void show(){
        int windowWidth = main_interface.frame.getWidth();
        int windowHeight = main_interface.frame.getHeight();
        int screenWidth = jFrame.getWidth();
        int screenHeight = jFrame.getHeight();
        jFrame.setLocation(windowWidth/2-screenWidth/2, windowHeight/2-screenHeight/2);
        jFrame.setVisible(true);
    }

    public void setValue(String value){
        if(!value.equals("")){
            int result = Integer.parseInt(value);
            binaryTextField.setText(main_interface.data.hardware.intToString(12,result));
        }
    }


}
