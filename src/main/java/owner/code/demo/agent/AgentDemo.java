package owner.code.demo.agent;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * @Description: Agent 方法主入口
 * @Author: sxl
 * @date: 2023/08/12 9:00 PM
 */
public class AgentDemo {

    // JVM运行前，Agent完成字节码修改
    public static void premain(String args, Instrumentation inst) {
        System.out.println("JVM运行前，Agent字节码修改");
        inst.addTransformer(new PreMainTransformerDemo());
        System.out.println("JVM运行前，Agent字节码修改完成");
    }

    // JVM运行时，Agent修改字节码
    public static void agentmain(String args, Instrumentation inst) {
        System.out.println("JVM运行时，Agent修改字节码");
        Class[] allClass = inst.getAllLoadedClasses();
        for (Class c : allClass) {
            if (c.getName().contains("owner")) {
                inst.addTransformer(new AgentMainTransformerDemo(), true);
                try {
                    //agentmain 是JVM运行时，需要调用 retransformClasses 重定义类 ！！
                    inst.retransformClasses(c);
                } catch (UnmodifiableClassException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println("JVM运行时，Agent修改字节码完成");
    }
}
