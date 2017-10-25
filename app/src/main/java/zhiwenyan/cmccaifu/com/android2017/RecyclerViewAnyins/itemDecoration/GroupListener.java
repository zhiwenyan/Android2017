package zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.itemDecoration;

import android.view.View;

/**
 * Created by yanzhiwen on 2017/10/25.
 */

public interface GroupListener {
    String getGroupName(int position);
    View getGroupView(int position);
}
