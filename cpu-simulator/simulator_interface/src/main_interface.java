import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class main_interface {

    public processInstruction data;
    public JFrame frame;
    /*
    This function if for create listener for each button.
     */
    public main_interface() {
        data = new processInstruction();
        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                processInput(e);
                showRunPanel(e);
            }
        });
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset(e);
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        readFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                jfc.showDialog(new JLabel(), "Choose");
                File file = jfc.getSelectedFile();
                BufferedReader br = null;
                try {
                    br = new BufferedReader(new FileReader(file));
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

                String st;
                while (true) {
                    try {
                        if (((st = br.readLine()) != null)) {
                            appendToPane(input, st, Color.black);
                        } else {
                            break;
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }

            }
        });
        stepRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInputPanel(e);
            }
        });
        IPL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showIPLPanel(e);
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                output.setText("");
            }
        });
    }

    /*
    main function call this function to show main panel.
     */
    public void setShowPanel() {
        frame = new JFrame("main_interface");
        inputScroll.setRowHeaderView(new LineNumberHeaderView());
        outputScroll.setRowHeaderView(new LineNumberHeaderView());
        frame.add(main);
        frame.setSize(1100, 600);
        frame.setVisible(true);
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("main_interface");
//        frame.setContentPane(new main_interface().main);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//    }

    private JPanel main;
    private JPanel memoryPanel;
    private JTable memory;
    private JPanel instructionPanel;
    private JPanel inputPanel;
    private JPanel buttonPanel;
    private JButton run;
    private JButton readFile;
    private JButton restart;
    private JButton exit;
    private JLabel Titile;
    private JPanel Register;
    private JPanel showPanel;
    private JTextField register3Value;
    private JTextField register2Value;
    private JTextField register1Value;
    private JTextField MARValue;
    private JTextField MBRValue;
    private JTextField IRValue;
    private JTextField PCValue;
    private JLabel PC;
    private JLabel MAR;
    private JLabel MBR;
    private JLabel register1;
    private JLabel IR;
    private JLabel register2;
    private JLabel register3;
    private JLabel R0;
    private JLabel R1;
    private JLabel R2;
    private JLabel R3;
    private JTextField R0Value;
    private JTextField R1Value;
    private JTextField R2Value;
    private JTextField R3Value;
    private JScrollPane memoryScroll;
    private JScrollPane inputScroll;
    private JTextArea guide;
    private JScrollPane guideScroll;
    private JTextPane input;
    private JScrollPane outputScroll;
    private JTextArea output;
    private JScrollPane cacheScroll;
    private JTable cache;
    private JButton IPL;
    private JButton stepRun;
    private JLabel cc;
    private JTextField a0000TextField;
    private JLabel MFR;
    private JTextField MFRValue;
    private JButton clear;
    private JTextField FR1Value;
    private JLabel FR1;
    private JLabel FR0;
    private JTextField FR0Value;


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public void setRegister1Value(String text) {
        register1Value.setText(text);
    }

    public void setRegister2Value(String text) {
        register2Value.setText(text);
    }

    public void setRegister3Value(String text) {
        register3Value.setText(text);
    }

    public void setPcValue(String text) {
        PCValue.setText(text);
    }

    public void setMARValue(String text) {
        MARValue.setText(text);
    }

    public void setMDRValue(String text) {
        MBRValue.setText(text);
    }

    public void setIRValue(String text) {
        IRValue.setText(text);
    }

    public void setTable() {
        final String[] columnNames = {"Address", "Value"};
        TableModel dataModel = new DefaultTableModel(data.hardware.simulator_memory.memoryArray, columnNames);
        memory.setModel(dataModel);
        final String[] columnNames2 = {"tag", "word0", "word1", "word2", "word3", "word4", "word5", "word6", "word7"};
        TableModel dataModel2 = new DefaultTableModel(data.hardware.cache.cacheArray, columnNames2);
        cache.setModel(dataModel2);
    }

    public String getIRValue() {
        return IRValue.getText();
    }

    public JPanel getmain() {
        return main;
    }

    public JTextField getPc(){
        return PCValue;
    }


    protected void showInputPanel(ActionEvent arg) {
        inputAddress inputAddress = new inputAddress(this);
        inputAddress.show();
    }

    protected void showIPLPanel(ActionEvent arg) {
        IPLinput ipLinput = new IPLinput(this);
        ipLinput.show();
    }

    protected void showRunPanel(ActionEvent arg) {
        runInput runInput = new runInput(this);
        runInput.show();
    }

    protected void reset(ActionEvent arg) {
        data.hardware.setMAR(0);
        data.hardware.setMDR(0);
        data.hardware.setIR(0);
        data.hardware.setPC(0);
        data.hardware.setX1(0);
        data.hardware.setX2(0);
        data.hardware.setX3(0);
        data.hardware.setR0(0);
        data.hardware.setR1(0);
        data.hardware.setR2(0);
        data.hardware.setR3(0);
        data.hardware.setFR0(0);
        data.hardware.setFR1(0);
        for (int i = 0; i < 2048; i++) {
            data.hardware.simulator_memory.memoryArray[i][1] = "0000,0000,0000,0000";
            data.hardware.simulator_memory.memoryArray[i][2] = "";
        }
        refreshInterface();

    }

    /*
    This function will be called if user click the run button.
     */
    protected void processInput(ActionEvent arg) {
        String instruction = input.getText();
        int i = 0;
        if (!instruction.equals("")) {
            String[] Instruction = instruction.split("\n");
            for (; i < Instruction.length; i++) {
                input.setText("");
                for (int j = 0; j < Instruction.length; j++) {
                    if (j == i) {
                        appendToPane(input, Instruction[j], Color.red);
                        StyleContext sc = StyleContext.getDefaultStyleContext();
                        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, Color.black);
                        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Andale Mono");
                        aset = sc.addAttribute(aset, StyleConstants.FontSize, 12);
                        int len = input.getDocument().getLength();
                        input.setCaretPosition(len);
                        input.setCharacterAttributes(aset, false);
                        input.replaceSelection("");
                    } else {
                        appendToPane(input, Instruction[j], Color.black);
                    }
                }
                String[] instructionArray = Instruction[i].split(" ");
                boolean result = data.setInstruction(instructionArray);
                if (result) {
                    refreshInterface();
                } else {
                    break;
                }
            }
            if (i == Instruction.length) {
                input.setText("");
                JOptionPane.showMessageDialog(main, "Program Finished!", "Message", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(main, "Line " + (i) + " Wrong!", "Message", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    /*
    Interface will refresh, then the new value will show.
     */
    public void refreshInterface() {
        register3Value.setText(data.hardware.intToString(16, data.hardware.x3.value));
        register2Value.setText(data.hardware.intToString(16, data.hardware.x2.value));
        register1Value.setText(data.hardware.intToString(16, data.hardware.x1.value));
        MARValue.setText(data.hardware.intToString(12, data.hardware.MAR.value));
        MBRValue.setText(data.hardware.intToString(16, data.hardware.MDR.value));
        IRValue.setText(data.hardware.intToString(16, data.hardware.IR.value));
        PCValue.setText(data.hardware.intToString(12, data.hardware.PC.value));
        R0Value.setText(data.hardware.intToString(16, data.hardware.R0.value));
        R1Value.setText(data.hardware.intToString(16, data.hardware.R1.value));
        R2Value.setText(data.hardware.intToString(16, data.hardware.R2.value));
        R3Value.setText(data.hardware.intToString(16, data.hardware.R3.value));
        MFRValue.setText(data.hardware.intToString(4,data.hardware.MFR.value));
        FR0Value.setText(data.hardware.intToString(16,data.hardware.FR0.value));
        FR1Value.setText(data.hardware.intToString(16,data.hardware.FR1.value));
        setTable();
    }

    /*
    Set text in JTextArea line by line.
     */
    private void appendToPane(JTextPane tp, String msg, Color c) {
        msg = msg + "\n";
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);
        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Andale Mono");
        aset = sc.addAttribute(aset, StyleConstants.FontSize, 12);
        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
    }


    /*
    need to be finished.
     */
    public void stepRun(){
        int address = data.hardware.PC.value;
        data.hardware.cache.hit(address,data.hardware);
        String instruction = (String) data.hardware.simulator_memory.memoryArray[address][2];
        String[] instructionArray = instruction.split(" ");
        if(instructionArray[0] == "OUT"){
            processOUT(instructionArray[1]);
            return;
        }
        if(instructionArray[0] == "IN"){
            processIN(instructionArray[1]);
            return;
        }
        boolean result = data.setInstruction(instructionArray);
        if (!result && !data.hardware.simulator_memory.memoryArray[address][2].equals("")) {
            JOptionPane.showMessageDialog(main, "Memory " + (address) + " Wrong!", "Message", JOptionPane.ERROR_MESSAGE);
        } else {
            data.hardware.PC.value += 1;
            refreshInterface();
        }
        refreshInterface();
    }

    /*
    need to be finished.
     */
    public void storeInstruction(int address){
        String instruction = input.getText();
        if (!instruction.equals("")){
            String[] Instruction = instruction.split("\n");
            for(int i=0;i<Instruction.length;i++){
                data.hardware.simulator_memory.memoryArray[i+address][2] = Instruction[i];
                String[] splitInstruction = Instruction[i].split(" ");
                String[] instructionData = splitInstruction[1].split(",");
                if(splitInstruction[0].equals("LDR") || splitInstruction[0].equals("STR") || splitInstruction[0].equals("LDA")
                        || splitInstruction[0].equals("JZ") || splitInstruction[0].equals("JNE") || splitInstruction[0].equals("JCC") ||
                         splitInstruction[0].equals("SOB") ||
                        splitInstruction[0].equals("JGE") || splitInstruction[0].equals("AMR") || splitInstruction[0].equals("SMR")||splitInstruction[0].equals("FADD")
                        ||splitInstruction[0].equals("FSUB")||splitInstruction[0].equals("LDFR")||splitInstruction[0].equals("STFR")){
                    String ins;
                    if(splitInstruction[0].equals("LDR")){
                        ins = "000001";
                    }
                    else if(splitInstruction[0].equals("STR")){
                        ins = "000010";
                    }
                    else if(splitInstruction[0].equals("LDA")){
                        ins = "000011";
                    }
                    else if(splitInstruction[0].equals("JZ")){
                        ins = "001010";
                    }
                    else if(splitInstruction[0].equals("JNE")){
                        ins = "001011";
                    }
                    else if(splitInstruction[0].equals("JCC")){
                        ins = "001100";
                    }
                    else if(splitInstruction[0].equals("SOB")){
                        ins = "010000";
                    }
                    else if(splitInstruction[0].equals("AMR")){
                        ins = "000100";
                    }
                    else if(splitInstruction[0].equals("SMR")){
                        ins = "000101";
                    }
                    else if(splitInstruction[0].equals("FADD")){
                        ins = "100001";
                    }
                    else if(splitInstruction[0].equals("FSUB")){
                        ins = "100010";
                    }
                    else if(splitInstruction[0].equals("LDFR")){
                        ins = "110010";
                    }
                    else if(splitInstruction[0].equals("STFR")){
                        ins = "110011";
                    }
                    else {
                        ins = "010001";
                    }
                    StringBuilder builder = new StringBuilder(ins);
                    builder.append(data.hardware.intToString(2,Integer.parseInt(instructionData[0])));
                    builder.append(data.hardware.intToString(2,Integer.parseInt(instructionData[1])));
                    if(instructionData.length==4){
                        builder.append('1');
                    }
                    else{
                        builder.append('0');
                    }
                    builder.append(data.hardware.intToString(5,Integer.parseInt(instructionData[2])));
                    for(int j = 0;j<(16/4)-1;j++){
                        builder.insert(4+j*4+j,',');
                    }
                    ins = builder.toString();
                    data.hardware.simulator_memory.memoryArray[address+i][1] = ins;
                    continue;
                }
                else if(splitInstruction[0].equals("LDX") || splitInstruction[0].equals("STX")||splitInstruction[0].equals("JMA") || splitInstruction[0].equals("JSR")){
                    data.hardware.simulator_memory.memoryArray[i+address][2] = Instruction[i];
                    String ins = "101001";
                    if(splitInstruction[0].equals("STX")){
                        ins = "101010";
                    }
                    else if(splitInstruction[0].equals("JMA")){
                        ins = "001101";
                    }
                    else if(splitInstruction[0].equals("JSR")){
                        ins = "001110";
                    }
                    StringBuilder builder = new StringBuilder(ins);
                    builder.append("00");
                    builder.append(data.hardware.intToString(2,Integer.parseInt(instructionData[0])));
                    if(instructionData.length==3){
                        builder.append('1');
                    }
                    else{
                        builder.append('0');
                    }
                    builder.append(data.hardware.intToString(5,Integer.parseInt(instructionData[1])));
                    for(int j = 0;j<(16/4)-1;j++){
                        builder.insert(4+j*4+j,',');
                    }
                    ins = builder.toString();
                    data.hardware.simulator_memory.memoryArray[address+i][1] = ins;
                    continue;
                }
                else if(splitInstruction[0].equals("RFS")){
                    data.hardware.simulator_memory.memoryArray[i+address][2] = Instruction[i];
                    String ins = "001111";
                    StringBuilder builder = new StringBuilder(ins);
                    builder.append("00");
                    builder.append("00");
                    builder.append("0");
                    builder.append(data.hardware.intToString(5,Integer.parseInt(instructionData[2])));
                    for(int j = 0;j<(16/4)-1;j++){
                        builder.insert(4+j*4+j,',');
                    }
                    ins = builder.toString();
                    data.hardware.simulator_memory.memoryArray[address+i][1] = ins;
                    continue;
                }
                else if(splitInstruction[0].equals("AIR") || splitInstruction[0].equals("SIR")){
                    data.hardware.simulator_memory.memoryArray[i+address][2] = Instruction[i];
                    String ins = "000110";
                    if(splitInstruction[0].equals("SIR")){
                        ins = "000111";
                    }
                    StringBuilder builder = new StringBuilder(ins);
                    builder.append(data.hardware.intToString(2,Integer.parseInt(instructionData[0])));
                    builder.append("00");
                    builder.append("0");
                    builder.append(data.hardware.intToString(5,Integer.parseInt(instructionData[1])));
                    for(int j = 0;j<(16/4)-1;j++){
                        builder.insert(4+j*4+j,',');
                    }
                    ins = builder.toString();
                    data.hardware.simulator_memory.memoryArray[address+i][1] = ins;
                    continue;
                }
                else if(splitInstruction[0].equals("MLT") || splitInstruction[0].equals("DVD") || splitInstruction[0].equals("TRR")
                        || splitInstruction[0].equals("AND" ) || splitInstruction[0].equals("ORR")){
                    data.hardware.simulator_memory.memoryArray[i+address][2] = Instruction[i];
                    String ins = "010100";
                    if(splitInstruction[0].equals("DVD")){
                        ins = "010101";
                    }
                    else if(splitInstruction[0].equals("TRR")){
                        ins = "010110";
                    }
                    else if(splitInstruction[0].equals("AND")){
                        ins = "010111";
                    }
                    else{
                        ins = "011000";
                    }
                    StringBuilder builder = new StringBuilder(ins);
                    builder.append(data.hardware.intToString(2,Integer.parseInt(instructionData[0])));
                    builder.append(data.hardware.intToString(2,Integer.parseInt(instructionData[1])));
                    builder.append("000000");
                    for(int j = 0;j<(16/4)-1;j++){
                        builder.insert(4+j*4+j,',');
                    }
                    ins = builder.toString();
                    data.hardware.simulator_memory.memoryArray[address+i][1] = ins;
                    continue;
                }
                else if(splitInstruction[0].equals("NOT")){
                    data.hardware.simulator_memory.memoryArray[i+address][2] = Instruction[i];
                    String ins = "011001";
                    StringBuilder builder = new StringBuilder(ins);
                    builder.append(data.hardware.intToString(2,Integer.parseInt(instructionData[0])));
                    builder.append("00000000");
                    for(int j = 0;j<(16/4)-1;j++){
                        builder.insert(4+j*4+j,',');
                    }
                    ins = builder.toString();
                    data.hardware.simulator_memory.memoryArray[address+i][1] = ins;
                    continue;
                }
                else if(splitInstruction[0].equals("IN") || splitInstruction[0].equals("OUT")){
                    data.hardware.simulator_memory.memoryArray[i+address][2] = Instruction[i];
                    String ins = "111101";
                    if(splitInstruction[0].equals("OUT")){
                        ins = "111110";
                    }
                    StringBuilder builder = new StringBuilder(ins);
                    builder.append(data.hardware.intToString(2,Integer.parseInt(instructionData[0])));
                    builder.append("000");
                    builder.append(data.hardware.intToString(5,Integer.parseInt(instructionData[1])));
                    for(int j = 0;j<(16/4)-1;j++){
                        builder.insert(4+j*4+j,',');
                    }
                    ins = builder.toString();
                    data.hardware.simulator_memory.memoryArray[address+i][1] = ins;
                }
                else if(splitInstruction[0]=="HLT"){
                    data.hardware.simulator_memory.memoryArray[i+address][2] = Instruction[i];
                }
            }
        }
        input.setText("");
        setTable();
    }

    public void processIN (String instructionData){
        String[] instruction = instructionData.split(",");
        in in = new in(Integer.parseInt(instruction[0]),this);
        in.show();
    }

    public void processOUT (String instructionData){
        String[] instruction = instructionData.split(",");
        int num = Integer.parseInt(instruction[0]);
        if (num == 0){
            String  string = R0Value.getText() + "\n";
            output.append(string);
        }
        else if(num == 1){
            String  string = R1Value.getText() + "\n";
            output.append(string);
        }
        else if(num == 2){
            String  string = R2Value.getText() + "\n";
            output.append(string);
        }
        else if(num == 3){
            String  string = R3Value.getText() + "\n";
            output.append(string);
        }
    }

    public void setRegisterValue(int RegisterNumber,int value){
        if(RegisterNumber == 0){
            R0Value.setText(data.hardware.intToString(16,value));
        }
        else if(RegisterNumber == 1){
            R1Value.setText(data.hardware.intToString(16,value));
        }
        else if(RegisterNumber == 2){
            R2Value.setText(data.hardware.intToString(16,value));
        }
        else{
            R3Value.setText(data.hardware.intToString(16,value));
        }
    }
}
