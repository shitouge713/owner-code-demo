package owner.code.demo.snowFlake;

import java.util.UUID;

public class SnowFlakeTest {
    public static void main(String[] args) {
        SnowFlake flake = new SnowFlake(1,12);
        System.out.println(flake.nextId());
    }
}
