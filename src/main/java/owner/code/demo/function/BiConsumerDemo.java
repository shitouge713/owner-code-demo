package owner.code.demo.function;

import java.util.*;
import java.util.function.BiConsumer;

/**
 * BiConsumer作为参数，定义好方法体
 * 再定义具体的执行逻辑
 */
public class BiConsumerDemo<K,V> {
    private K k;
    private V v;

    public static void main(String[] args) {
        BiConsumerDemo<String, String> demo = new BiConsumerDemo();
        demo.k = "123";
        demo.v = "456";
        demo.biConsumerMethod((arg1, arg2) -> {
            System.out.println("arg1:" + arg1 + ", arg2:" + arg2);
        });
    }

    public void biConsumerExample1() {
        Map<String, Integer> numberMap = new HashMap<>();
        numberMap.put("One", 1);
        //BiConsumer 执行体
        BiConsumer<String, Integer> bic = (arg1, arg2) -> {
            System.out.println("arg1:" + arg1 + ", arg2:" + arg2);
        };
        //使用，当方法参数为BiConsumer时，直接调用
        numberMap.forEach(bic);
    }

    public void biConsumerExample2() {
        Map<String, Integer> numberMap = new HashMap<>();
        numberMap.put("One", 1);
        //BiConsumer 执行体的简写
        numberMap.forEach((arg1, arg2) -> {
            System.out.println("arg1:" + arg1 + ", arg2:" + arg2);
        });
    }

    /**
     * 参数为 BiConsumer的方法
     * @param action
     */
    void biConsumerMethod(BiConsumer<? super K, ? super V> action) {
        Objects.requireNonNull(action);
        K k = this.k;
        V v = this.v;
        action.accept(k, v);
    }
}
