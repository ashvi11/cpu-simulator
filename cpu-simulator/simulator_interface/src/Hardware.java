
/*************
this class contains all hardware component
and all possible methods for handle communicate problem with
different register and memory.
*/

public class Hardware {
    public register MAR;
    public register MDR;
    public register PC;
    public register IR;
    public register x1;
    public register x2;
    public register x3;
    public register R0;
    public register R1;
    public register R2;
    public register R3;
    public register cc;
    public register MFR;
    public register FR0;
    public register FR1;
    public memory simulator_memory;
    public cache cache;

    public Hardware(){
        MAR = new register(12,0);
        MDR = new register(16,0);
        PC = new register(12,0);
        IR = new register(16,0);
        x1 = new register(16,0);
        x2 = new register(16,0);
        x3 = new register(16,0);
        R0 = new register(16,0);
        R1 = new register(16,0);
        R2 = new register(16,0);
        R3 = new register(16,0);
        cc = new register(4,0);
        FR0 = new register(16,0);
        FR1 = new register(16,0);
        MFR = new register(4,0);
        simulator_memory = new memory();
        cache = new cache();
    }

    public void setRegisterValue(int registerNumber,int value){
        if(registerNumber == 0){
            setR0(value);
        }
        else if(registerNumber == 1){
            setR1(value);
        }
        else if(registerNumber == 2){
            setR2(value);
        }
        else{
            setR3(value);
        }
    }

    public void setMAR(int value){
        MAR.value = value;
    }

    public void setCc(int value)  { cc.value = value;}
    public void setMFR(int value)  { MFR.value = value;}

    public void setMDR(int value){
        MDR.value = value;
    }

    public void setPC(int value){
        PC.value = value;
    }

    public void setIR(int value){
        IR.value = value;
    }

    public void setX1(int value){
        x1.value = value;
    }

    public void setX2(int value){
        x2.value = value;
    }

    public void setX3(int value){
        x3.value = value;
    }

    public void setR0(int value){
        R0.value = value;
    }

    public void setR1(int value){
        R1.value = value;
    }

    public void setR2(int value){
        R2.value = value;
    }

    public void setR3(int value){
        R3.value = value;
    }
    public void setFR0(int value){
        FR0.value = value;
    }
    public void setFR1(int value){
        FR1.value = value;
    }

    public int getRegisterValue(int registerNumber){
        if(registerNumber == 0){
            return R0.value;
        }
        else if(registerNumber == 1){
            return R1.value;
        }
        else if(registerNumber == 2){
            return R2.value;
        }
        else{
            return R3.value;
        }
    }
    public int getIndexRegisterValue(int indexRegisterNumber){
        if(indexRegisterNumber == 0){
            return 0;
        }
        else if(indexRegisterNumber == 1){
            return x1.value;
        }
        else if(indexRegisterNumber == 2){
            return x2.value;
        }
        else{
            return x3.value;
        }
    }

    public void setIndexRegisterValue(int indexRegisterNumber,int value){
        if(indexRegisterNumber == 0){
        }
        else if(indexRegisterNumber == 1){
            setX1(value);
        }
        else if(indexRegisterNumber == 2){
            setX2(value);
        }
        else{
            setX3(value);
        }
    }

    public int getMAR(){
        return MAR.value;
    }

    public int getMDR(){
        return MDR.value;
    }

    public int getIR(){
        return IR.value;
    }

    public int getPC(){
        return PC.value;
    }

    public Object[][] getMemory(){
        return simulator_memory.memoryArray;
    }


    public int stringToInt(String data){
        String[] dataArray = data.split(",");
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<dataArray.length;i++){
            builder.append(dataArray[i]);
        }
        String finalData = builder.toString();
        if(finalData.length()==16&&finalData.charAt(0)=='1'){
            int count = Integer.valueOf(finalData.substring(1,16),2);
            return -(Integer.valueOf(Integer.toBinaryString(~(count-1)).substring(17,32),2));
        }
        return Integer.valueOf(finalData,2);
    }

    /*
    make data more user friendly.
     */
    public String intToString(int bit,int value){
        if(value>=Math.pow(2,bit)-1){
            String string = "";
            StringBuilder builder = new StringBuilder(string);
            for(int i =0;i<bit;i++){
                builder.insert(0,'1');
            }
            for(int i = 0;i<(bit/4)-1;i++){
                builder.insert(4+i*4+i,',');
            }
            return builder.toString();
        }
        String string = Integer.toBinaryString(value);
        StringBuilder builder;
        if(string.length()<bit){
            builder = new StringBuilder(string);
            for(int i=0; i < bit-string.length();i++){
                builder.insert(0,'0');
            }
        }
        else{
            builder = new StringBuilder(string.substring(string.length()-bit,string.length()));
        }
        for(int i = 0;i<(bit/4)-1;i++){
            builder.insert(4+i*4+i,',');
        }
        return builder.toString();
    }

    public int realAddress(String indexRegister,String address){
        return stringToInt((String)simulator_memory.memoryArray[Integer.parseInt(address)+getIndexRegisterValue(Integer.parseInt(indexRegister))][1]);
    }
    public boolean checkIntruction(int register,int indexRegister,int addressBit,int address){
        if(register>3 || register<0){
            return false;
        }
        if(indexRegister>3 || indexRegister<0){
            return false;
        }
        if(address<6){
            MFR.value = 1;
            return false;
        }
        if(address>=2048){
            MFR.value = 8;
            return false;
        }
        return true;
    }

    public String memoryToPC(int address){
        StringBuilder sb = new StringBuilder();
        String memoryContent = simulator_memory.memoryArray[address][1].toString();
        for(int i = 4; i < memoryContent.length(); i++){
            if(memoryContent.charAt(i) != ',') {
                sb.append(memoryContent.charAt(i));
            }
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public int getFR(int register){
        if(register==0){
            return FR0.value;
        }
        else{
            return FR1.value;
        }
    }

    public int getCCRegisterValue(){
        return cc.value;
    }

}
