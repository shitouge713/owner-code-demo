package owner.code.demo.listner;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class EventSourceObject {
    Set<BusListner> listners;
    public EventSourceObject(){
        this.listners = new HashSet<BusListner>();
    }
    public void add(BusListner listner){
        this.listners.add(listner);
    }
    public void change(){
        System.out.println("事件源发生变化");
        Iterator<BusListner> iterator = listners.iterator();
        while (iterator.hasNext()){
            iterator.next().callBackEvent(new BusEvent(this));
        }
    }
}
