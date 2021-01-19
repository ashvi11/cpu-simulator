public class VectorOperation {
    public boolean processVADD(String instructionData, Hardware hardware){
        String[] instruction = instructionData.split(",");
        int RealAddress = hardware.realAddress(instruction[1],instruction[2]);
        int vectorLength = hardware.getFR(Integer.parseInt(instruction[0]));
        if(instruction.length == 4){
            if(!hardware.checkIntruction(1, Integer.parseInt(instruction[1]), 5, Integer.parseInt(instruction[2]))){
                return false;
            }
            if(RealAddress < 6){
                return false;
            }
            for(int i = 0; i < vectorLength; i ++){
                hardware.simulator_memory.setMemory(hardware.stringToInt((String)hardware.simulator_memory.memoryArray[hardware.stringToInt((String) hardware.simulator_memory.memoryArray[Integer.parseInt(instruction[2])][1])][1]),
                        hardware.intToString(16,(hardware.stringToInt((String)hardware.simulator_memory.memoryArray[Integer.parseInt(instruction[2] + i)][1]) +
                                hardware.stringToInt( (String)hardware.simulator_memory.memoryArray[Integer.parseInt(instruction[2] + i + 1)][1]))));
            }
            return true;
        } else if(instruction.length == 3){
            for(int i = 0; i < vectorLength; i ++){
            hardware.simulator_memory.setMemory(hardware.stringToInt((String) hardware.simulator_memory.memoryArray[Integer.parseInt(instruction[2])][1]),
                    hardware.intToString(16, (hardware.stringToInt((String)hardware.simulator_memory.memoryArray[Integer.parseInt(instruction[2] + i)][1]) +
                            hardware.stringToInt( (String)hardware.simulator_memory.memoryArray[Integer.parseInt(instruction[2] + i + 1)][1]))));
            }
            return true;
        } else {
            return false;
        }
    }
    public boolean processVSUB(String instructionData, Hardware hardware){
        String[] instruction = instructionData.split(",");
        int RealAddress = hardware.realAddress(instruction[1],instruction[2]);
        int vectorLength = hardware.getFR(Integer.parseInt(instruction[0]));
        if(instruction.length == 4){
            if(!hardware.checkIntruction(1, Integer.parseInt(instruction[1]), 5, Integer.parseInt(instruction[2]))){
                return false;
            }
            if(RealAddress < 6){
                return false;
            }
            for(int i = 0; i < vectorLength; i ++){
                hardware.simulator_memory.setMemory(hardware.stringToInt((String)hardware.simulator_memory.memoryArray[hardware.stringToInt((String) hardware.simulator_memory.memoryArray[Integer.parseInt(instruction[2])][1])][1]),
                        hardware.intToString(16,(hardware.stringToInt((String)hardware.simulator_memory.memoryArray[Integer.parseInt(instruction[2] + i)][1]) -
                                hardware.stringToInt( (String)hardware.simulator_memory.memoryArray[Integer.parseInt(instruction[2] + i + 1)][1]))));
            }
            return true;
        } else if(instruction.length == 3){
            for(int i = 0; i < vectorLength; i ++){
                hardware.simulator_memory.setMemory(hardware.stringToInt((String) hardware.simulator_memory.memoryArray[Integer.parseInt(instruction[2])][1]),
                        hardware.intToString(16, (hardware.stringToInt((String)hardware.simulator_memory.memoryArray[Integer.parseInt(instruction[2] + i)][1]) -
                                hardware.stringToInt( (String)hardware.simulator_memory.memoryArray[Integer.parseInt(instruction[2] + i + 1)][1]))));
            }
            return true;
        } else {
            return false;
        }
    }
    public boolean processCNVRT(String instructionData, Hardware hardware){
        String[] instruction = instructionData.split(",");
        int RealAddress = hardware.realAddress(instruction[1],instruction[2]);
        int flag = hardware.getFR(0);
        int number = hardware.stringToInt((String)hardware.simulator_memory.memoryArray[Integer.parseInt(instruction[2])][1]);
        if(flag == 0){
            hardware.setRegisterValue(0, number);
            return true;
        } else if(flag == 1){
            hardware.FR0.setValue(number);
            return true;
        } else {
            return false;
        }
    }
}
