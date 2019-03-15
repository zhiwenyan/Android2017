package zhiwenyan.cmccaifu.com.android2017.view.circleImageView

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_shape.*
import zhiwenyan.cmccaifu.com.android2017.R

//
class ShapeActivity : AppCompatActivity() {
    companion object {
        const val url = "https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p893.webp"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shape)

        Glide.with(this)
                .load(url)
                .into(iv_circle)


    }
}
