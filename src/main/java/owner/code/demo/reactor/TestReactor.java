package owner.code.demo.reactor;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
public class TestReactor {
    public static void main(String[] args) {
        TestReactor t = new TestReactor();
        Mono<String> mono = t.handle();
        t.method2(mono);
        mono.subscribe(new MySubscriber());
    }

    public void method2(Mono<String> mono) {
        mono.doOnSuccess(
                aVoid -> log.info("Handling completed，{}", aVoid)
        );
    }

    public Mono<String> handle(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Mono.just("123");
    }
    public void method1() {
        //just()：创建Flux序列，并声明数据流
        Flux<Integer> integerFlux = Flux.just(1, 2, 3, 4);
        //subscribe()：订阅Flux序列，只有进行订阅后才回触发数据流，不订阅就什么都不会发生
        integerFlux.subscribe(System.out::println);

        Flux<String> stringFlux = Flux.just("hello", "world");
        stringFlux.subscribe(System.out::println);

        //fromArray(),fromIterable()和fromStream()：可以从一个数组、Iterable 对象或Stream 对象中创建Flux序列
        Integer[] array = {1, 2, 3, 4};
        Flux.fromArray(array).subscribe(System.out::println);

        List<Integer> integers = Arrays.asList(array);
        Flux.fromIterable(integers).subscribe(System.out::println);

        Stream<Integer> stream = integers.stream();
        Flux.fromStream(stream).subscribe(System.out::println);
        Mono.fromSupplier(() -> "Hello").subscribe(System.out::println);
        Mono<Void> mono = Mono.fromDirect(new MyPublisher()).then();
        mono.subscribe(a -> {
            System.out.println(123);
        });
    }
}
