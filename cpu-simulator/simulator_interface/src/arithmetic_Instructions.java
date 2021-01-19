//import com.sun.tools.javac.util.SharedNameTable;

/*********
This class is used to process arithmeticInstructions.
It is the second step of processing.
It will handle the user input all arithmetic related instruction and
change cooresponding register and memory content.
*/

public class arithmetic_Instructions {

    public boolean processAMR(String instructionData,Hardware hardware){
        hardware.setPC(hardware.getPC()+1);
        String[] instruction = instructionData.split(",");
        if(instruction.length ==3||instruction.length==4){
            int register = Integer.parseInt(instruction[0]);
            int indexRegister = Integer.parseInt(instruction[1]);
            int address = Integer.parseInt(instruction[2]);
            if(!hardware.checkIntruction(register,indexRegister,5,address)){
                return false;
            }
            if(instruction.length==4){
                address = hardware.realAddress(instruction[1],instruction[2]);
                if(address<6){
                    return false;
                }
                hardware.setRegisterValue(register,hardware.getRegisterValue(register)+hardware.stringToInt(hardware.cache.hit(hardware.intToString(12,address),hardware)));
                return true;
            }
            hardware.setRegisterValue(register,hardware.getRegisterValue(register)+hardware.stringToInt(hardware.cache.hit(hardware.intToString(12,address+hardware.getIndexRegisterValue(indexRegister)),hardware)));
            return true;
        }
        else{
            return false;
        }
    }

    public boolean processSMR(String instructionData,Hardware hardware){
        hardware.setPC(hardware.getPC()+1);
        String[] instruction = instructionData.split(",");
        if(instruction.length ==3||instruction.length==4){
            int register = Integer.parseInt(instruction[0]);
            int indexRegister = Integer.parseInt(instruction[1]);
            int address = Integer.parseInt(instruction[2]);
            if(!hardware.checkIntruction(register,indexRegister,5,address)){
                return false;
            }
            if(instruction.length==4){
                address = hardware.realAddress(instruction[1],instruction[2]);
                if(address<6){
                    return false;
                }
                hardware.setRegisterValue(register,hardware.getRegisterValue(register)-hardware.stringToInt(hardware.cache.hit(hardware.intToString(12,address),hardware)));
                return true;
            }
            hardware.setRegisterValue(register,hardware.getRegisterValue(register)-hardware.stringToInt(hardware.cache.hit(hardware.intToString(12,address+hardware.getIndexRegisterValue(indexRegister)),hardware)));
            return true;
        }
        else{
            return false;
        }
    }

    public boolean processAIR(String instructionData,Hardware hardware){
        hardware.setPC(hardware.getPC()+1);
        String[] instruction = instructionData.split(",");
        if(instruction.length == 2){
            int register = Integer.parseInt(instruction[0]);
            int num = Integer.parseInt(instruction[1]);
            hardware.setRegisterValue(register,hardware.getRegisterValue(register)+num);
            return true;
        }
        return false;
    }

    public boolean processSIR(String instructionData,Hardware hardware){
        hardware.setPC(hardware.getPC()+1);
        String[] instruction = instructionData.split(",");
        if(instruction.length == 2){
            int register = Integer.parseInt(instruction[0]);
            int num = Integer.parseInt(instruction[1]);
            hardware.setRegisterValue(register,hardware.getRegisterValue(register)-num);
            return true;
        }
        return false;
    }

    public boolean processTRR(String instructionData,Hardware hardware){
        hardware.setPC(hardware.getPC()+1);
        String[] instruction = instructionData.split(",");
        if(instruction.length==2){
            if(hardware.getRegisterValue(Integer.parseInt(instruction[0]))==hardware.getRegisterValue(Integer.parseInt(instruction[1]))){
                hardware.setCc(1);
                return true;
            }
            else{
                hardware.setCc(0);
                return true;
            }
        }
        return false;
    }

    public boolean processAND(String instructionData,Hardware hardware){
        hardware.setPC(hardware.getPC()+1);
        String[] instruction = instructionData.split(",");
        if(instruction.length==2){
            short x = (short) hardware.getRegisterValue(Integer.getInteger(instruction[0]));
            short y = (short) hardware.getRegisterValue(Integer.getInteger(instruction[1]));
            hardware.setRegisterValue(Integer.getInteger(instruction[0]),x&y);
            return true;
        }
        return false;
    }

    public boolean processORR (String instructionData,Hardware hardware){
        hardware.setPC(hardware.getPC()+1);
        String[] instruction = instructionData.split(",");
        if(instruction.length==2){
            short x = (short) hardware.getRegisterValue(Integer.getInteger(instruction[0]));
            short y = (short) hardware.getRegisterValue(Integer.getInteger(instruction[1]));
            hardware.setRegisterValue(Integer.getInteger(instruction[0]),x|y);
            return true;
        }
        return false;
    }

    public boolean processNOT (String instructionData,Hardware hardware){
        hardware.setPC(hardware.getPC()+1);
        String[] instruction = instructionData.split(",");
        if(instruction.length==1){
            short x = (short) hardware.getRegisterValue(Integer.getInteger(instruction[0]));
            hardware.setRegisterValue(Integer.getInteger(instruction[0]),~x);
            return true;
        }
        return false;
    }

    public boolean processSRC (String instructionData,Hardware hardware){
        hardware.setPC(hardware.getPC()+1);
        String[] instruction = instructionData.split(",");
        if(instruction.length==4){
            short x = (short) hardware.getRegisterValue(Integer.getInteger(instruction[0]));
            int count = Integer.parseInt(instruction[1]);
            if(instruction[2].equals("1")){
                if(instruction[3].equals("0")){
                    if(x<0){
                        short a = 0b100000000000;
                        hardware.setRegisterValue(Integer.getInteger(instruction[0]),a|(x<<count));
                    }
                    else{
                        short a = 0b011111111111;
                        hardware.setRegisterValue(Integer.getInteger(instruction[0]),a&(x<<count));
                    }
                }
                else{
                    hardware.setRegisterValue(Integer.getInteger(instruction[0]),x<<count);
                }
            }
            else {
                if(instruction[3].equals("0")){
                    if(x<0){
                        short a = 0b100000000000;
                        hardware.setRegisterValue(Integer.getInteger(instruction[0]),a|(x>>>count));
                    }
                    else{
                        hardware.setRegisterValue(Integer.getInteger(instruction[0]),x>>>count);
                    }
                }
                else{
                    hardware.setRegisterValue(Integer.getInteger(instruction[0]),x>>>count);
                }
            }
            return true;
        }
        return false;
    }

    public boolean processRRC (String instructionData,Hardware hardware){
        hardware.setPC(hardware.getPC()+1);
        String[] instruction = instructionData.split(",");
        if(instruction.length==4){
            short x = (short) hardware.getRegisterValue(Integer.getInteger(instruction[0]));
            int count = Integer.parseInt(instruction[1]);
            if(instruction[2].equals("1")){
                for(int i=0;i<count;i++){
                    if(x>=0){
                        x *= 2;
                    }
                    else{
                        x *= 2;
                        x += 1;
                    }
                }
            }
            else {
                for(int i=0;i<count;i++){
                    if(x%2==1){
                        x = (short) (0b100000000000 | (short) (x>>>1));
                    }
                    else{
                        x /= 2;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean processMLT (String instructionData,Hardware hardware){
        hardware.setPC(hardware.getPC()+1);
        String[] instruction = instructionData.split(",");
        int num1 = hardware.getRegisterValue(Integer.parseInt(instruction[0]));
        int num2 = hardware.getRegisterValue(Integer.parseInt(instruction[1]));
        if(num1==0 || num1==2 || num2==0 || num2==2){
            return false;
        }
        int num3 = num1*num2;
        hardware.setRegisterValue(Integer.parseInt(instruction[0]),num3>>16);
        hardware.setRegisterValue(Integer.parseInt(instruction[0])+1,num3%(int)Math.pow(2,16));
        return true;
    }

    public boolean processDVD (String instructionData,Hardware hardware){
        hardware.setPC(hardware.getPC()+1);
        String[] instruction = instructionData.split(",");
        int num1 = hardware.getRegisterValue(Integer.parseInt(instruction[0]));
        int num2 = hardware.getRegisterValue(Integer.parseInt(instruction[1]));
        if(num1==0 || num1==2){
            return false;
        }
        hardware.setRegisterValue(Integer.parseInt(instruction[0]),num1/num2);
        hardware.setRegisterValue(Integer.parseInt(instruction[0])+1,num1%num2);
        return true;
    }

    public boolean processFADD(String instructionData,Hardware hardware){
        hardware.setPC(hardware.getPC()+1);
        String[] instruction = instructionData.split(",");
        if(instruction.length ==3||instruction.length==4){
            int register = Integer.parseInt(instruction[0]);
            int indexRegister = Integer.parseInt(instruction[1]);
            int address = Integer.parseInt(instruction[2]);
            if(!hardware.checkIntruction(register,indexRegister,5,address)){
                return false;
            }
            if(instruction.length==4){
                address = hardware.realAddress(instruction[1],instruction[2]);
                if(address<6){
                    return false;
                }
            }
            int value1 = hardware.getFR(register);
            int value2 = hardware.stringToInt((String)hardware.simulator_memory.memoryArray[address][1]);
            if(value1+value2>Math.pow(2,15)-1||value1+value2<-Math.pow(2,15)){
                return false;
            }
            if(register==0){
                hardware.FR0.value=value1+value2;
            }
            else{
                hardware.FR1.value=value1+value2;
            }
            return true;
        }
        else{
            return false;
        }
    }

    public boolean processFSUB(String instructionData,Hardware hardware){
        hardware.setPC(hardware.getPC()+1);
        String[] instruction = instructionData.split(",");
        if(instruction.length ==3||instruction.length==4){
            int register = Integer.parseInt(instruction[0]);
            int indexRegister = Integer.parseInt(instruction[1]);
            int address = Integer.parseInt(instruction[2]);
            if(!hardware.checkIntruction(register,indexRegister,5,address)){
                return false;
            }
            if(instruction.length==4){
                address = hardware.realAddress(instruction[1],instruction[2]);
                if(address<6){
                    return false;
                }
            }
            int value1 = hardware.getFR(register);
            int value2 = hardware.stringToInt((String)hardware.simulator_memory.memoryArray[address][1]);
            if(value1-value2>Math.pow(2,15)-1||value1-value2<-Math.pow(2,15)){
                return false;
            }
            if(register==0){
                hardware.FR0.value=value1+value2;
            }
            else{
                hardware.FR1.value=value1+value2;
            }
            return true;
        }
        else{
            return false;
        }
    }

    public boolean processLDFR(String instructionData,Hardware hardware){
        hardware.setPC(hardware.getPC()+1);
        String[] instruction = instructionData.split(",");
        if(instruction.length ==3||instruction.length==4){
            int register = Integer.parseInt(instruction[0]);
            int indexRegister = Integer.parseInt(instruction[1]);
            int address = Integer.parseInt(instruction[2]);
            if(!hardware.checkIntruction(register,indexRegister,5,address)){
                return false;
            }
            if(instruction.length==4){
                address = hardware.realAddress(instruction[1],instruction[2]);
                if(address<6){
                    return false;
                }
            }
            int value1 = hardware.stringToInt((String)hardware.simulator_memory.memoryArray[address][1]);
            int value2 = hardware.stringToInt((String)hardware.simulator_memory.memoryArray[address+1][1]);
            if(value1>Math.pow(2,8)-1||value2>Math.pow(2,8)-1){
                return false;
            }
            if(register==0){
                hardware.FR0.value=(value1<<8)+value2;
            }
            else{
                hardware.FR1.value=value1+value2;
            }
            return true;
        }
        else{
            return false;
        }
    }

    public boolean processSTFR(String instructionData,Hardware hardware){
        hardware.setPC(hardware.getPC()+1);
        String[] instruction = instructionData.split(",");
        if(instruction.length ==3||instruction.length==4){
            int register = Integer.parseInt(instruction[0]);
            int indexRegister = Integer.parseInt(instruction[1]);
            int address = Integer.parseInt(instruction[2]);
            if(!hardware.checkIntruction(register,indexRegister,5,address)){
                return false;
            }
            if(instruction.length==4){
                address = hardware.realAddress(instruction[1],instruction[2]);
                if(address<6){
                    return false;
                }
            }
            int value1 = hardware.getFR(register);
            hardware.simulator_memory.memoryArray[address][1]=hardware.intToString(16,value1>>8);
            hardware.simulator_memory.memoryArray[address+1][1]=hardware.intToString(16,value1&(int)(Math.pow(2,8)-1));
            return true;
        }
        else{
            return false;
        }
    }

}
