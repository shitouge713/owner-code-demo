package owner.code.demo.listner;

import java.util.EventListener;

public class BusListner implements EventListener {
    public void callBackEvent(BusEvent event){
        Object source = event.getSource();
        System.out.println("执行到监听器逻辑");
        System.out.println(source);
    }
}
