package com.nn.design.factoryMethod;

/**
 * @author huyuening@mozihealthcare.cn
 * @version 1.0.0
 * @Date 2020/6/29 12:48
 * @Description
 */
public abstract class Factory {
    /**
     * 让子类（具体工厂）来实例化具体对象（机器）
     */
    public abstract MachineApi newMachine();

    /**
     * 加工材料
     */
    public void process(String material){
        MachineApi machine = newMachine();
        machine.process(material);
    }
}
