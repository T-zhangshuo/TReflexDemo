package com.zhangshuo.treflexdemo;

import com.zhangshuo.treflexdemo.Reflex.LogUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class IPeopleProxy implements InvocationHandler {
    private Object realObject;

    public IPeopleProxy(Object realObject) {
        this.realObject = realObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LogUtils.i("动态代理方法:" + method.getName());
        if (method.getName().equals("eat")) {
            return true;
        }
        return method.invoke(realObject, args);
    }
}
