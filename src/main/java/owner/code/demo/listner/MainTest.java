package owner.code.demo.listner;

public class MainTest {
    public static void main(String[] args) {
        EventSourceObject sourceObject = new EventSourceObject();
        sourceObject.add(new BusListner());
        sourceObject.change();
    }
}
