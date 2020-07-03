package com.nn.design.factoryMethod;

/**
 * @author huyuening@mozihealthcare.cn
 * @version 1.0.0
 * @Date 2020/6/29 12:51
 * @Description
 */
public class SteamedBunFactory extends Factory {
    //馒头工厂，只需要提供馒头机器就行
    @Override
    public MachineApi newMachine() {
        return new SteamedBunMachine();
    }
}
