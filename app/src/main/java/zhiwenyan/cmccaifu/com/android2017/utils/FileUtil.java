package zhiwenyan.cmccaifu.com.android2017.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import zhiwenyan.cmccaifu.com.android2017.utils.gson.DoubleJsonDeserializer;
import zhiwenyan.cmccaifu.com.android2017.utils.gson.FloatJsonDeserializer;
import zhiwenyan.cmccaifu.com.android2017.utils.gson.IntegerJsonDeserializer;
import zhiwenyan.cmccaifu.com.android2017.utils.gson.StringJsonDeserializer;

/**
 * Description:
 * Data：8/28/2018-2:01 PM
 *
 * @author yanzhiwen
 */
class User  {
    private int id;
    private int age;
    private String name;

    public User(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

}

public class FileUtil {
    public static void read() {
        try {
            PrintWriter printWriter = new PrintWriter(System.out);
            OutputStream outputStream = new FileOutputStream("C:\\Users\\fumi_it1\\Desktop\\test1.txt");
            InputStream inputStream = new FileInputStream("C:\\Users\\fumi_it1\\Desktop\\test.txt");
            int len;
            byte[] bytes = new byte[1024];
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                printWriter.print(new String(bytes, "GBK"));
            }
            // System.out.println(bufferedReader.readLine());
            printWriter.close();
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Gson createGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        //gsonBuilder.setExclusionStrategies(new SpecificClassExclusionStrategy(null, Model.class));
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");

        JsonDeserializer deserializer = new IntegerJsonDeserializer();
        gsonBuilder.registerTypeAdapter(int.class, deserializer);
        gsonBuilder.registerTypeAdapter(Integer.class, deserializer);

        deserializer = new FloatJsonDeserializer();
        gsonBuilder.registerTypeAdapter(float.class, deserializer);
        gsonBuilder.registerTypeAdapter(Float.class, deserializer);

        deserializer = new DoubleJsonDeserializer();
        gsonBuilder.registerTypeAdapter(double.class, deserializer);
        gsonBuilder.registerTypeAdapter(Double.class, deserializer);

        deserializer = new StringJsonDeserializer();
        gsonBuilder.registerTypeAdapter(String.class, deserializer);

        return gsonBuilder.create();
    }

    public static void save(User src) {
        String fileName = "C:\\Users\\fumi_it1\\Desktop\\aaa1.json";
        File file = new File(fileName);
        Gson gson =createGson();
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            gson.toJson(src,writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public static void readJson() {
        String fileName = "C:\\Users\\fumi_it1\\Desktop\\aaa1.json";
        File file = new File(fileName);
        Gson gson = new Gson();
        System.out.println(file.getAbsolutePath());
        try {
            FileReader fileReader = new FileReader(file);
            User user = gson.fromJson(fileReader, User.class);
            System.out.println(user);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        //read();
        User user = new User(1, 22, "sssss");
        save(user);
        readJson();
    }
}
