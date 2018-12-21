package zhiwenyan.cmccaifu.com.android2017.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_koltin.*
import zhiwenyan.cmccaifu.com.android2017.R


/**
 * Description:
 * Data：10/12/2018-11:48 AM
 * @author yanzhiwen
 */
class KotlinActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_koltin)

        tv_name.text = "Steven Yan"

        btn_ok.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
    }
}
