package com.nn.juc.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 2020/4/23 11:13
 */
public class StreamTest {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
        test4();
    }

    /**
     * sorted
     * 排序
     */
    public static void test4() {
        List<User> users = getUsers();
        users.stream().sorted(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o2.getAge()-o1.getAge();
            }
        }).forEach(User::getAge);

    }

    /**
     * reduce
     * 实现对年纪的加减乘除操作，最后返回值
     */
    public static void test3() {
        List<User> users = getUsers();
        Optional<Integer> reduce = users.stream().map(User::getAge).reduce((x, y) -> x * y);
        System.out.println(reduce.get());
    }

    /**
     * filter
     */
    public static void test2() {
        List<User> users = getUsers();
        users.stream().filter(son -> son.getAge() > 5).forEach(System.out::println);
    }

    /**
     * 流转换为其他数据结构
     *
     * @return
     */
    public static void test1() {
        List<User> users = getUsers();
        //把users转换成只有name
        List<String> collect = users.stream().map(son -> {
            return son.getUserName();
        }).collect(Collectors.toList());
        System.out.println(collect);
    }


    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(new User("zhangsan" + i, i));
        }
        return users;
    }

}
