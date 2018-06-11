package zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.Model.ChatData;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.commonAdapter.CommonRecycleAdapter;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.commonAdapter.CommonViewHolder;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.commonAdapter.ItemClickListener;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.commonAdapter.MulitiTypeSupport;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;

public class CommonAdapterActivity extends BaseActivity {

    @BindView(R.id.commonRecycler)
    RecyclerView mCommonRecycler;
    private List<ChatData> mDatas = new ArrayList<>();
    @BindView(R.id.container)
    LinearLayout mContainer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_common_adapter;
    }

    @Override
    protected void init() {
        doSetToolBarTitle("多种条目RecyclerView");
        ChatData chatData;
        for (int i = 0; i < 100; i++) {
            if (i % 3 == 0) {
                chatData = new ChatData(i + "", 1);
            } else {
                chatData = new ChatData(i + "", 0);
            }
            mDatas.add(chatData);
        }
        mCommonRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        RecyclerChatAdapter adapter = new RecyclerChatAdapter(this, mDatas);
        mCommonRecycler.setAdapter(adapter);
        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                TextView tv = new TextView(CommonAdapterActivity.this);
                tv.setText(position + "");
                mContainer.addView(tv);
            }
        });
    }

    private class RecyclerChatAdapter extends CommonRecycleAdapter<ChatData> {

        public RecyclerChatAdapter(Context context, List<ChatData> datas) {
            super(context, datas, new MulitiTypeSupport<ChatData>() {
                @Override
                public int getLayoutId(ChatData item) {
                    if (item.getIsMe() == 1) {
                        return R.layout.item_chat_friend;
                    }
                    return R.layout.item_chat_me;
                }
            });
        }

        @Override
        protected void convert(CommonViewHolder holder, ChatData chatData, int position) {
            if (chatData.getIsMe() == 1) {
                holder.setText(R.id.chat_text1, chatData.getChatContent());
                holder.setText(R.id.chat_text2, chatData.getChatContent());
                holder.setText(R.id.chat_text3, chatData.getChatContent());
            }
            if (chatData.getIsMe() == 0) {
                holder.setText(R.id.chat_text, chatData.getChatContent());
            }
        }
    }
}
