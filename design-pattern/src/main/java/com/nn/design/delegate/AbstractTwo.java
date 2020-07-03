package com.nn.design.delegate;

/**
 * @author huyuening@mozihealthcare.cn
 * @version 1.0.0
 * @Date 2020/6/5 11:38
 * @Description
 */
public abstract class AbstractTwo extends AbstractOne {
    @Override
    protected void refreshBeanFactory() {
        System.out.println("刷新容器");
    }
}
