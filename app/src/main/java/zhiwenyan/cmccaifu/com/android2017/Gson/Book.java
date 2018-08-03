package zhiwenyan.cmccaifu.com.android2017.Gson;

import java.util.Arrays;

/**
 * Description:
 * <p>
 * {
 * "title": "Java Puzzlers: Traps, Pitfalls, and Corner Cases",
 * "isbn-10": "032133678X",
 * "isbn-13": "978-0321336781",
 * "authors": [
 * "Joshua Bloch",
 * "Neal Gafter"
 * ]
 * }
 * <p>
 * <p>
 * Data：7/25/2018-10:53 AM
 *
 * @author yanzhiwen
 */
public class Book {
    private String[] authors;
//    @SerializedName("isbn-10")
    private String isbn10;
//    @SerializedName("isbn-13")
    private String isbn13;
    private String title;
    //为了代码简洁，这里移除getter和setter方法等


    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book{" +
                "authors=" + Arrays.toString(authors) +
                ", isbn10='" + isbn10 + '\'' +
                ", isbn13='" + isbn13 + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
