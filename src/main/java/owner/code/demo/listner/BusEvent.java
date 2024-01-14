package owner.code.demo.listner;

import java.util.EventObject;

public class BusEvent extends EventObject {
    Object source;
    public BusEvent(Object source) {
        super(source);
        this.source = source;
    }
    @Override
    public Object getSource() {
        return source;
    }
    public void setSource(Object source) {
        this.source = source;
    }
}
