package zhiwenyan.cmccaifu.com.android2017.Gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Description: Configure GSON
 * Data：7/25/2018-10:59 AM
 *
 * @author yanzhiwen
 */
public class Test {
    public static void main(String[] args) {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Book.class, new BookSerialiser());
        gsonBuilder.setPrettyPrinting();
        final Gson gson = gsonBuilder.create();

        final Book javaPuzzlers = new Book();
        javaPuzzlers.setTitle("Java Puzzlers: Traps, Pitfalls, and Corner Cases");
        javaPuzzlers.setIsbn10("032133678X");
        javaPuzzlers.setIsbn13("978-0321336781");
        javaPuzzlers.setAuthors(new String[] { "Joshua Bloch", "Neal Gafter" });

        // Format to JSON
        final String json = gson.toJson(javaPuzzlers);
        System.out.println(json);
    }
}
