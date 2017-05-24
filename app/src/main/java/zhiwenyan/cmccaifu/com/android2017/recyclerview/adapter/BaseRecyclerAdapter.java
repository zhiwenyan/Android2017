package zhiwenyan.cmccaifu.com.android2017.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import java.util.Collections;
import java.util.List;


public class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int HEAD = 0;
    private static final int NORMAL = 1;
    private static final int FOOTER = 2;
    //    private static final int MULTIPLE = 3;
    private List<T> mList;
    private boolean mIsShowFooter = false;
    private boolean mHasHeadView = false;
    private Context mContext;
    private View mItemView;
    private RecyclerAdapter.OnItemClickListener mOnItemClickListener;
    private int mLastPosition = -1;
    private boolean isOpenAnimation = false;
    private int mResid;

    public BaseRecyclerAdapter(List<T> list, int resid) {
        this.mList = list;
        this.mResid = resid;
    }

    public void setOnItemClickListener(RecyclerAdapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHasHeadView && position == 0) {
            return HEAD;
        } else if (mIsShowFooter && isFooterPosition(position)) {
            return FOOTER;
        } else {
            return NORMAL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        switch (viewType) {
            case HEAD:
                return new RecyclerAdapter.HeadViewHolder(mContext, mItemView);
            case NORMAL:
                mItemView = LayoutInflater.from(parent.getContext()).inflate(mResid, parent, false);
                return new RecyclerAdapter.ItemViewHolder(mContext, mItemView);
//            case MULTIPLE:
//                mItemView = getView(parent.getContext(), R.layout.multiple_layout);
//                return new RecyclerAdapter.MultipleViewHolder(mContext, mItemView);
            case FOOTER:
                return new RecyclerAdapter.FooterViewHolder(mContext, mItemView);
            default:
                throw new RuntimeException("there is no type that matches the type " +
                        viewType + " + make sure your using types correctly");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RecyclerAdapter.ItemViewHolder) {
            int pos;
            if (mHasHeadView) {
                pos = position - 1;
            } else {
                pos = position;
            }
            setItemViewValue((ItemViewHolder) holder, mList.get(pos));
            setEvent((ItemViewHolder) holder, pos);
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        setAnim(holder);

    }

    public void addHeaderView(Context context, int resId) {
        mHasHeadView = true;
        mItemView = getView(context, resId);
        notifyItemInserted(0);
    }

    public void setLastPosition(int lastPosition) {
        this.mLastPosition = lastPosition;
    }

    public void setOpenAnimation(boolean openAnimation) {
        this.isOpenAnimation = openAnimation;
    }

    private void setAnim(RecyclerView.ViewHolder holder) {
        if (isOpenAnimation && holder.getLayoutPosition() > mLastPosition) {
            ScaleAnimation scaleAnim = new ScaleAnimation(0.8f, 1.0f, 0.8f, 1.0f,
                    Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
            scaleAnim.setDuration(300);
            holder.itemView.setAnimation(scaleAnim);
        }
        mLastPosition = holder.getLayoutPosition();

    }

    public int getHeadLayoutPosition() {
        if (mHasHeadView) {
            return 0;
        }
        return -1;
    }

    private void setEvent(ItemViewHolder holder, final int position) {
        mItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(position);
                }
            }
        });
    }

    public void setItemViewValue(ItemViewHolder holder, T item) {
    }

    public void showFooter(int resId) {
        mIsShowFooter = true;
        mItemView = getView(mContext, resId);
        notifyItemInserted(getItemCount());
    }

    private View getView(Context context, int resId) {
        return LayoutInflater.from(context).inflate(resId, null);
    }


    public void hideFooter() {
        mIsShowFooter = false;
        notifyItemRemoved(getItemCount());
    }

    public void addMore(List<T> data) {
        int startPosition = mList.size();
        mList.addAll(data);
        notifyItemRangeInserted(startPosition, mList.size());
    }

    /**
     * 移动Item
     *
     * @param fromPosition
     * @param toPosition
     */
    public void moveItem(int fromPosition, int toPosition) {
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
        notifyItemMoved(fromPosition, toPosition);
    }

    /**
     * 滑动Item
     *
     * @param position
     */
    public void removeItem(int position) {
        mList.remove(position);//删除数据
        notifyItemRemoved(position);

    }
    private boolean isFooterPosition(int position) {
        return (getItemCount() - 1) == position;
    }

    @Override
    public int getItemCount() {
        if (mList == null) {
            return 0;
        }
        int size = mList.size();
        if (mHasHeadView && mIsShowFooter) {
            return size + 2;
        } else if (mHasHeadView || mIsShowFooter) {
            return size + 1;
        }
        return size;
    }

    public static class ItemViewHolder extends BaseViewHolder {
        public ItemViewHolder(Context context, View view) {
            super(context, view);
        }
    }

    static class HeadViewHolder extends BaseViewHolder {

        public HeadViewHolder(Context context, View view) {
            super(context, view);
        }
    }

    static class FooterViewHolder extends BaseViewHolder {
        public FooterViewHolder(Context context, View view) {
            super(context, view);
        }
    }

    static class MultipleViewHolder extends BaseViewHolder {
        public MultipleViewHolder(Context context, View view) {
            super(context, view);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
