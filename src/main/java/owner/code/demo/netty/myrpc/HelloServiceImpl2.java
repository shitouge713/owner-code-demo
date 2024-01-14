package owner.code.demo.netty.myrpc;

public class HelloServiceImpl2 implements HelloService {
    @Override
    public String sayHello(String name) {
        return "hello-40,"+name;
    }

    @Override
    public Person getPerson(String name) {
        Person person = new Person();
        person.setName(name);
        person.setAge(40);
        return person;
    }
}
