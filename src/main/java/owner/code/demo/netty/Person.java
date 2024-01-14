package owner.code.demo.netty;

public class Person implements Car {
        private Car car;

    public Person(Car car) {
        this.car = car;
    }

    @Override
    public void driver() {
        System.out.println("driver-before");
        car.driver();
        System.out.println("driver-after");
    }
}
