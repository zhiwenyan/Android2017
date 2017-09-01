package zhiwenyan.cmccaifu.com.android2017.recyclerview;

import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.ViewGroup.SwipeBackLayout;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;
import zhiwenyan.cmccaifu.com.android2017.model.item;
import zhiwenyan.cmccaifu.com.android2017.recyclerview.adapter.RecyclerAdapter;

public class RecyclerViewActivity extends BaseActivity {

    @InjectView(R.id.rv)
    RecyclerView mRv;
    @InjectView(R.id.refreshLayout)
    SwipeRefreshLayout mRefreshLayout;

    @InjectView(R.id.swipeBackLayout)
    SwipeBackLayout mSwipeBackLayout;
    private List<item> mItems = new ArrayList<>();
    RecyclerAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recycler_view;
    }

    @Override
    protected void init() {
        mRefreshLayout.setColorSchemeColors(getColor(R.color.colorPrimary));
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshLayout.setRefreshing(false);

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.setHasFixedSize(true);
        mRv.setItemAnimator(new DefaultItemAnimator());
        // mRv.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL));
        mAdapter = new RecyclerAdapter(getListItem(), R.layout.list_item);
        mAdapter.setLastPosition(4);
        mAdapter.addHeaderView(this, R.layout.head_layout);
        mRv.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Snackbar.make(mRv, "你单击了" + position, Snackbar.LENGTH_SHORT).show();
            }
        });

        //滑动监听
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mAdapter.setOpenAnimation(true);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int lastVisibleItemPosition = ((LinearLayoutManager) layoutManager)
                        .findLastVisibleItemPosition();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                if (visibleItemCount > 0 && newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItemPosition >= totalItemCount - 1) {
                    Snackbar.make(mRv, "滑动到了底部", Snackbar.LENGTH_SHORT).show();
                    mAdapter.showFooter(R.layout.footer_layout);
                    //mAdapter.addMore(getListItem());
                    mRv.scrollToPosition(mAdapter.getItemCount() - 1);
                }
            }
        });
        //ItemTouchHelper 用于实现 RecyclerView Item 拖曳效果的类
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {

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

            /**
             * @param recyclerView attach的RecyclerView
             * @param viewHolder 拖动的Item
             * @param target 放置Item的目标位置
             * @return
             */
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();//要拖曳的位置
                int toPosition = target.getAdapterPosition();//要放置的目标位置
                mAdapter.moveItem(fromPosition, toPosition);
                return true;
            }

            /**
             * @param viewHolder 滑动移除的Item
             * @param direction
             */
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();//获取要滑动删除的Item位置
                mAdapter.removeItem(position);

            }

            @Override
            public boolean isLongPressDragEnabled() {
                return super.isLongPressDragEnabled();//不支持长按拖曳效果直接返回false
            }

            @Override
            public boolean isItemViewSwipeEnabled() {
                return super.isItemViewSwipeEnabled();//不支持滑动效果直接返回false
            }
        });
        itemTouchHelper.attachToRecyclerView(mRv);
        mSwipeBackLayout.setCallBackListener(new SwipeBackLayout.callBackListener() {
            @Override
            public void finish() {
                RecyclerViewActivity.this.finish();
            }
        });

    }

    private List<item> getListItem() {
        item item;
        for (int i = 0; i < 10; i++) {
            item = new item();
            item.setName("zhiwenyan@icloud.com" + "---->" + i);
            mItems.add(item);
        }
        return mItems;
    }

}
