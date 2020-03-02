package com.leeue.test01;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author liyue
 * @date 2020/2/11 12:32
 */
public class Test01 {

    public static void main(String[] args) {

        Test01 test01 = new Test01();

        test01.testInteger();

        LocalDateTime localDateTime = LocalDateTime.now().plusDays(-1);

        System.out.println(localDateTime);


        List<String> a = new ArrayList<>();

        a.add("aa");
        a.add("bb");
        a.add("cc");

        List<String> cc = a.stream().filter(b -> b.equals("cc")).collect(Collectors.toList());

        System.out.println(cc);

        System.out.println(a);
    }


    /**
     * Integer 缓存区间是 -127-128 所以 == 比较的是
     */
    public void testInteger() {

        Integer i = 666;
        Integer j = 666;

        System.out.println("666值比较结果" + (i == j));

        Map map = new HashMap();

        map.put("zhang", "a");
    }


}
