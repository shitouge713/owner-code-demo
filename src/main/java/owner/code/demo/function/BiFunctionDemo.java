package owner.code.demo.function;

import java.util.function.BiFunction;

public class BiFunctionDemo {
    public static void main(String[] args) {
        /**
         * 以下写法可以用lambda表达式代替，该表达式即表示重写BiFunction的apply方法
         * (BiFunction) (o, o2) -> (String)o + o2;
         * (o, o2) -> o + o2;
         */
        BiFunction<String, String, String> biFunction = new BiFunction() {
            @Override
            public Object apply(Object o, Object o2) {
                return (String) o + o2;
            }
        };
        System.out.println(biFunction.apply("Hello ", "Jack!")); // Hello Jack!
    }
}
