package owner.code.demo.queue;

import java.lang.instrument.Instrumentation;

public class ObjectSizeFetcher {
    private static volatile Instrumentation instrumentation;

    public static void premain(String args, Instrumentation inst) {
        instrumentation = inst;
    }

    public static long getObjectSize(Object obj) {
        if (instrumentation == null) {
            throw new IllegalStateException("Instrumentation is not initialized");
        }
        return instrumentation.getObjectSize(obj);
    }
}
