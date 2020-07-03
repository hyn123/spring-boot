package com.nn.design.factoryMethod;

/**
 * @author huyuening@mozihealthcare.cn
 * @version 1.0.0
 * @Date 2020/6/29 12:45
 * @Description 面条机器
 */
public class BoodleMachine implements MachineApi {
    @Override
    public void process(String material) {
        System.out.println("面条机器加工："+material);
    }
}
