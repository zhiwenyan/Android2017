package zhiwenyan.cmccaifu.com.android2017.retrofit.model;

/**
 * Created by zhiwenyan on 4/19/17.
 */

public class Reviews {
    public String book;
    public String title;
    public String content;
    public String rating;

    @Override
    public String toString() {
        return "Reviews{" +
                "book='" + book + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }

}
