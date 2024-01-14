package owner.code.demo.cglib;

import owner.code.demo.cglib.AnimalInterface;
import owner.code.demo.cglib.TargetInterface;

/**
 * 目标实现类
 */
public class Target implements TargetInterface, AnimalInterface {

    @Override
    public String method1() {
        System.out.println("method1 running...");
        return "aaa";
    }

    @Override
    public void method2() {
        System.out.println("method2 running...");
    }

    @Override
    public int method3(int x) {
        return x;
    }

    @Override
    public void run() {
        System.out.println("动物在跑...");
    }

    @Override
    public void sing() {
        System.out.println("动物在唱歌...");
    }
}
