package com.nn.design.factoryMethod;

/**
 * @author huyuening@mozihealthcare.cn
 * @version 1.0.0
 * @Date 2020/6/29 12:53
 * @Description
 */
public class Test {
    public static void main(String[] args) {
        SteamedBunFactory mSteamedBunFactory  = new SteamedBunFactory ();
        mSteamedBunFactory.process("面粉");//我把面粉加工成了馒头
    }
}
