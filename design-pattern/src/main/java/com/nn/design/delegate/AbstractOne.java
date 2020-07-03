package com.nn.design.delegate;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author huyuening@mozihealthcare.cn
 * @version 1.0.0
 * @Date 2020/6/5 11:36
 * @Description 抽象类一
 */
public abstract class AbstractOne {
    protected abstract void refreshBeanFactory();

    protected void obtainFreshBeanFactory() {
        //这里使用了委派设计模式，父类定义了抽象的refreshBeanFactory()方法，
        // 具体实现调用子类容器的refreshBeanFactory()方法
        refreshBeanFactory();
    }
}
