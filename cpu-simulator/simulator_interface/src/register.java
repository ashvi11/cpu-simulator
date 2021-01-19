import java.math.*;


/*******
This is class is used to define register
 */
public class register {

    public int bit;
    public int value;

    public register(int bit){
        this.bit = bit;
        this.value = 0;
    }

    public register(int bit,int value){
        this.bit = bit;
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValuew(){
        return value;
    }

    /*
    Exception
     */
    private void checkValue() {
        if (value >= Math.pow(2, bit)) {

        }
    }
}
