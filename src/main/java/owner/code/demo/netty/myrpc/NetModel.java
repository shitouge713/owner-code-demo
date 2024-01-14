package owner.code.demo.netty.myrpc;

import java.io.Serializable;
import java.util.Arrays;

public class NetModel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 传递过程中的几个必要参数
     * 接口名
     * 方法名
     * 方法参数
     * 方法类型
     */
    String interfaceName;
    String methodName;
    Object[] args;
    String[] type;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "NetModel{" +
                "interfaceName='" + interfaceName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", args=" + Arrays.toString(args) +
                ", type=" + Arrays.toString(type) +
                '}';
    }
}
