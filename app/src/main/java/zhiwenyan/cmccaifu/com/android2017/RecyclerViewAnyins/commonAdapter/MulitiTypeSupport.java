package zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.commonAdapter;

/**
 * Created by zhiwenyan on 5/25/17.
 * 多布局的支持
 */

public interface MulitiTypeSupport<T> {
    int getLayoutId(T item);
}
