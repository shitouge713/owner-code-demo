package owner.code.demo.future;

import cn.hutool.core.date.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * FutureTask 使用示例
 */
public class FutureTaskDemo {
    public static void main(String[] args) {
        //创建固定长度的线程池
        ExecutorService pool = Executors.newFixedThreadPool(5);
        //任务集合
        List<FutureTask<Integer>> futureTasks = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            //实例化FutureTask，传入计算任务类
            FutureTask<Integer> futureTask = new FutureTask<>(new ComputeTask(i + ""));
            //添加到任务集合中
            futureTasks.add(futureTask);
            //提交任务到线程池
            pool.submit(futureTask);
        }
        System.out.println("所有【计算任务】提交完毕，主线程开始执行============");
        //主线程睡眠5秒，模拟主线程做某些任务
        try {
            System.out.println("【主线程任务】开始============");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(DateUtil.format(new Date(),"yyyy-MM-dd mm:hh:ss")+","+"【主线程任务】结束============");
        //用于打印任务执行结果
        Integer result = 0;
        for (FutureTask<Integer> task : futureTasks) {
            try {
                //FutureTask的get()方法会自动阻塞，知道得到任务执行结果为止
                result += task.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        //关闭线程池
        pool.shutdown();
        System.out.println(DateUtil.format(new Date(),"yyyy-MM-dd mm:hh:ss") +"多线程多任务执行结果：" + result);
    }
}
