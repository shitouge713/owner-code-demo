package owner.code.demo.arithmetic;

/**
 * 一个整数，它加上100后是一个完全平方数，加上168又是一个完全平方数，请问该数是多少
 */
public class Suanfa02 {
    public static void main(String[] args) {
        for (int i = 1; i <10000000 ; i++) {
            if(Math.sqrt(i+100)%1==0){
                if(Math.sqrt(i+168)%1==0){
                    System.out.println(i);
                }
            }
        }
    }
}
