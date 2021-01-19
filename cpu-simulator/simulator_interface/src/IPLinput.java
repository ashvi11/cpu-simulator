import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class IPLinput {
    private JTextField decimalTextField;
    public JTextField binaryTextField;
    private JButton cancelButton;
    private JButton OKButton;
    private JPanel inputAddress;
    private JLabel Decimal;
    private JLabel Binary;
    private main_interface main_interface;
    JFrame jFrame = new JFrame();

    public IPLinput(main_interface main_interface){
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
                main_interface.storeInstruction(Integer.parseInt(decimalTextField.getText()));//refresh the interface in the stepRun function
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
        int windowWidth = main_interface.frame.getWidth(); //获得窗口宽
        int windowHeight = main_interface.frame.getHeight(); //获得窗口高
        int screenWidth = jFrame.getWidth(); //获取屏幕的宽
        int screenHeight = jFrame.getHeight(); //获取屏幕的高
        jFrame.setLocation(windowWidth/2-screenWidth/2, windowHeight/2-screenHeight/2);//设置窗口居中显示
        jFrame.setVisible(true);
    }

    public void setValue(String value){
        if(!value.equals("")){
            int result = Integer.parseInt(value);
            binaryTextField.setText(main_interface.data.hardware.intToString(12,result));
        }
    }
}
