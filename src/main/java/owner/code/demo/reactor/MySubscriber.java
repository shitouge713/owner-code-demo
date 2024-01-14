package owner.code.demo.reactor;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class MySubscriber implements Subscriber<String> {
    @Override
    public void onSubscribe(Subscription s) {
        System.out.println("2222222222");
    }

    @Override
    public void onNext(String s) {
        System.out.println("MySubscriber-onNext:" + s);
    }

    @Override
    public void onError(Throwable t) {
        System.out.println("MySubscriber-onError:" + t);
    }

    @Override
    public void onComplete() {
        System.out.println("MySubscriber-onComplete");
    }
}
