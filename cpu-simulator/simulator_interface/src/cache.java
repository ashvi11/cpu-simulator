/***********
this class is used for build system cache
It contains method to process hit or miss condition.
*/
public class cache {
    /*
    1.Full Associative Cache
    2.FIFO
     */
    String[][] cacheArray = new String[16][9];
    int count = 0;

    public cache(){
        for(int i=0;i<16;i++){
            for(int j=0;j<9;j++){
                cacheArray[i][j] = "null";
            }
        }
    }

    public String hit(String address,Hardware hardware){
        String tag = "";
        for(int i = 0;i<11;i++){
            if(address.charAt(i) != ','){
                tag += address.charAt(i);
            }
        }
        /*
        if hit
         */
        for(int i = 0;i<16;i++){
            if(cacheArray[i][0].equals(tag)){
                return cacheArray[i][hardware.stringToInt(address.substring(address.length()-3,address.length()))+1];
            }
        }

        /*
        if miss
         */
        int start = hardware.stringToInt(address.substring(0,11) + "000");
        cacheArray[count%16][0] = tag;
        for(int i = 1;i<9;i++){
            cacheArray[count%16][i] = (String) hardware.simulator_memory.memoryArray[start][1];
            start += 1;
        }
        count ++;
//        System.out.println(cacheArray[0][0]);
        return (String) hardware.simulator_memory.memoryArray[hardware.stringToInt(address)][1];
    }

    public String hit(int address1,Hardware hardware){
        String address = hardware.intToString(12,address1);
        String tag = "";
        for(int i = 0;i<11;i++){
            if(address.charAt(i) != ','){
                tag += address.charAt(i);
            }
        }
        /*
        if hit
         */
        for(int i = 0;i<16;i++){
            if(cacheArray[i][0].equals(tag)){
                return cacheArray[i][hardware.stringToInt(address.substring(address.length()-3,address.length()))+1];
            }
        }

        /*
        if miss
         */
        int start = hardware.stringToInt(address.substring(0,11) + "000");
        cacheArray[count%16][0] = tag;
        for(int i = 1;i<9;i++){
            cacheArray[count%16][i] = (String) hardware.simulator_memory.memoryArray[start][1];
            start += 1;
        }
        count ++;
//        System.out.println(cacheArray[0][0]);
        return (String) hardware.simulator_memory.memoryArray[hardware.stringToInt(address)][1];
    }
}
