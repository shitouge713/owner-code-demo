package owner.code.demo.reactor;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

/**
 * 自定义Publisher，实现MyPublisher
 */
public class MyPublisher implements Publisher<String> {

    @Override
    public void subscribe(Subscriber<? super String> subscriber) {
        // 订阅者订阅后，会调用这个方法
        /*subscriber.onSubscribe(new Subscription() {
            @Override
            public void request(long n) {
                // 订阅者请求数据时，会调用这个方法
                for (int i = 0; i < n; i++) {
                    subscriber.onNext("Hello World!");
                }
                subscriber.onComplete();
            }
            @Override
            public void cancel() {
                // 订阅者取消订阅时，会调用这个方法
            }
        });*/
        subscriber.onSubscribe(new MySubscription());
    }
}
