package owner.code.demo.reactor;


import java.util.Observable;
import java.util.Observer;

public class ObserverDemo extends Observable {
    public static void main(String[] args) {
        ObserverDemo observerDemo = new ObserverDemo();

        //添加观察者
        observerDemo.addObserver((o, arg) -> {
            System.out.println("数据发生变化A");
        });
        observerDemo.addObserver((o, arg) -> {
            System.out.println("数据发生变化B");
        });
        /*Observer observer = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                System.out.println("数据发生变化C");
            }
        };
        observerDemo.addObserver(observer);
        */
        //将此Observable对象标记为已更改
        observerDemo.setChanged();
        //如果该对象发生了变化，则通知其所有观察者
        observerDemo.notifyObservers();
    }


}
