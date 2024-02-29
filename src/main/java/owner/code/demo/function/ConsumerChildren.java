package owner.code.demo.function;

import java.util.function.Consumer;

public class ConsumerChildren implements Consumer<Integer> {

    @Override
    public void accept(Integer integer) {
        System.out.println(integer + 10);
    }
}
