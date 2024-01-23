package owner.code.demo.atomic;

public class Counter {
    private volatile  static int count = 0;
    public void addCount(){
        count++;
    }
    public int getCount(){
        return count;
    }
}
