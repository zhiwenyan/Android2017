package zhiwenyan.cmccaifu.com.android2017.mvp;

import android.widget.TextView;

import butterknife.InjectView;
import butterknife.OnClick;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;
import zhiwenyan.cmccaifu.com.android2017.mvp.presenter.BookPresenter;
import zhiwenyan.cmccaifu.com.android2017.mvp.view.IBookView;
import zhiwenyan.cmccaifu.com.android2017.retrofit.model.BookSearchResponse;

public class MVPActivity extends BaseActivity implements IBookView {
    BookPresenter mBookPresenter;
    @InjectView(R.id.resultTv)
    TextView mResultTv;

    @Override
    protected void init() {
        super.init();
        mBookPresenter = new BookPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mvp;
    }

    @OnClick(R.id.get)
    public void onClick() {
        mBookPresenter.getBookSearchResponse();
    }

    @Override
    public void setBook(BookSearchResponse bookSearchResponse) {
        BookSearchResponse.BooksBean booksBean = bookSearchResponse.getBooks().get(0);
        mResultTv.setText("描述:" + booksBean.getAuthor_intro() + "\n"
                + "出版社:" + booksBean.getPublisher());
    }
}
