package zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.InjectView;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.adapter.ItemDragAdapter;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;

public class ItemDragActivity extends BaseActivity {

    @InjectView(R.id.rv)
    RecyclerView mRv;
    private List<String> mList = new ArrayList<>();
    private ItemDragAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_item_drag;
    }

    @Override
    protected void init() {
        doSetToolBarTitle("Item拖动排序和滑动删除");
        while (mList.size() <= 26) {
            char ch = (char) (int) (Math.random() * 26 + 65);
            if (!mList.contains(ch + "")) {
                mList.add(ch + "");
            }
            if (mList.size() == 26) {
                break;
            }
        }


        mRv.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
        mAdapter = new ItemDragAdapter(this, mList, R.layout.item_drag);
        mRv.setAdapter(mAdapter);
        mItemTouchHelper.attachToRecyclerView(mRv);

    }

    private ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int dragFlags = 0;
            int swipeFlags = 0;
            if (viewHolder.getAdapterPosition() == 0 || viewHolder.getAdapterPosition() == mAdapter.getItemCount() - 1) {
                return makeMovementFlags(dragFlags, swipeFlags);//直接返回0表示不支持拖曳和滑动
            } else {
                //actionState : action状态类型，有三类 ACTION_STATE_DRAG （拖曳），ACTION_STATE_SWIPE（滑动），ACTION_STATE_IDLE（静止）
                dragFlags = makeFlag(ItemTouchHelper.ACTION_STATE_DRAG, ItemTouchHelper.UP | ItemTouchHelper.DOWN
                        | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);//支持上下左右的拖曳
                swipeFlags = makeMovementFlags(ItemTouchHelper.ACTION_STATE_SWIPE, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);//表示支持左右的滑动
                return makeMovementFlags(dragFlags, swipeFlags);//直接返回0表示不支持拖曳和滑动
            }
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                              RecyclerView.ViewHolder target) {

            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();
            //做数据的交换
            if (fromPosition < toPosition) { //下移动
                for (int index = fromPosition; index < toPosition; index++) {
                    Collections.swap(mList, index, index + 1);
                }
            } else {  //上移动
                for (int index = fromPosition; index > toPosition; index--) {
                    Collections.swap(mList, index, index - 1);
                }
            }
            mAdapter.notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int currentPosition = viewHolder.getAdapterPosition();
            mList.remove(currentPosition);
            mAdapter.notifyItemRemoved(currentPosition);

        }

        //状态发生改变
        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            super.onSelectedChanged(viewHolder, actionState);

        }


        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
        }
    });
}
