package com.nn.design.factoryMethod;

/**
 * @author huyuening@mozihealthcare.cn
 * @version 1.0.0
 * @Date 2020/6/29 12:44
 * @Description 馒头机器
 */
public class SteamedBunMachine implements MachineApi {
    @Override
    public void process(String material) {
        System.out.println("馒头机器加工："+material);
    }
}
