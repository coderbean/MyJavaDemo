package coderbean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by jishipu on 16/12/6.
 *
 * 用来压缩和解压字符串
 */
public class ZipHelper {
    private ZipHelper() {
    }

    /**
     * 压缩字符串
     *
     * @param str 待压缩的字符串
     * @return 压缩后的字符串
     * @throws Exception 压缩过程中的异常
     */
    public static String compress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        // ByteArrayOutputStream 和 ByteArrayInputStream 是一个虚拟的流,
        // JDk源码中关闭方法是空的, 所以无需关闭, 为了代码整洁，还是放到了try-with-resource里面
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             GZIPOutputStream gzip = new GZIPOutputStream(out)) {
            gzip.write(str.getBytes());
            gzip.finish();
            return out.toString("ISO-8859-1");
        }
    }

    /**
     * 解压字符串
     *
     * @param str 待解压的字符串
     * @return 解压后的字符串
     * @throws Exception 解压过程中的异常
     */
    public static String uncompress(String str) throws IOException {
        if (str == null || str.length() == 0) {
            return str;
        }
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
             GZIPInputStream gunzip = new GZIPInputStream(in)) {
            byte[] buffer = new byte[1024];
            int n;
            while ((n = gunzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
            return out.toString();
        }
    }
}