package owner.code.demo.reactor;


import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.Observer;

@Slf4j
public class ObserverOfRxJavaDemo {
    public static void main(String[] args) {
        //创建一个观察者对象，重写onCompleted、onError、onNext方法
        Observer obServer = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                log.info("完成");
            }

            @Override
            public void onError(Throwable e) {
                log.info("异常");
            }

            @Override
            public void onNext(Integer integer) {
                log.info("接收Obsverable中发射的值：" + integer);
            }
        };
        //Observable.create创建一个观察对象，此时观察对象是泛指，尚不确定是谁，当observable被订阅时，会执行观察对象的方法
        Observable<Integer> observable = Observable.create(subscribe -> {
            subscribe.onNext(1);
            subscribe.onCompleted();
        });
        //将obServer对象订阅到observable对象上,当observable被订阅时，会执行观察对象（方法参数obServer）定义的方法
        observable.subscribe(obServer);
    }


}
