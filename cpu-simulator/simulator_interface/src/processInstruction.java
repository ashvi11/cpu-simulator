import com.sun.security.jgss.InquireSecContextPermission;

/******
This class is used to process the instruction.
It is the first step of processing.
It will read user input command.
It will distribute different instruction to different processing file
to future processing.
 */


public class processInstruction {

    public Hardware hardware;
    public processInstruction(){
        hardware =  new Hardware();
        hardware.simulator_memory.memoryArray[1][1] = "0000,0000,0000,0110";
        hardware.simulator_memory.memoryArray[6][2] = "HLT";
    }

    /*
    Call the function for exact instruction based on the input
     */
    public boolean setInstruction(String[] instruction){
        loadStoreInstruction part1 = new loadStoreInstruction();
        arithmetic_Instructions part2 = new arithmetic_Instructions();
        TransferInstruction part3 = new TransferInstruction();
        if(instruction[0].equals("LDR") || instruction[0].equals("ldr")){
            return part1.processLDR(instruction[1],hardware);
        }
        else if(instruction[0].equals("STR") || instruction[0].equals("str")){
            return part1.processSTR(instruction[1],hardware);
        }
        else if(instruction[0].equals("LDA") || instruction[0].equals("lda")){
            return part1.processLDA((instruction[1]),hardware);
        }
        else if(instruction[0].equals("LDX") || instruction[0].equals("ldx")){
            return part1.processLDX(instruction[1],hardware);
        }
        else if(instruction[0].equals("STX") || instruction[0].equals("stx")){
            return part1.processSTX(instruction[1],hardware);
        }
        else if(instruction[0].equals("AMR")){
            return part2.processAMR(instruction[1],hardware);
        }
        else if(instruction[0].equals("AIR")){
            return part2.processAIR(instruction[1],hardware);
        }
        else if(instruction[0].equals("SMR")){
            return part2.processSMR(instruction[1],hardware);
        }
        else if(instruction[0].equals("SIR")){
            return part2.processSIR(instruction[1],hardware);
        }
        else if(instruction[0].equals("TRR")){
            return part2.processTRR(instruction[1],hardware);
        }
        else if(instruction[0].equals("AND")){
            return part2.processAND(instruction[1],hardware);
        }
        else if(instruction[0].equals("ORR")){
            return part2.processORR(instruction[1],hardware);
        }
        else if(instruction[0].equals("NOT")){
            return part2.processNOT(instruction[1],hardware);
        }
        else if(instruction[0].equals("SRC")){
            return part2.processSRC(instruction[1],hardware);
        }
        else if(instruction[0].equals("RRC")){
            return part2.processRRC(instruction[1],hardware);
        }
        else if(instruction[0].equals("MLT")){
            return part2.processMLT(instruction[1],hardware);
        }
        else if(instruction[0].equals("DVD")){
            return part2.processDVD(instruction[1],hardware);
        }else if(instruction[0].equals("JZ") || instruction[0].equals("jz")){
            return part3.processJZ(instruction[1],hardware);
        } else if(instruction[0].equals("JNE") || instruction[0].equals("jne")){
            return part3.processJNE(instruction[1],hardware);
        } else if(instruction[0].equals("JCC") || instruction[0].equals("jcc")){
            return part3.processJCC((instruction[1]),hardware);
        } else if(instruction[0].equals("JMA") || instruction[0].equals("jma")){
            return part3.processJMA(instruction[1],hardware);
        } else if(instruction[0].equals("JSR") || instruction[0].equals("jsr")){
            return part3.processJSR(instruction[1],hardware);
        } else if(instruction[0].equals("RFS") || instruction[0].equals("rfs")){
            return part3.processRFS(instruction[1],hardware);
        }else if(instruction[0].equals("SOB") || instruction[0].equals("sob")){
            return part3.processSOB(instruction[1],hardware);
        }else if(instruction[0].equals("JGE") || instruction[0].equals("jge")){
            return part3.processJGE(instruction[1],hardware);
        }
        else if(instruction[0].equals("FADD") || instruction[0].equals("fadd")){
            return part2.processFADD(instruction[1],hardware);
        }
        else if(instruction[0].equals("FSUB") || instruction[0].equals("fsub")){
            return part2.processFSUB(instruction[1],hardware);
        }
        else if(instruction[0].equals("LDFR") || instruction[0].equals("ldfr")){
            return part2.processLDFR(instruction[1],hardware);
        }
        else if(instruction[0].equals("STFR") || instruction[0].equals("stfr")){
            return part2.processSTFR(instruction[1],hardware);
        }
        else {
            hardware.MFR.value = 4;
            return false;
        }
    }

}
