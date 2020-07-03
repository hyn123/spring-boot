package com.nn.design;

import com.nn.design.delegate.AbstractFive;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DesignApplicationTests {

    @Test
    void contextLoads() {
        AbstractFive five = new AbstractFive();
        five.test();
    }

}
