package zhiwenyan.cmccaifu.com.android2017.mvp.model;

import zhiwenyan.cmccaifu.com.android2017.mvp.BookListener;

/**
 * Created by zhiwenyan on 4/20/17.
 */

public interface IBookModel {
    void loadBook(BookListener listener);
}
