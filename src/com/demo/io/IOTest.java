package com.demo.io;

import java.io.*;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 阻塞io测试
 * @date 2021/2/8 19:26
 * @see
 */
public class IOTest {


    /**
     * 读进来、写出去；
     * 单个字节的读写，单个字符的读写----效率太低
     */
    public static void main(String[] args) throws Exception {
//        ===============》》》 字节流
        // 文件IO
//        testFileIO();
        // 带缓冲区IO
//        testBufferIO();
        // 字节数组IO
        testByteArrayIO();
        //
        PipedInputStream pipedInputStream = new PipedInputStream();
        // ============>>> 字符流
//        todo ...
//        Reader reader = new BufferedReader();
//        InputStreamReader
//                FileReader
//        StringReader
//                CharArrayReader
//        LineNumberReader
//
//        Writer writer = new BufferedWriter();
        String fileName = "";
        FileWriter fileWriter = new FileWriter(fileName);
        // 字符流写
        fileWriter.write("abssfsfsfsasa");

        StringWriter stringWriter = new StringWriter();
        StringWriter append = stringWriter.append("abcsdfsd").append("sfsd哈哈哈");
        append.write("hahahha");
        append.flush();

        CharArrayWriter charArrayWriter = new CharArrayWriter();
//        charArrayWriter.writeTo();
        charArrayWriter.write("abcd");

    }

    /**
     * ByteArrayOutputStream类实现了一个输出流，其中的数据被写入一个 byte 数组。缓冲区会随着数据的不断写入而自动增长。
     * 可使用 toByteArray()和 toString()获取数据
     */
    private static void testByteArrayIO() throws Exception {
        byte[] bytes = "abcdsfsfsdaaa12".getBytes();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // 输入流和输出流连通
        int readSize;
        // 单个字节的读入到流中
        while ((readSize = byteArrayInputStream.read()) != -1) {
            // 单个字节的写出
            byteArrayOutputStream.write(readSize);
        }

//        OutputStream out = new FileOutputStream("");
//        // 写入到out输出流
//        byteArrayOutputStream.writeTo(out);
        // 获取流中数据
        byte[] byteContent = byteArrayOutputStream.toByteArray();
        String strContent = byteArrayOutputStream.toString();
        System.out.println("输出流中数据：" + strContent);
    }

    /**
     * 　　BufferedInputStream继承于FilterInputStream，提供缓冲输入流功能。缓冲输入流相对于普通输入流的优势是
     * ，它提供了一个缓冲数组，每次调用read方法的时候，它首先尝试从缓冲区里读取数据，
     * 若读取失败（缓冲区无可读数据），则选择从物理数据源（譬如文件）读取新数据（
     * 这里会尝试尽可能读取多的字节）放入到缓冲区中，最后再将缓冲区中的内容部分或全部返回给用户.
     * 由于从缓冲区里读取数据远比直接从物理数据源（譬如文件）读取速度快
     */
    private static void testBufferIO() throws Exception {
        InputStream in = new FileInputStream("D:\\IdeaProjects\\java-basic\\src\\resources\\iotestRead.txt");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(in);
        byte[] buffer = new byte[1024];
        int readBytes;
        StringBuilder sb = new StringBuilder();
        while ((readBytes = bufferedInputStream.read(buffer)) != -1) {
            sb.append(new String(buffer, 0, readBytes));
        }
        System.out.println("========>>流中字符串：" + sb.toString());

        OutputStream out = new FileOutputStream("D:\\IdeaProjects\\java-basic\\src\\resources\\iotestbufferWrite.txt");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(out);
        bufferedOutputStream.write(sb.toString().getBytes());
        bufferedOutputStream.flush();

    }

    private static void testFileIO() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("D:\\IdeaProjects\\java-basic\\src\\resources\\iotestRead.txt");
        byte[] buffer = new byte[1024];
        // 读到流结尾
        while (fileInputStream.read() != -1) {
            // 单个字节的读,写入到buffer中
            fileInputStream.read(buffer);
        }
        String inStr = new String(buffer);
        System.out.println("==========>>流中数据:" + inStr);

        // 要写入的文件路径
        String fileOutPath = "D:\\IdeaProjects\\java-basic\\src\\resources\\iotestWrite.txt";
        FileOutputStream fileOutputStream = new FileOutputStream(fileOutPath);

        byte[] content = inStr.getBytes();
        // 按字节写，阻塞
        fileOutputStream.write(content);
        fileOutputStream.flush();
    }


}
