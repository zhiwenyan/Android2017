package zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.Model.photo;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.commonAdapter.CommonRecycleAdapter;
import zhiwenyan.cmccaifu.com.android2017.RecyclerViewAnyins.commonAdapter.CommonViewHolder;

public class PhotoActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<photo> mPhotos=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        for (int i=0;i<10;i++){
            photo photo=new photo();
            photo.url="https://ws1.sinaimg.cn/large/610dc034ly1fhj5228gwdj20u00u0qv5.jpg";
            mPhotos.add(photo);
        }
        mRecyclerView=(RecyclerView)findViewById(R.id.rv);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        PhotoAdapter adapter=new PhotoAdapter(this,mPhotos,R.layout.photo_item);
        mRecyclerView.setAdapter(adapter);
    }
    class PhotoAdapter extends CommonRecycleAdapter<photo>{

        public PhotoAdapter(Context context, List<photo> mDatas, int layoutId) {
            super(context, mDatas, layoutId);
        }

        @Override
        protected void convert(CommonViewHolder holder, photo photo, int position) {
            ImageView imageView=holder.getView(R.id.img);
            Glide.with(PhotoActivity.this).load(photo.url).into(imageView);
        }
    }
}
