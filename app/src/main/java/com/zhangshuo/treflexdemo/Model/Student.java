package com.zhangshuo.treflexdemo.Model;

public class Student extends People {
    public Student() {
        setName("学生");
        setAge(19);
    }

    private Student(String name, int age) {
        setName(name);
        setAge(age);
    }
}
