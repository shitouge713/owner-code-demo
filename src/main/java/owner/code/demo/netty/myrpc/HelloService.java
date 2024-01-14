package owner.code.demo.netty.myrpc;

public interface HelloService {
    String sayHello(String name);
    Person getPerson(String name);
}
