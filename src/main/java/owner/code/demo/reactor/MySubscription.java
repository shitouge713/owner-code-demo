package owner.code.demo.reactor;

import org.reactivestreams.Subscription;

public class MySubscription implements Subscription {
    @Override
    public void request(long n) {
        System.out.println("Subscription n:" + n);
    }

    @Override
    public void cancel() {
        System.out.println("Subscription 执行 cancel");
    }
}
