package com.leeue.zk;

/**
 * @author liyue
 * @date 2019/8/28 14:27
 */
public class Test002 extends   AbTest {
    @Override
    void add() {
        System.out.println("我是子类 执行add方法了");
    }

    @Override
    void remove() {
        System.out.println("我是子类 执行remove方法了");
    }

    public static void main(String[] args) {
      new Test002().get();
    }
}
