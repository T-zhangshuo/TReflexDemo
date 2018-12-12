package com.zhangshuo.treflexdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zhangshuo.treflexdemo.Model.IPeople;
import com.zhangshuo.treflexdemo.Model.People;
import com.zhangshuo.treflexdemo.Model.Student;
import com.zhangshuo.treflexdemo.Reflex.LogUtils;
import com.zhangshuo.treflexdemo.Reflex.TReflex;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //测试反射
    public void testReflex(View view) {
        //测试获取示例
        Student student = new Student();
        boolean isEat = student.eat("苹果");
        boolean isPlay = Student.play("张三");
        LogUtils.i("正常结果:" + student.toString() + " eat:" + isEat + " play:" + isPlay);

        //通过反射获取例子代码
        Student student1 = (Student) TReflex.newInstance("com.zhangshuo.treflexdemo.Model.Student");
        boolean isEat1 = student.eat("苹果");
        boolean isPlay1 = Student.play("张三");
        LogUtils.i("反射实例（无参数):" + student1.toString() + " eat:" + isEat1 + " play:" + isPlay1);

        //通过反射获取例子代码
        Class[] paramTypes = new Class[]{String.class, int.class};
        Object[] valueTypes = new Object[]{"学生(反射)", 20};
        Student student2 = (Student) TReflex.newInstance("com.zhangshuo.treflexdemo.Model.Student", paramTypes, valueTypes);
        boolean isEat2 = student.eat("苹果");
        boolean isPlay2 = Student.play("张三");
        LogUtils.i("反射实例（有参数):" + student2.toString() + " eat:" + isEat2 + " play:" + isPlay2);

        //反射获取字段值
        String name = (String) TReflex.getField(student, "name");
        String nameStatic = (String) TReflex.getFieldStatic(Student.class, "carName");
        LogUtils.i("反射获取字段:" + "私有字段：" + name + " 静态字段:" + nameStatic);

        //反射设置字段
        TReflex.setFieldValue(student, "name", "姓名(反射)");
        TReflex.setFieldStatic(student, "carName", "-----");
        LogUtils.i("反射设置字段:" + student.toString());

        //反射执行方法
        String name3 = (String) TReflex.invokeMethod(student, "getName");
        Class[] pTypes = new Class[]{int.class};
        Object[] pValues = new Object[]{200};
        TReflex.invokeMethod(student, "setAge", pTypes, pValues);
        //反射静态方法
        Class[] methodParamTypes = new Class[]{String.class};
        Object[] methodParamValues = new Object[]{"李四"};
        TReflex.invokeMethodStatic(student, "play", methodParamTypes, methodParamValues);
        LogUtils.i("反射方法:结果:" + name3 + " 设置方法:" + student.toString());

        //动态代理-只能接口的形式。
        People people = new People();
        LogUtils.i("动态代理:未代理结果:" + people.eat("吃东西"));
        IPeople iPeople = (IPeople) TReflex.invokeMethodProxy(People.class, new IPeopleProxy(people));
        LogUtils.i("动态代理:已代理结果:" + iPeople.eat("吃东西"));
    }

}

