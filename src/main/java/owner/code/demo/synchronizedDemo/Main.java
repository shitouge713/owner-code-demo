package owner.code.demo.synchronizedDemo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) throws Exception {
        HasSelfNum hasSelfNum = new HasSelfNum();
        Thread threadA = new Thread(hasSelfNum, "R1");
        Thread threadB = new Thread(hasSelfNum, "R2");
        threadA.start();
        threadB.start();
        //threadA.join();
        //threadB.join();
        log.info("最终num = {}", hasSelfNum.getNum());
    }

}
