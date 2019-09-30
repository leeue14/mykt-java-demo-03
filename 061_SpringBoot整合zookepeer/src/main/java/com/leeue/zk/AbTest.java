package com.leeue.zk;

/**
 * 模板方法抽象类
 *
 * @author liyue
 * @date 2019/8/28 14:22
 */
public abstract  class AbTest {

    //把重复的代码抽象在父类。
     void get(){
         System.out.println("开始执行get方法，这一块以后写共同业务的代码");
         add();
         remove();
         sys();
    }

    abstract void add();
    abstract  void remove();

    void sys(){
        System.out.println("我是最后执行的方法，可以写共同业务的代码");
    }
}
