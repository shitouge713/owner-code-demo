package owner.code.demo.function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 函数式编程的好处是只有在调用的时候才知道具体的执行逻辑
 * 定义方法时只需要定义好参数即可
 *
 * @param <T>
 */
public class ConsumerDemo<T> {
    private T t;

    public static void main(String[] args) {
        ConsumerDemo<String> demo = new ConsumerDemo<>();
        demo.t = "测试";
        demo.consumerMethod(a -> {
            System.out.println(a);
        });
    }

    public void consumerExample1() {
        Consumer consumer = a -> {
            System.out.println(123);
        };
        consumer.accept("测试1234");
    }

    public void consumerExample2() {
        List<String> list = new ArrayList<>();
        list.stream().forEach(a -> {
            System.out.println(123);
        });
    }

    public void consumerMethod(Consumer<? super T> action) {
        action.accept(t);
    }
}
