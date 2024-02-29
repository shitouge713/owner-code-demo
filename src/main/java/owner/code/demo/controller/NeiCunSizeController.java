package owner.code.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.util.RamUsageEstimator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
@RestController
@Api(tags = "NeiCunSizeController", consumes = "application/json")
public class NeiCunSizeController {

    private static final Integer UNIT_B = 1024;
    private static final ArrayBlockingQueue<byte[]> array;
    private static final LinkedBlockingQueue<byte[]> link;

    static {
        array = new ArrayBlockingQueue(10000);
        link = new LinkedBlockingQueue(10000);
        for (int i = 0; i < 10000; i++) {
            array.add(new byte[3 * UNIT_B]);
            link.add(new byte[3 * UNIT_B]);
        }
    }

    @GetMapping("/neiCunSize")
    @ApiOperation("查看内存大小")
    public void neiCunSize() {
        System.out.println("array sizeOf is " + RamUsageEstimator.sizeOf(array));
        System.out.println("array humanSizeOf is " + RamUsageEstimator.humanSizeOf(array));
        System.out.println("link sizeOf is " + RamUsageEstimator.sizeOf(link));
        System.out.println("link humanSizeOf is " + RamUsageEstimator.humanSizeOf(link));
    }
}
