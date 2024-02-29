package owner.code.demo.async;

import lombok.extern.slf4j.Slf4j;
import owner.code.demo.function.FunctionChildren;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 *
 */
@Slf4j
public class CompletableFutureDemo01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFutureDemo01 demo = new CompletableFutureDemo01();
        //demo.completedFutureMethod();
        //demo.thenRunMethod();
        //demo.thenAcceptMethod();
        //demo.thenApplyMethod();
        //demo.exceptionallyMethod();
        //demo.whenCompleteMethod();
        //demo.handleMethod();
        //demo.allOfMethod();
        demo.thenComposeMethod();
    }

    /**
     * completedFuture 直接创建一个已完成的结果
     * get()/join()，阻塞获取结果，get会抛出异常，join不会
     */
    public void completedFutureMethod() {
        CompletableFuture<String> future = CompletableFuture.completedFuture("Hello World");
        System.out.println(future.join());
    }

    /**
     * thenRun(Runnable action)/thenRunAsync(Runnable action)
     * 异步任务回调，做完第一个任务，再做第二个任务
     * 区别是第二个任务是Runnable类型，不能再使用CompletableFuture
     * 且两个任务间没有参数传递
     */
    public void thenRunMethod() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行第一个任务");
            return null;
        });
        future1.thenRun(() -> {
            System.out.println("接着执行第二个任务");
        });
    }

    /**
     * thenAccept(Consumer action)/thenAcceptAsync
     * 第一个任务执行完成后，执行第二个回调方法任务，
     * 会将该任务的执行结果，作为入参，传递到回调方法中，但是回调方法是没有返回值的
     * thenApply/thenApplyAsync
     */
    public void thenAcceptMethod() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            return "第一个任务结果";
        });
        future1.thenAccept((a) -> {
            System.out.println(a);
        });
    }

    /**
     * thenApply/thenApplyAsync
     * 第一个任务执行完成后，执行第二个回调方法任务，
     * 会将该任务的执行结果，作为入参，传递到回调方法中，但是回调方法有返回值
     */
    public void thenApplyMethod() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            return "第一个任务结果";
        });
        CompletableFuture<String> future2 = future1.thenApply((a) -> {
            System.out.println(a);
            return "第二个任务结果";
        });
        System.out.println(future2.join());
    }

    /**
     * exceptionally
     * 某个任务执行异常时，执行的回调方法;并且有抛出异常作为参数，传递到回调方法。
     */
    public void exceptionallyMethod() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            return "第一个任务结果";
        });
        CompletableFuture<String> future2 = future1.exceptionally((e) -> {
            e.printStackTrace();
            log.error("fatalError,的程序异常啦");
            return "你的程序异常啦";
        });
    }

    /**
     * whenComplete(BiConsumer consumer)
     * 某个任务执行完成后，执行的回调方法，回调方法无返回值
     * 并且whenComplete有返回值，返回值是上个任务的结果
     */
    public void whenCompleteMethod() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            return "第一个任务结果";
        });
        CompletableFuture<String> future2 = future1.whenComplete((a, throwable) -> {
            System.out.println("当前线程名称：" + Thread.currentThread().getName());
            System.out.println("上个任务执行完啦，还把" + a + "传过来");
        });
        System.out.println(future2.join());
    }

    /**
     * handle(BiFunction function)
     * 某个任务执行完成后，执行回调方法，并且是有返回值
     * 并且handle方法返回的CompletableFuture的result是回调方法执行的结果
     */
    public void handleMethod() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            return "第一个任务结果";
        });
        CompletableFuture<String> future2 = future1.handle((a, throwable) -> {
            System.out.println("当前线程名称：" + Thread.currentThread().getName());
            System.out.println("上个任务执行完啦，还把" + a + "传过来");
            return "第二个任务结果";
        });
        System.out.println(future2.join());
    }

    /**
     * supplyAsync/runAsync 创建异步任务
     * supplyAsync 有返回值
     * runAsync 没有返回值
     * thenCombine / thenAcceptBoth / runAfterBoth
     * 将两个CompletableFuture组合起来，只有这两个都正常执行完了，才会执行某个任务。
     * thenCombine：会将两个任务的执行结果作为方法入参，传递到指定方法中，且有返回值
     * thenAcceptBoth: 会将两个任务的执行结果作为方法入参，传递到指定方法中，且无返回值
     * runAfterBoth 不会把执行结果当做方法入参，且没有返回值。
     */
    public void thenCombineMethod() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            return "Hello";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            return "World";
        });
        CompletableFuture<String> result = future1.thenCombine(future2, (future1Result, future2Result) -> future1Result + " " + future2Result);
        result.whenComplete((a, throwable) -> {
            System.out.println("当前线程名称：" + Thread.currentThread().getName());
            System.out.println(a);
        });
        //等待异步任务完成并获取结果
        result.join();
    }

    /**
     * applyToEither / acceptEither / runAfterEither
     * 将两个CompletableFuture组合起来，只要其中一个执行完了,就会执行某个任务。
     * applyToEither：会将已经执行完成的任务，作为方法入参，传递到指定方法中，且有返回值
     * acceptEither: 会将已经执行完成的任务，作为方法入参，传递到指定方法中，且无返回值
     * runAfterEither： 不会把执行结果当做方法入参，且没有返回值。
     */
    public void applyToEitherMethod() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            return 2;
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 3;
        });
        CompletableFuture<Integer> result = future2.applyToEither(future1, new FunctionChildren());
        //等待异步任务完成并获取结果
        System.out.println(result.join());
    }

    /**
     * AllOf
     * 所有任务都执行完成后，才执行allOf返回的CompletableFuture。
     * 如果任意一个任务异常，allOf的CompletableFuture，执行get方法，会抛出异常
     */
    public void allOfMethod() {
        CompletableFuture<Void> a = CompletableFuture.runAsync(()->{
            System.out.println("a执行完了");
        });
        CompletableFuture<Void> b = CompletableFuture.runAsync(() -> {
            System.out.println("b也执行完了");
        });
        CompletableFuture<Void> c = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("c也执行完了");
        });
        CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(a, b, c).whenComplete((m,k)->{
            System.out.println("finish");
        });
        allOfFuture.join();
    }

    /**
     * AnyOf
     * 任意一个任务执行完，就执行anyOf返回的CompletableFuture。
     * 如果执行的任务异常，anyOf的CompletableFuture，执行get方法，会抛出异常
     */
    public void anyOfMethod() {
        CompletableFuture<Void> a = CompletableFuture.runAsync(()->{
            System.out.println("a执行完了");
        });
        CompletableFuture<Void> b = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("b也执行完了");
        });
        CompletableFuture<Void> c = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("c也执行完了");
        });
        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(a, b, c).whenComplete((m, k)->{
            System.out.println("finish");
        });
        anyOfFuture.join();
    }

    /**
     * thenCompose
     * thenCompose方法会在某个任务执行完成后，将该任务的执行结果,作为方法入参,去执行指定的方法。
     * 该方法会返回一个新的CompletableFuture实例
     */
    public void thenComposeMethod() {
        CompletableFuture<String> a = CompletableFuture.supplyAsync(() -> {
            return "a执行完了";
        });
        CompletableFuture<String> b = CompletableFuture.supplyAsync(() -> {
            return "b执行完了";
        });
        CompletableFuture<String> result = b.thenCompose(data -> {
            System.out.println(data);
            System.out.println(a.join() + b.join());
            return a;
        });
        result.join();
    }

}
