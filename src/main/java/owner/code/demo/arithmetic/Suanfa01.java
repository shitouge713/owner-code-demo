package owner.code.demo.arithmetic;

import java.util.ArrayList;
import java.util.List;

/**
 * 有1、2、3、4个数字，能组成多少个互不相同且无重复数字的三位数？都是多少？
 */

public class Suanfa01 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        int count = 0;
        for (int i = 1; i <list.size()+1 ; i++) {
            for (int j = 1; j <list.size()+1 ; j++) {
                for (int k = 1; k <list.size()+1 ; k++) {
                    if(i!=j&&j!=k&&i!=k){
                        count++;
                        System.out.println(100*i+10*j+k);
                    }
                }
            }
        }
        System.out.println(count);
    }
}
