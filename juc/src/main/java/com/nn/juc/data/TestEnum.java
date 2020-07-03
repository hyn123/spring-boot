package com.nn.juc.data;

/**
 * 2020/4/28 12:00
 */
public enum TestEnum {

    ABC("01","abc"),

    EFG("02","dfg");
    private String code;

    private String name;

    TestEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    private void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
}
