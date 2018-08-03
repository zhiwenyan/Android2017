package zhiwenyan.cmccaifu.com.android2017.Gson.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Description:
 * Data：7/25/2018-11:27 AM
 *
 * @author yanzhiwen
 */
public class Test {
    public static void main(String[] args) {

        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Book.class, new BookTypeAdapter());
        final Gson gson = gsonBuilder.create();
        final Book book = new Book();
        book.setAuthors(new String[] { "Joshua Bloch", "Neal Gafter" });
        book.setTitle("Java Puzzlers: Traps, Pitfalls, and Corner Cases");
        book.setIsbn("978-0321336781");

        final String json = gson.toJson(book);
        System.out.println("Serialised");
        System.out.println(json);

        final Book parsedBook = gson.fromJson(json, Book.class);
        System.out.println("\nDeserialised");
        System.out.println(parsedBook);

    }
}
