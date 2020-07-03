package com.nn.juc.lambda;

/**
 * 2020/4/23 11:11
 */
public class User {
    public User(String userName, Integer age) {
        this.userName = userName;
        this.age = age;
    }

    public User() {
    }

    private String userName;
    private Integer age;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
