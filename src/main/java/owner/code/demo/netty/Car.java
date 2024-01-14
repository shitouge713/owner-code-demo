package owner.code.demo.netty;

import com.alibaba.dubbo.common.extension.SPI;

@SPI
public interface Car {
    public void driver();
}
