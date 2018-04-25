package cn.lxb.test;

import cn.lxb.blog.utils.MD5Util;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.ArrayList;
import java.util.List;

/**
 * 递归
 * Created by 54LXB on 2017-06-22.
 */
public class Demo {

    // 递归调用，计算一个数的阶乘：
    @org.junit.Test
    public void Demo1() {
        System.out.println(count(5));
    }

    private int count(int number) {
        if (number == 1) {
            return 1;
        } else {
            return number*count(number-1);
        }
    }

    // 密码加密
    public static void main(String[] args) {
        String password = MD5Util.md5("54lxb970913.");
        String algorithmName = "md5";
        int hashIterations = 3;
        SimpleHash hash = new SimpleHash(algorithmName, password, null, hashIterations);
        String encodedPassword = hash.toHex();
        System.out.println(encodedPassword);
    }

    @org.junit.Test
    public void testSplit() throws Exception {
        List<String> list = new ArrayList<String>();
        list.add("姓名:小宝 爱好:旅游");
        list.add("姓名:大宝 爱好:学习");
        list.add("姓名:二宝 爱好:探险");
        for (int i=0; i<list.size(); i++) {
            String[] strs = list.get(i).split(" ");
            for (String str:strs) {
                System.out.print(str);
            }
            System.out.println();
        }
        String string = "姓名:小宝 爱好:旅游";
        String[] strs = string.split(" ");
        System.out.println(strs.length);
        System.out.println(System.getProperty("java.io.tmpdir"));
    }
}

/*
 * 执行过程分析：
 * 当number = 5时，number != 1，所以执行else语句，返回结果是 5*count(number-1);
 * 当number = 4时，number != 1，所以执行else语句，返回结果是 4*count(number-1);
 * 当number = 3时，number != 1，所以执行else语句，返回结果是 3*count(number-1);
 * 当number = 2时，number != 1，所以执行else语句，返回结果是 2*count(number-1);
 * 当number = 1时，number == 1，所以执行if语句，返回结果是 1*count(1)；
 * 相当于：5*count(4*count(3*count(2*count(count(1))))) = 120;
 */
