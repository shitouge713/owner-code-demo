package owner.code.demo.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 我们得到一个ID列表，我们希望从中获取一个名称和一个统计信息，并将它们成对地组合起来，所有这些都是异步的。
 * 以下示例使用Reactor执行此操作：
 */
public class ReactorDemo {
    public static void main(String[] args) {
        ReactorDemo demo = new ReactorDemo();
        demo.method();
    }

    public void method() {
        Flux<String> ids = ifhIds();
        Flux<String> combinations =
                ids.flatMap(id -> {
                    Mono<String> nameTask = ifhName(id);
                    Mono<String> statTask = ifhStat(id);
                    return nameTask.zipWith(statTask,
                            (name, stat) -> "Name " + name + " has stats " + stat);
                });
        Mono<List<String>> result = combinations.collectList();
        List<String> results = result.block();
        System.out.println(results);
    }


    public Flux<String> ifhIds() {
        Flux<String> result = Flux.just("1", "2", "3");
        return result;
    }

    public Mono<String> ifhName(String name) {
        Mono result = Mono.just("姓名：" + name);
        return result;
    }

    public Mono<String> ifhStat(String id) {
        Mono result = Mono.just("id：" + id);
        return result;
    }
}
