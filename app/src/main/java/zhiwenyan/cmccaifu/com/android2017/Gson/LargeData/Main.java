package zhiwenyan.cmccaifu.com.android2017.Gson.LargeData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Description:
 * Data：7/25/2018-11:41 AM
 *
 * @author yanzhiwen
 */
public class Main {
    public static void main(final String[] args) throws IOException {
        // Configure GSON
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LargeData.class, new LargeDataTypeAdapter());
        gsonBuilder.setPrettyPrinting();

        final Gson gson = gsonBuilder.create();

        final LargeData data = new LargeData();
        data.create(10485760);

        final String json = gson.toJson(data);

        final File dir = new File("target/part2");
        dir.mkdirs();
//
//        try (PrintStream out = new PrintStream(new File(dir, "output.json"), "UTF-8")) {
//            out.println(json);
//        }
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(new File(dir, "output.json")), "UTF-8"))) {
            gson.toJson(data, out);
        }
        System.out.println("Done");
    }

}
