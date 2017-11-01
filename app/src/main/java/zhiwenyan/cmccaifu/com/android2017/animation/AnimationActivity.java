package zhiwenyan.cmccaifu.com.android2017.animation;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import zhiwenyan.cmccaifu.com.android2017.R;
import zhiwenyan.cmccaifu.com.android2017.base.BaseActivity;

public class AnimationActivity extends BaseActivity {

    @BindView(R.id.target)
    TextView mTarget;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_animation;
    }

    @OnClick({R.id.transBtn, R.id.rorationBtn, R.id.scaleBtn, R.id.alphaBtn, R.id.groupBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.transBtn:
                TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0.3f,
                        Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0.5f);
                translateAnimation.setDuration(2000);
                mTarget.startAnimation(translateAnimation);
                break;
            case R.id.rorationBtn:
                RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF,
                        0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setDuration(2000);
                mTarget.startAnimation(rotateAnimation);
                break;
            case R.id.scaleBtn:
                ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                scaleAnimation.setDuration(2000);
                mTarget.startAnimation(scaleAnimation);
                break;
            case R.id.alphaBtn:
                AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1.0f);
                alphaAnimation.setDuration(2000);
                mTarget.startAnimation(alphaAnimation);
                break;
            case R.id.groupBtn:
//                Animation animation = AnimationUtils.loadAnimation(this, R.anim.simple_anim);
//                animation.setDuration(3000);
//                mTarget.startAnimation(animation);
                ScaleAnimation scaleAnimation1 = new ScaleAnimation(0.1f, 1.0f, 0.1f, 1f, Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                RotateAnimation rotateAnimation1 = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF,
                        0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                AnimationSet animationSet = new AnimationSet(true);
                animationSet.addAnimation(scaleAnimation1);
                animationSet.addAnimation(rotateAnimation1);
                animationSet.setDuration(2000);
                mTarget.startAnimation(animationSet);
                break;
        }
    }
}
