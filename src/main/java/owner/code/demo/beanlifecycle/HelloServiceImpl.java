package owner.code.demo.beanlifecycle;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public void login(String username, String password) {
        System.out.println ("登录");
    }

    @Override
    public void regist() {
        System.out.println ("注册");
    }

    @Override
    public void search() {
        System.out.println ("userService search...");
    }

    @Override
    public void update() {
        System.out.println ("update...");
    }
}
