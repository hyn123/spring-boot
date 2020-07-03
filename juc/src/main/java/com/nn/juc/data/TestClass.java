package com.nn.juc.data;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: hyn
 * @Date: 2020-01-16 13:53
 */
public class TestClass {
    public static void main(String[] args) throws InterruptedException {
        /*LinkedListTest<String> linkedListTest = new LinkedListTest<>();
        linkedListTest.add("123");
        linkedListTest.add("456");
        linkedListTest.add("789");
        while (linkedListTest.last!=null){
            System.out.println();
        }*/
        System.out.println(TestEnum.ABC.getCode());
        System.out.println(TestEnum.ABC.getName());
        Thread.sleep(600000);
    }

}
