package owner.code.demo.reactor;


import lombok.extern.slf4j.Slf4j;
import rx.Observable;

/**
 * 在上述示例中，我们使用 Observable.create() 方法创建了一个 Observable 对象。
 * 该 Observable 对象会发送一个 "Hello World!" 消息，然后发送一个完成通知。
 * 我们使用 subscribe() 方法订阅该 Observable 对象，并在收到消息时将其打印到控制台。
 */
@Slf4j
public class ObservableDemo {
    public static void main(String[] args) {
        /**
         * Observable.create()
         * 接收一个回调函数作为参数，该回调函数会在observable对象被订阅时调用
         * 回调函数的第一个参数是 Observer 对象，它可以用于发送数据和通知到订阅者。
         * 回调函数的第二个参数是 error 对象，它可以用于发送错误到订阅者。
         * 回调函数的第三个参数是完成通知，它可以用于通知订阅者 Observable 对象已经完成。
         */
        Observable<String> observable = Observable.create(subscribe -> {
            subscribe.onNext("hello world");
            subscribe.onCompleted();
        });
        //observable被订阅
        observable.subscribe((value) -> {
            System.out.println(value);
        });
    }


}
