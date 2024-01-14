package owner.code.demo.async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 我们得到一个ID列表，我们希望从中获取一个名称和一个统计信息，并将它们成对地组合起来，所有这些都是异步的。
 * 以下示例使用CompletableFuture类型的列表执行此操作：
 */
public class CompletableFutureDemo04 {

    public static void main(String[] args) {
        CompletableFutureDemo04 demo = new CompletableFutureDemo04();
        demo.method();
    }

    public void method() {
        /**
         * 1.我们从一个Future开始，这个Future给了我们一个要处理的id值列表。
         * 2.一旦得到列表，我们希望开始更深入的异步处理。
         * 3.对于列表中的每个元素：
         * 4.异步获取关联的名称。
         * 5.异步获取关联的任务。
         * 6.将两种结果结合起来。
         * 7.我们现在有一个代表所有组合任务的Furture列表。要执行这些任务，我们需要将列表转换为数组。
         * 8.将数组传递给CompletableFuture。allOf，它输出一个在所有任务完成后完成的Future。
         * 9.棘手的一点是allOf返回CompletableFuture<Void>，因此我们在期货列表上重申，使用join（）收集它们的结果（在这里，join（）不会阻止，因为allOf确保Future全部完成）。
         * 10.一旦整个异步管道被触发，我们将等待它被处理，并返回结果列表。
         */
        CompletableFuture<List<String>> ids = ifhIds();
        CompletableFuture<List<String>> result = ids.thenComposeAsync(l -> {
            Stream<CompletableFuture<String>> zip =
                    l.stream().map(i -> {
                        CompletableFuture<String> nameTask = ifhName(i);
                        CompletableFuture<Integer> statTask = ifhStat(i);
                        return nameTask.thenCombineAsync(statTask, (name, stat) -> "Name " + name + " has stats " + stat);
                    });
            List<CompletableFuture<String>> combinationList = zip.collect(Collectors.toList());
            CompletableFuture<String>[] combinationArray = combinationList.toArray(new CompletableFuture[combinationList.size()]);
            CompletableFuture<Void> allDone = CompletableFuture.allOf(combinationArray);
            return allDone.thenApply(v -> combinationList.stream()
                    .map(CompletableFuture::join)
                    .collect(Collectors.toList()));
        });
        List<String> results = result.join();
        System.out.println(results);
    }

    public CompletableFuture<List<String>> ifhIds() {
        CompletableFuture result = CompletableFuture.supplyAsync(() -> {
            List list = new ArrayList();
            list.add("1");
            list.add("2");
            list.add("3");
            return list;
        });
        return result;
    }

    public CompletableFuture<String> ifhName(String name) {
        CompletableFuture result = CompletableFuture.supplyAsync(() -> {
            return name;
        });
        return result;
    }

    public CompletableFuture<Integer> ifhStat(String id) {
        CompletableFuture result = CompletableFuture.supplyAsync(() -> {
            return Integer.valueOf(id);
        });
        return result;
    }
}
