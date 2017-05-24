package zhiwenyan.cmccaifu.com.android2017.mvp.presenter;

import zhiwenyan.cmccaifu.com.android2017.mvp.BookListener;
import zhiwenyan.cmccaifu.com.android2017.mvp.model.BookModel;
import zhiwenyan.cmccaifu.com.android2017.mvp.view.IBookView;
import zhiwenyan.cmccaifu.com.android2017.retrofit.model.BookSearchResponse;

/**
 * Created by zhiwenyan on 4/20/17.
 */

/**
 * Presenter层--->关联Model和View
 */
public class BookPresenter implements BookListener {
    private BookModel mBookModel;
    private IBookView mIBookView;

    public BookPresenter(IBookView bookView) {
        this.mIBookView = bookView;
        mBookModel = new BookModel();
    }

    public void getBookSearchResponse() {
        mBookModel.loadBook(this);
    }

    @Override
    public void setBookListener(BookSearchResponse bookResponse) {
        mIBookView.setBook(bookResponse);
    }
}
