package com.yh;

import java.io.*;

/**
 * ClassName: IOtest
 * Description:
 *
 * @Author 计科-杨欢
 * @Create 2024/8/14 17:14
 * @Version 1.0
 */
public class IOtest {
        public static void main(String[] args) throws IOException {
            File file = new File("E:\\a.txt");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 1000000; i++) {
                sb.append("一览众山小");
            }

            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file));
            long start = System.currentTimeMillis();
            writer.write(sb.toString());
            System.out.println("字符流输入耗费时间" + (System.currentTimeMillis() - start)+"ms");

            writer.close();

            InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
            char[] chars = new char[1024];
            int count;
            StringBuilder sbb = new StringBuilder();
            long start1 = System.currentTimeMillis();
            while ((count = reader.read(chars)) != -1) {
                sbb.append(chars, 0, count);
            }
            System.out.println("字符流输出耗费时间" + (System.currentTimeMillis() - start1)+"ms");
            reader.close();
            //System.out.println(sbb);


            file.delete();



    }
}
