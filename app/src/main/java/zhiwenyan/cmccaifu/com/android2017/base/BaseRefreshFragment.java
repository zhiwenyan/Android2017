package zhiwenyan.cmccaifu.com.android2017.base;

/**
 * Created by zhiwenyan on 2017/6/28.
 * <p>
 * <p>
 * 通用的刷新Fragment
 */

public abstract class BaseRefreshFragment{
//    protected String tag = UUID.randomUUID().toString();
//    private MultipleStatusView mMultipleStatusView;
//    private SwipeRefreshLayout mSwipeRefreshLayout;
//    private RecyclerView mRecyclerView;
//    private CommonRecycleAdapter<T> mCommonRecycleAdapter;
//    protected List<T> mList = new ArrayList<>();
//    protected int mPageIndex = 1; //当前第几页
//    protected int mCount = 10;  //一页返回多少条
//    private Type type;
//
//    public BaseRefreshFragment() {
//        type = getSuperclassTypeParameter(getClass());
//    }
//
//    static Type getSuperclassTypeParameter(Class<?> subclass) {
//        Type superclass = subclass.getGenericSuperclass();
//        if (superclass instanceof Class) {
//            throw new RuntimeException("Missing type parameter.");
//        }
//        ParameterizedType param = (ParameterizedType) superclass;
//        return $Gson$Types.canonicalize(param.getActualTypeArguments()[0]);
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.common_refresh_recycler;
//    }
//
//    @Override
//    protected void initViews(ViewHolder holder, View root) {
//        mMultipleStatusView = holder.get(R.id.multipleStatusView);
//        mSwipeRefreshLayout = holder.get(R.id.refresh_layout);
//        mSwipeRefreshLayout.setColorSchemeResources(R.color.common_fm_color);
//        mSwipeRefreshLayout.setOnRefreshListener(this);
//        mRecyclerView = holder.get(R.id.recycler_view);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//        mRecyclerView.addItemDecoration(new DefaultItemDecoration(getActivity(), R.drawable.common_item_decoration));
//        mRecyclerView.setHasFixedSize(false);
//        mCommonRecycleAdapter = new CommonRecycleAdapter<T>(getContext(), getItemLayout(), mList) {
//            @Override
//            protected void convert(CommonViewHolder holder, T t, int position) {
//                fillValue(holder, t, position);
//            }
//        };
//        mMultipleStatusView.setOnRetryClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (DoubleClick.isFastDoubleClick()) {
//                    return;
//                }
//                onRefresh();
//            }
//        });
//        mRecyclerView.setAdapter(mCommonRecycleAdapter);
//        mRecyclerView.addOnScrollListener(new OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
//                int lastVisibleItemPosition = ((LinearLayoutManager) layoutManager)
//                        .findLastVisibleItemPosition();
//                int visibleItemCount = layoutManager.getChildCount();
//                int totalItemCount = layoutManager.getItemCount();
//                //判断是否滑动到最后
//                if (visibleItemCount > 0 && newState == RecyclerView.SCROLL_STATE_IDLE
//                        && lastVisibleItemPosition >= totalItemCount - 1) {
//                    onLoadMore();
//                    mRecyclerView.scrollToPosition(mCommonRecycleAdapter.getItemCount() - 1);
//                }
//            }
//        });
//        onRequestData();
//    }
//
//    public void onRequestData() {
//        mSwipeRefreshLayout.setRefreshing(true);
//        OkHttpManager.getInstance().getList(tag, getUrl(), type, new HttpListener() {
//            @Override
//            public void onSuccess(Object obj) {
//                ArrayList<T> result = (ArrayList<T>) obj;
//                mList.addAll(result);
//                mCommonRecycleAdapter.notifyDataSetChanged();
//                if (mList.size() > 0) {
//                    mMultipleStatusView.showContent();
//                } else {
//                    mMultipleStatusView.showEmpty();
//                }
//                setRefreshing();
//            }
//
//            @Override
//            public void onFailure(int errorType, String message) {
//                if (mList.size() < 1) {
//                    if (errorType == 5) {
//                        mMultipleStatusView.showNoNetwork();
//                    } else {
//                        mMultipleStatusView.showError();
//                    }
//                } else {
//                    doToast(message);
//                }
//                setRefreshing();
//            }
//
//        });
//
//    }
//
//    /**
//     * SwipeRefreshLayout 是否显示刷新图标
//     */
//    private void setRefreshing() {
//        if (mSwipeRefreshLayout.isRefreshing()) {
//            mSwipeRefreshLayout.setRefreshing(false);
//        }
//    }
//
//    /**
//     * 上拉加载更多
//     */
//    private void onLoadMore() {
//        if (!mSwipeRefreshLayout.isRefreshing()) {
//            mPageIndex++;
//            onRequestData();
//        }
//    }
//
//    /**
//     * 下拉刷新
//     */
//    @Override
//    public void onRefresh() {
//        if (mList.size() > 1) {
//            mList.clear();
//        }
//        mPageIndex = 1;
//        onRequestData();
//    }
//
//
//    /**
//     * 获取ItemLayout
//     *
//     * @return
//     */
//    public abstract int getItemLayout();
//
//    /**
//     * 填充View
//     *
//     * @param holder
//     * @param t
//     * @param position
//     */
//    public abstract void fillValue(CommonViewHolder holder, T t, int position);
//
//    /**
//     * 请求数据的Url
//     *
//     * @return
//     */
//    public abstract String getUrl();

}


