package zhiwenyan.cmccaifu.com.android2017.okhttp.okio;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

/**
 * Description:
 * Okio类作为OkIo库暴露给外部使用的类，提供大量的静态方法；
 * 其有两个关键的接口，Sink和Source，继承了Closeable接口；
 * Sink可以简单的看做OutputStream；->写操作!   ->通过一个Sink获得一个BufferedSink。
 * Source可以简单的看做InputStream。->读操作!	->通过一个Source获得BufferedSource，
 * Data：3/20/2018-10:17 AM
 *
 * @author: yanzhiwen
 */
public class OkioDemo {
    private static void read() {


        String fileName = "C:\\Users\\fumi_it1\\Desktop\\test.txt";
        Source source = null;
        BufferedSource bufferedSource = null;
        File file = new File(fileName);
        //读文件
        try {
            source = Okio.source(file);
            //通过source拿到bufferedSource
            bufferedSource = Okio.buffer(source);
            String read = bufferedSource.readString(Charset.forName("GBK"));
            System.out.println(read);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedSource != null) {
                try {
                    bufferedSource.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 新建文件并写入数据
     */
    private static void create_writer() {
        String filename = "create.txt";
        boolean isCreate = false;
        Sink sink;
        BufferedSink bSink = null;
        try {
            //判断文件是否存在，不存在，则新建！
            File file = new File(filename);
            if (!file.exists()) {
                isCreate = file.createNewFile();
            } else {
                isCreate = true;
            }
            //写入操作
            if (isCreate) {
                sink = Okio.sink(file);
                bSink = Okio.buffer(sink);
                bSink.writeUtf8("1");
                bSink.writeUtf8("\n");
                bSink.writeUtf8("this is new file!");
                bSink.writeUtf8("\n");
                bSink.writeString("我是每二条", Charset.forName("utf-8"));
                bSink.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bSink) {
                    bSink.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //Buffer 写操作：

    /**
     * 写buffer
     * 在okio中buffer是一个很重要的对象
     */
    public static void sinkFromOutputStream() {
        String filename = "create.txt";
        File file = new File(filename);
        //1.构建buffer对象
        Buffer data = new Buffer();
        //2.向缓冲中写入文本
        data.writeUtf8("afdsa");
        //3.可以连续追加，类似StringBuffer
        data.writeUtf8("b");
        //4.构建字节数组流对象
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //5.构建写缓冲池
//      Sink sink = Okio.sink(out);
        //6.向池中写入buffer
        try {
            Sink sink = Okio.sink(file);
            sink.write(data, 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Buffer 读操作：

    /**
     * 读buffer
     */
    public static void sourceFromInputStream() {
        //1.构建字节数组流
        try {
            InputStream in = new ByteArrayInputStream(("adasfdsaf").getBytes());
            //2.缓冲源
            Source source = Okio.source(in);
            //3.buffer
            Buffer sink = new Buffer();
            source.read(sink, in.read());
            //4.将数据读入buffer
            System.out.print(sink.readUtf8());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        OkioDemo.read();
        OkioDemo.create_writer();
        OkioDemo.sinkFromOutputStream();
        OkioDemo.sourceFromInputStream();
    }
}
