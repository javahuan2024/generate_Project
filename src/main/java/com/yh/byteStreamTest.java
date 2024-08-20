package com.yh;

import com.alibaba.druid.sql.visitor.functions.Char;

import java.io.*;

/**
 * ClassName: byteStreamTest
 * Description:
 *
 * @Author 计科-杨欢
 * @Create 2024/8/14 17:35
 * @Version 1.0
 */
public class byteStreamTest {
    public static void main(String[] args) throws IOException {
        File file = new File("E:\\a.txt");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            sb.append("一览众山小");
        }


        FileOutputStream fileOutputStream = new FileOutputStream(file);
        long start = System.currentTimeMillis();
        fileOutputStream.write(sb.toString().getBytes());
        System.out.println("字节流输入耗费时间"+(System.currentTimeMillis() - start)+"ms");
        fileOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        StringBuilder sbb = new StringBuilder();
        int len = 0;

        long start1 = System.currentTimeMillis();
        while ((len = fileInputStream.read(bytes)) != -1) {
            sbb.append(new String(bytes, 0, len));
        }
        System.out.println("字节流输出耗费时间"+(System.currentTimeMillis() - start1)+"ms");
        //System.out.println(sbb);
        fileInputStream.close();
        file.delete();

    }
}
