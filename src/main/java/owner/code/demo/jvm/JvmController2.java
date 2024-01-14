package owner.code.demo.jvm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jvm")
public class JvmController2 {

    private static Logger logger = LoggerFactory.getLogger(JvmController.class);
    /**
     * 最小的单位
     */
    private static final int UNIT_MB = 1024 * 1024;

    @GetMapping("/test")
    public void test() {
        logger.info("test success");
    }

    @GetMapping("/jvm")
    public void jvm() {
        jvmTest();
    }

    public void jvmTest() {
        getJvmInfo();
        int count = 1;
        List caches = new ArrayList();
        logger.info("--初始化时已用堆值:" + ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed() / 1024 + "k");
        for (int i = 1; i <= 12; i++) {
            if (i == 11) {
                logger.info("--caches准备添加第11次,old区内存不够，开始full GC 前先执行minor GC 第" + 5 + "次,FGC 第1次(触发条件：【MinorGC后存活的对象超过了老年代剩余空间】)");
            }
            caches.add(new byte[3 * UNIT_MB]);
            if (i % 2 == 0 && i != 10) {
                logger.info("--caches添加第" + i + "次后，eden + survivor 的内存不够，开始minor GC 第" + count + "次");
                count++;
            } else {
                logger.info("--caches添加第" + i + "次");
            }
        }
        logger.info("目前整个堆内存已经36m多，Young区6M多，Old区最大值为32M");
        caches.remove(0);//释放空间，重新添加 ,如果不释放空间，会报错：java.lang.OutOfMemoryError: Java heap space 【这里这样做，主要为了防止数组对象实际大小超过堆大小】
        logger.info("--FGC开始 第2次（触发条件：晋升到老年代的大小超过了老年代剩余大小）");
        caches.add(new byte[3 * UNIT_MB]);
        logger.info("本次FGC，移植了Young区的一部分到Old区，导致Young区还有3M左右");
        for (int i = 0; i < 8; i++) {//这里是为了下次FGC后，直接减少老年代的内存大小，从而正常YGC
            caches.remove(0);
        }
        logger.info("--FGC开始 第3次(触发条件:同上)");
        caches.add(new byte[3 * UNIT_MB]);

        for (int i = 0; i < 6; i++) {
            caches.add(new byte[3 * UNIT_MB]);
        }
    }

    /**
     * 通过代码打印程序的堆、内存信息
     */
    public static void getJvmInfo() {
        logger.info("-----------------------JVM-Info-start----------------");

        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage mu = memoryMXBean.getHeapMemoryUsage();

        logger.info("heapInfo:" + mu);
        logger.info("初始化堆:" + mu.getInit() / 1024 / 1024 + "Mb");
        logger.info("最大堆值:" + mu.getMax() / 1024 / 1024 + "Mb");
        logger.info("已用堆值:" + mu.getUsed() / 1024 / 1024 + "Mb");

        MemoryUsage none = memoryMXBean.getNonHeapMemoryUsage();
        logger.info("non-heap Info(非堆内存):" + none);


        List<String> args = ManagementFactory.getRuntimeMXBean().getInputArguments();
        logger.info("运行时VM参数:" + args);

        logger.info("运行时总内存" + Runtime.getRuntime().totalMemory() / 1024 / 1024);
        logger.info("运行时空闲内存" + Runtime.getRuntime().freeMemory() / 1024 / 1024);
        logger.info("运行时最大内存" + Runtime.getRuntime().maxMemory() / 1024 / 1024);

        logger.info("-----------------------JVM-Info-end----------------");
        logger.info("--");
        logger.info("--");
        logger.info("--");
    }
}
