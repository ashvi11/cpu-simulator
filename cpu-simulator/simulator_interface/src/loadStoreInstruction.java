/***********
This class is used to process loadStoreInstruction.
It is the second step of processing.
It will handle the user input load and store instruction and
change cooresponding register and memory content.
*/

public class loadStoreInstruction {

    public loadStoreInstruction(){}

    public boolean processLDR(String instructionData,Hardware hardware){
        hardware.setPC(hardware.getPC()+1);
        String[] instruction = instructionData.split(",");
        if(instruction.length == 4){
            if(!hardware.checkIntruction(Integer.parseInt(instruction[0]),Integer.parseInt(instruction[1]),5,Integer.parseInt(instruction[2]))){
                return false;
            }
            int RealAddress;
            RealAddress = hardware.realAddress(instruction[1],instruction[2]);
            if(RealAddress<6){
                return false;
            }
            derectLDR(Integer.parseInt(instruction[0]),0,RealAddress,hardware);
//            setRegisterValue(Integer.parseInt(instruction[0]),stringToInt((String)simulator_memory.memoryArray[RealAddress+getIndexRegisterValue(0)][1]));
            return true;
        }
        else if(instruction.length == 3){
            if(!hardware.checkIntruction(Integer.parseInt(instruction[0]),Integer.parseInt(instruction[1]),5,Integer.parseInt(instruction[2]))){
                return false;
            }
            String address = hardware.intToString(12,hardware.getIndexRegisterValue(Integer.parseInt(instruction[1]))+Integer.parseInt(instruction[2]));
            hardware.setRegisterValue(Integer.parseInt(instruction[0]), hardware.stringToInt(hardware.cache.hit(address,hardware)));
            return true;
        }
        else{
            return false;
        }
    }

    /*
The function to process LDR instruction.
 */
    public boolean processSTR(String instructionData,Hardware hardware){
        hardware.setPC(hardware.getPC()+1);
        String[] instruction = instructionData.split(",");
        if(instruction.length==4){
            if(!hardware.checkIntruction(Integer.parseInt(instruction[0]),Integer.parseInt(instruction[1]),5,Integer.parseInt(instruction[2]))){
                return false;
            }
            int RealAdress;
            RealAdress = hardware.realAddress(instruction[1],instruction[2]);
            if(RealAdress<6){
                return false;
            }
            hardware.simulator_memory.memoryArray[RealAdress][1] = hardware.intToString(16,hardware.getRegisterValue(Integer.parseInt(instruction[0])));
            return true;
        }
        else if(instruction.length==3){
            if(!hardware.checkIntruction(Integer.parseInt(instruction[0]),Integer.parseInt(instruction[1]),5,Integer.parseInt(instruction[2]))){
                return false;
            }
            hardware.simulator_memory.memoryArray[Integer.parseInt(instruction[2])][1] = hardware.intToString(16,hardware.getRegisterValue(Integer.parseInt(instruction[0])));
            return true;
        }
        else{
            return false;
        }
    }

    /*
    The function to process LDA instruction.
     */
    public boolean processLDA(String instructionData,Hardware hardware){
        hardware.setPC(hardware.getPC()+1);
        String[] instruction = instructionData.split(",");
        if(instruction.length==4){
            if(!hardware.checkIntruction(Integer.parseInt(instruction[0]),Integer.parseInt(instruction[1]),5,Integer.parseInt(instruction[2]))){
                if(Integer.parseInt(instruction[2]) >= Math.pow(2,5)) {
                    return false;
                }
            }
            int realValue = hardware.stringToInt((String)hardware.simulator_memory.memoryArray[Integer.parseInt(instruction[2])+hardware.getIndexRegisterValue(Integer.parseInt(instruction[1]))][1]);
            hardware.setRegisterValue(Integer.parseInt(instruction[0]),realValue);
            return true;
        }
        else if(instruction.length==3){
            if(!hardware.checkIntruction(Integer.parseInt(instruction[0]),Integer.parseInt(instruction[1]),5,Integer.parseInt(instruction[2]))){
                if(Integer.parseInt(instruction[2]) >= Math.pow(2,5)) {
                    return false;
                }
            }
            hardware.setRegisterValue(Integer.parseInt(instruction[0]),Integer.parseInt(instruction[2])+hardware.getIndexRegisterValue(Integer.parseInt(instruction[1])));
            return true;
        }
        else {
            return false;
        }
    }

    /*
    The function to process LDX instruction.
     */
    public boolean processLDX(String instructionData,Hardware hardware){
        hardware.setPC(hardware.getPC()+1);
        String[] instruction = instructionData.split(",");
        if(instruction.length==3){
            if(!hardware.checkIntruction(0,Integer.parseInt(instruction[0]),7,Integer.parseInt(instruction[1]))){
                return false;
            }
            int RealAdress;
            RealAdress = hardware.stringToInt((String)hardware.simulator_memory.memoryArray[Integer.parseInt(instruction[2])][1]);
            hardware.setIndexRegisterValue(Integer.parseInt(instruction[0]),hardware.stringToInt((String) hardware.simulator_memory.memoryArray[RealAdress][1]));
            return true;
        }
        else if(instruction.length==2){
            if(!hardware.checkIntruction(0,Integer.parseInt(instruction[0]),7,Integer.parseInt(instruction[1]))){
                return false;
            }
            hardware.setIndexRegisterValue(Integer.parseInt(instruction[0]),hardware.stringToInt((String) hardware.simulator_memory.memoryArray[Integer.parseInt(instruction[1])][1]));
            return true;
        }
        else{
            return false;
        }
    }

    /*
    The function to process STX instruction.
     */
    public boolean processSTX (String instructionData,Hardware hardware){
        hardware.setPC(hardware.getPC()+1);
        String[] instruction = instructionData.split(",");
        if(instruction.length==3){
            if(!hardware.checkIntruction(0,Integer.parseInt(instruction[0]),7,Integer.parseInt(instruction[1]))){
                return false;
            }
            int RealAddress;
            RealAddress = hardware.stringToInt((String)hardware.simulator_memory.memoryArray[Integer.parseInt(instruction[2])][1]);
            if(RealAddress<6){
                return false;
            }
            hardware.simulator_memory.memoryArray[RealAddress][1] = hardware.intToString(16,hardware.getIndexRegisterValue(Integer.parseInt(instruction[0])));
            return true;
        }
        else if(instruction.length==2){
            if(!hardware.checkIntruction(0,Integer.parseInt(instruction[0]),7,Integer.parseInt(instruction[1]))){
                return false;
            }
            hardware.simulator_memory.memoryArray[Integer.parseInt(instruction[1])][1] = hardware.intToString(16,hardware.getIndexRegisterValue(Integer.parseInt(instruction[0])));
            return true;
        }
        else{
            return false;
        }
    }

    /*
    This function can return real address based on the input instruction.
     */
//    public int realAddress(String indexRegister,String address){
//        return hardware.stringToInt((String)hardware.simulator_memory.memoryArray[Integer.parseInt(address)+hardware.getIndexRegisterValue(Integer.parseInt(indexRegister))][1]);
//    }

    public void derectLDR(int registerNumber, int indexRegister, int address,Hardware hardware){
        hardware.setRegisterValue(registerNumber,hardware.stringToInt((String)hardware.simulator_memory.memoryArray[address+hardware.getIndexRegisterValue(indexRegister)][1]));
    }

}
