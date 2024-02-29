package owner.code.demo.function;

import java.util.function.Function;

public class FunctionChildren implements Function<Integer, Integer> {


    @Override
    public Integer apply(Integer o) {
        return o + 10;
    }
}
