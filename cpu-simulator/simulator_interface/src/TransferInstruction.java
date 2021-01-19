/******
This class is used to process TransferInstruction.
It is the second step of processing.
It will handle the user input transfer instruction and
change cooresponding register and memory content.
*/

public class TransferInstruction {
    public TransferInstruction(){}

    public boolean processJZ(String instructionData, Hardware hardware){
        String[] instruction = instructionData.split(",");
        int RealAddress = hardware.realAddress(instruction[1],instruction[2]);
        if(instruction.length == 4){
            if(!hardware.checkIntruction(Integer.parseInt(instruction[0]), Integer.parseInt(instruction[1]), 5, Integer.parseInt(instruction[2]))){
                return false;
            }
            if(RealAddress < 6){
                return false;
            }
            if(hardware.getRegisterValue(Integer.parseInt(instruction[0])) == 0){
                hardware.PC.setValue(hardware.stringToInt(hardware.memoryToPC(hardware.stringToInt(hardware.simulator_memory.memoryArray[RealAddress][1].toString()))));
                return true;
            } else {
                hardware.PC.setValue(hardware.PC.getValuew() + 1);
                return true;
            }
        } else if(instruction.length == 3){
            if(!hardware.checkIntruction(Integer.parseInt(instruction[0]),Integer.parseInt(instruction[1]),5,Integer.parseInt(instruction[2]))){
                return false;
            }
            if(hardware.getRegisterValue(Integer.parseInt(instruction[0])) == 0){
                hardware.PC.setValue(hardware.stringToInt(hardware.memoryToPC(RealAddress)));
                return true;
            } else {
                hardware.PC.setValue(hardware.PC.getValuew() + 1);
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean processJNE(String instructionData, Hardware hardware){
        String[] instruction = instructionData.split(",");
        int RealAddress = hardware.realAddress(instruction[1],instruction[2]);
        if(instruction.length == 4){
            if(!hardware.checkIntruction(Integer.parseInt(instruction[0]), Integer.parseInt(instruction[1]), 5, Integer.parseInt(instruction[2]))){
                return false;
            }
            if(RealAddress < 6){
                return false;
            }
            if(hardware.getRegisterValue(Integer.parseInt(instruction[0])) != 0){
                hardware.PC.setValue(hardware.stringToInt(hardware.memoryToPC(hardware.stringToInt(hardware.simulator_memory.memoryArray[RealAddress][1].toString()))));
                return true;
            } else {
                hardware.PC.setValue(hardware.PC.getValuew() + 1);
                return true;
            }
        } else if(instruction.length == 3){
            if(!hardware.checkIntruction(Integer.parseInt(instruction[0]),Integer.parseInt(instruction[1]),5,Integer.parseInt(instruction[2]))){
                return false;
            }
            if(hardware.getRegisterValue(Integer.parseInt(instruction[0])) != 0){
                hardware.PC.setValue(hardware.stringToInt(hardware.memoryToPC(RealAddress)));
                return true;
            } else {
                hardware.PC.setValue(hardware.PC.getValuew() + 1);
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean processJCC(String instructionData, Hardware hardware){
        String[] instruction = instructionData.split(",");
        int RealAddress = hardware.realAddress(instruction[1],instruction[2]);
        if(instruction.length == 4){
            if(!hardware.checkIntruction(2, Integer.parseInt(instruction[1]), 5, Integer.parseInt(instruction[2]))){
                return false;
            }
            if(RealAddress < 6){
                return false;
            }
            if(hardware.getCCRegisterValue() == 2){
                hardware.PC.setValue(hardware.stringToInt(hardware.memoryToPC(hardware.stringToInt(hardware.simulator_memory.memoryArray[RealAddress][1].toString()))));
                return true;
            } else {
                hardware.PC.setValue(hardware.PC.getValuew() + 1);
                return true;
            }
        } else if(instruction.length == 3){
            if(!hardware.checkIntruction(2,Integer.parseInt(instruction[1]),5,Integer.parseInt(instruction[2]))){
                return false;
            }
            if(hardware.getCCRegisterValue() == 2){
                hardware.PC.setValue(hardware.stringToInt(hardware.memoryToPC(RealAddress)));
                return true;
            } else {
                hardware.PC.setValue(hardware.PC.getValuew() + 1);
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean processJMA(String instructionData, Hardware hardware){
        String[] instruction = instructionData.split(",");
        int RealAddress = hardware.realAddress(instruction[0],instruction[1]);
        if(instruction.length == 3){
            if(!hardware.checkIntruction(2, Integer.parseInt(instruction[0]), 5, Integer.parseInt(instruction[1]))){
                return false;
            }
            if(RealAddress < 6){
                return false;
            }
            hardware.PC.setValue(hardware.stringToInt(hardware.memoryToPC(hardware.stringToInt(hardware.simulator_memory.memoryArray[RealAddress][1].toString()))));
            return true;
        } else if(instruction.length == 2){
            if(!hardware.checkIntruction(2,Integer.parseInt(instruction[0]),5,Integer.parseInt(instruction[1]))){
                return false;
            }
            hardware.PC.setValue(hardware.stringToInt(hardware.memoryToPC(RealAddress)));
            return true;
        } else {
            return false;
        }
    }

    // Not complete function
    public boolean processJSR(String instructionData, Hardware hardware){
        String[] instruction = instructionData.split(",");
        int RealAddress = hardware.realAddress(instruction[0],instruction[1]);
        if(instruction.length == 3){
            if(!hardware.checkIntruction(2, Integer.parseInt(instruction[0]), 5, Integer.parseInt(instruction[1]))){
                return false;
            }
            if(RealAddress < 6){
                return false;
            }
            hardware.R3.setValue(hardware.PC.getValuew() + 1);
            hardware.PC.setValue(hardware.stringToInt(hardware.memoryToPC(hardware.stringToInt(hardware.simulator_memory.memoryArray[RealAddress][1].toString()))));
            // R0 contains pointer to arguments
            hardware.R0.setValue(100);
            return true;
        } else if(instruction.length == 2){
            if(!hardware.checkIntruction(2,Integer.parseInt(instruction[0]),5,Integer.parseInt(instruction[1]))){
                return false;
            }
            hardware.R3.setValue(hardware.PC.getValuew() + 1);
            hardware.R0.setValue(hardware.PC.getValuew());
            hardware.PC.setValue(hardware.stringToInt(hardware.memoryToPC(RealAddress)));
            // R0 contains pointer to arguments
            hardware.R0.setValue(100);
            return true;
        } else {
            return false;
        }
    }
    // not complete function
    public boolean processRFS(String instructionData, Hardware hardware){
        String[] instruction = instructionData.split(",");
        if(instruction.length > 1){
            return false;
        }
        if(hardware.stringToInt(instruction[0]) >= 2048){
            return false;
        }
        hardware.R0.setValue(hardware.stringToInt(instruction[0]));
        hardware.PC.setValue(hardware.R3.getValuew());
        return true;
    }

    //**********what if register smaller than 0************
    public boolean processSOB(String instructionData, Hardware hardware){
        String[] instruction = instructionData.split(",");
        int RealAddress = hardware.realAddress(instruction[1],instruction[2]);
        if(instruction.length == 4){
            if(!hardware.checkIntruction(Integer.parseInt(instruction[0]), Integer.parseInt(instruction[1]), 5, Integer.parseInt(instruction[2]))){
                return false;
            }
            if(RealAddress < 6){
                return false;
            }
            hardware.setRegisterValue(hardware.stringToInt(instruction[0]), hardware.getRegisterValue(hardware.stringToInt(instruction[0])) - 1);
            if(hardware.getRegisterValue(hardware.stringToInt(instruction[0])) > 0){
                hardware.PC.setValue(hardware.stringToInt(hardware.memoryToPC(hardware.stringToInt(hardware.simulator_memory.memoryArray[RealAddress][1].toString()))));
                return true;
            } else {
                hardware.PC.setValue(hardware.PC.getValuew() + 1);
                return true;
            }
        } else if(instruction.length == 3){
            if(!hardware.checkIntruction(Integer.parseInt(instruction[0]),Integer.parseInt(instruction[1]),5,Integer.parseInt(instruction[2]))){
                return false;
            }
            hardware.setRegisterValue(Integer.parseInt(instruction[0]), hardware.getRegisterValue(Integer.parseInt(instruction[0])) - 1);
            if(hardware.getRegisterValue(Integer.parseInt(instruction[0])) > 0){
                hardware.PC.setValue(hardware.stringToInt(hardware.memoryToPC(RealAddress)));
                return true;
            } else {
                hardware.PC.setValue(hardware.PC.getValuew() + 1);
                return true;
            }
        } else {
            return false;
        }
    }
    public boolean processJGE(String instructionData, Hardware hardware){
        String[] instruction = instructionData.split(",");
        int RealAddress = hardware.realAddress(instruction[1],instruction[2]);
        if(instruction.length == 4){
            if(!hardware.checkIntruction(Integer.parseInt(instruction[0]), Integer.parseInt(instruction[1]), 5, Integer.parseInt(instruction[2]))){
                return false;
            }
            if(RealAddress < 6){
                return false;
            }
            if(hardware.getRegisterValue(Integer.parseInt(instruction[0])) >= 0){
                hardware.PC.setValue(hardware.stringToInt(hardware.memoryToPC(hardware.stringToInt(hardware.simulator_memory.memoryArray[RealAddress][1].toString()))));
                return true;
            } else {
                hardware.PC.setValue(hardware.PC.getValuew() + 1);
                return true;
            }
        } else if(instruction.length == 3){
            if(!hardware.checkIntruction(Integer.parseInt(instruction[0]),Integer.parseInt(instruction[1]),5,Integer.parseInt(instruction[2]))){
                return false;
            }
            if(hardware.getRegisterValue(Integer.parseInt(instruction[0])) >= 0){
                hardware.PC.setValue(hardware.stringToInt(hardware.memoryToPC(RealAddress)));
                return true;
            } else {
                hardware.PC.setValue(hardware.PC.getValuew() + 1);
                return true;
            }
        } else {
            return false;
        }
    }

}
