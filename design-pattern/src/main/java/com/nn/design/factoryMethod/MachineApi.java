package com.nn.design.factoryMethod;

/**
 * @author huyuening@mozihealthcare.cn
 * @version 1.0.0
 * @Date 2020/6/29 12:43
 * @Description
 * 首先无论是做馒头还是挂面，他们都有一个加工方法，可以抽象出来
 *  Machine：机器
 */
public interface MachineApi {
    //process：加工      material:材料
    public void process(String material);
}
