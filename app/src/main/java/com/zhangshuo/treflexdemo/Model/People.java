package com.zhangshuo.treflexdemo.Model;

import android.text.TextUtils;

import com.zhangshuo.treflexdemo.Reflex.LogUtils;

public class People implements IPeople {
    private String name;
    private int age;
    private static String carName = "oooo";

    public People() {
    }

    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //行为
    @Override
    public boolean eat(String something) {
        LogUtils.i(name + " eat " + something);
        return TextUtils.isEmpty(something) || something.length() < 2;
    }

    //静态行为
    public static boolean play(String other) {
        LogUtils.i(other + " play " + carName);
        return TextUtils.isEmpty(other) || other.length() < 2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +","+carName+
                '}';
    }
}
