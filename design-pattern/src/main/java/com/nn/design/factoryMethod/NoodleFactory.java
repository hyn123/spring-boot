package com.nn.design.factoryMethod;

/**
 * @author huyuening@mozihealthcare.cn
 * @version 1.0.0
 * @Date 2020/6/29 12:52
 * @Description
 */
public class NoodleFactory extends Factory {
    //面条工厂，只需要提供面条机器就行
    @Override
    public MachineApi newMachine() {
        return new BoodleMachine();
    }
}
