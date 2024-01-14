package owner.code.demo.arithmetic;

/**
 * 双重检测单例模式
 * https://www.cnblogs.com/tangZH/p/10031337.html
 * 为什么要用synchronized
 * 多线程环境下，两个线程可能判断对象都不存在而创建两个对象
 * 为什么要将synchronized写在代码中而不是方法上
 * 为了尽可能减少需要同步的逻辑
 * 为什么要有两层if(singleton==null)判断
 * 如果没有第一次，那么每次都进入同步的逻辑，如果没有第二层，那么两个线程在获取锁之前，还是存在生成两个对象的可能
 * instance为何要有volatile 修饰
 * 为了防止指令重排序
 *
 */
public class Singleton {
    private static volatile  Singleton singleton = null;
    private Singleton(){
    }
    public static Singleton getSingleton(){
        if(singleton==null){
            synchronized (Singleton.class){
                if(singleton==null){
                    return new Singleton();
                }
            }
        }
        return singleton;
    }
}
