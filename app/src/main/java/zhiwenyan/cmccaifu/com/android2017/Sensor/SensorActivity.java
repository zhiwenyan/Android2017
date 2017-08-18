package zhiwenyan.cmccaifu.com.android2017.Sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import zhiwenyan.cmccaifu.com.android2017.R;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private float[] gravity = new float[3];   //重力在设备x、y、z轴上的分量
    private float[] motion = new float[3];  //过滤掉重力后，加速度在x、y、z上的分量
    private double ratioY;
    private double angle;
    private int counter = 1;
    private TextView tv;

    public static final float STANDARD_GRAVITY = 9.80665F;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        tv = (TextView) findViewById(R.id.sensorTv);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if(event.sensor.getType() == Sensor.TYPE_GYROSCOPE)
            /* 对于陀螺仪，测量的是x、y、z三个轴向的角速度，分别从values[0]、values[1]、values[2]中读取，单位为弧度/秒。*/
            tv.setText("事件：" + " x:" + event.values[0] + " y:" + event.values[1]  + " z:" + event.values[2]);
//        for (int i = 0; i < 3; i++) {
//        /* accelermeter是很敏感的，看之前小例子的log就知道。因为重力是恒力，
//        我们移动设备，它的变化不会太快，不象摇晃手机这样的外力那样突然。
//        因此通过low-pass filter对重力进行过滤。这个低通滤波器的权重，我们使用了0.1和0.9，当然也可以设置为0.2和0.8。 */
//            gravity[i] = (float) (0.1 * event.values[i] + 0.9 * gravity[i]);
//            motion[i] = event.values[i] - gravity[i];
//        }
//
//        //计算重力在Y轴方向的量，即G*cos(α)
//        ratioY = gravity[1] / SensorManager.GRAVITY_EARTH;
//        if (ratioY > 1.0)
//            ratioY = 1.0;
//        if (ratioY < -1.0)
//            ratioY = -1.0;
//        //获得α的值，根据z轴的方向修正其正负值。
//        angle = Math.toDegrees(Math.acos(ratioY));
//        if (gravity[2] < 0)
//            angle = -angle;
//
//        //避免频繁扫屏，每10次变化显示一次值
//        if (counter++ % 10 == 0) {
//            tv.setText("Raw Values : \n"
//                    + "   x,y,z = " + event.values[0] + "," + event.values[1] + "," + event.values[2] + "\n"
//                    + "Gravity values : \n"
//                    + "   x,y,z = " + gravity[0] + "," + gravity[1] + "," + gravity[2] + "\n"
//                    + "Motion values : \n"
//                    + "   x,y,z = " + motion[0] + "," + motion[1] + "," + motion[2] + "\n"
//                    + "Y轴角度 :" + angle);
//            tv.invalidate();
//            counter = 1;
//        }
//
        float xValue = event.values[0];// Acceleration minus Gx on the x-axis
        float yValue = event.values[1];//Acceleration minus Gy on the y-axis
        float zValue = event.values[2];//Acceleration minus Gz on the z-axis
        tv.setText("x轴： "+xValue+"  y轴： "+yValue+"  z轴： "+zValue);
        if(xValue > STANDARD_GRAVITY) {
            tv.append("\n重力指向设备左边");
        } else if(xValue < -STANDARD_GRAVITY) {
            tv.append("\n重力指向设备右边");
        } else if(yValue > STANDARD_GRAVITY) {
            tv.append("\n重力指向设备下边");
        } else if(yValue < -STANDARD_GRAVITY) {
            tv.append("\n重力指向设备上边");
        } else if(zValue > STANDARD_GRAVITY) {
            tv.append("\n屏幕朝上");
        } else if(zValue < -STANDARD_GRAVITY) {
            tv.append("\n屏幕朝下");
       }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
