/***********
This is class is used to define memory
 */
public class memory {

    Object[][] memoryArray = new Object[2048][3];

    public memory(){
        for(int i =0;i<2048;i++){
            memoryArray[i][0] = i;
            memoryArray[i][1] = "0000,0000,0000,0000";
            memoryArray[i][2] = "";
        }
    }

    public void setMemory(int i,String value){
        memoryArray[i][1] = value;
    }

}
