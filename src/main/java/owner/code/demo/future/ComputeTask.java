package owner.code.demo.future;

import cn.hutool.core.date.DateUtil;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * @description: 创建一个计算任务类，实现Callable接口,重写call方法
 * @author: sxl
 * @create: 2023-06-02 22:06
 */
public class ComputeTask implements Callable<Integer> {

    private String taskName;//任务名称

    //任务构造器
    public ComputeTask(String taskName) {
        this.taskName = taskName;
        //System.out.println("创建【计算任务】开始，计算任务名称：" + taskName);
    }

    //计算任务的方法
    @Override
    public Integer call() throws Exception {
        Integer result = 0;
        for (int i = 1; i <= 50; i++) {
            result = +i;
        }
        //模拟线程耗时5s
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(DateUtil.format(new Date(),"yyyy-MM-dd mm:hh:ss") + Thread.currentThread().getName()+"【计算任务】" + taskName + "执行完成。");
        return result;
    }
}
