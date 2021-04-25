package com.lx.test;

import java.lang.reflect.Method;

public class UserServletTest {

    public void add(){
        System.out.println("add方法执行了");
    }
    public void delete(){
        System.out.println("delete方法执行了");
    }
    public void updata(){
        System.out.println("updata方法执行了");
    }
    public void show(){
        System.out.println("show方法执行了");
    }

    public static void main(String[] args) {
        String action = "add";
        try {
            //获取action业务鉴别字符串  ，  获取相应的业务，反射的对象
            Method method = UserServletTest.class.getMethod(action);
//            System.out.println(method);//public void com.lx.test.UserServletTest.add()
            //调用目标业务方法
            //method.invoke(new UserServletTest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
