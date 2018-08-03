package zhiwenyan.cmccaifu.com.android2017.Gson.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Description:
 * Data：7/25/2018-11:25 AM
 *
 * @author yanzhiwen
 */
public class BookTypeAdapter extends TypeAdapter<Book> {

    @Override
    public Book read(final JsonReader in) throws IOException {
        final Book book = new Book();

        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "isbn":
                    book.setIsbn(in.nextString());
                    break;
                case "title":
                    book.setTitle(in.nextString());
                    break;
                case "authors":
                    book.setAuthors(in.nextString().split(";"));
                    break;
            }
        }
        in.endObject();

        return book;
    }

    @Override
    public void write(final JsonWriter out, final Book book) throws IOException {
        out.beginObject();
        out.name("isbn").value(book.getIsbn());
        out.name("title").value(book.getTitle());
//        out.name("authors").value(StringUtils.join(book.getAuthors(), ";"));
        out.endObject();
    }
}

