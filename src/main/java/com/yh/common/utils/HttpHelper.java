//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.yh.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import javax.servlet.ServletRequest;

public class HttpHelper {
    public HttpHelper() {
    }

    public static String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;

        try {
            InputStream inputStream = request.getInputStream();
            Throwable var4 = null;

            try {
                reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));

                String line;
                while((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (Throwable var28) {
                var4 = var28;
                throw var28;
            } finally {
                if (inputStream != null) {
                    if (var4 != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable var27) {
                            var4.addSuppressed(var27);
                        }
                    } else {
                        inputStream.close();
                    }
                }

            }
        } catch (IOException var30) {
            System.out.println("getBodyString出现问题！");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException var26) {
                    System.out.println(var26.getMessage());
                }
            }

        }

        return sb.toString();
    }
}
