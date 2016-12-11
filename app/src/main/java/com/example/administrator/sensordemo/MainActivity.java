package com.example.administrator.sensordemo;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SensorManager mSensormanager;
    private Sensor mAccelerometer;
    private Sensor mOrientation;
    private Sensor mLight;
    private TextView tAccelerometer;
    private TextView tOritation;
    private TextView tLight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tAccelerometer = (TextView) this.findViewById(R.id.accelerometer);
        tOritation = (TextView) this.findViewById(R.id.orientation);
        tLight = (TextView) this.findViewById(R.id.light);
        //获得传感器管理器
        mSensormanager  = (SensorManager) getSystemService(SENSOR_SERVICE);
        //获得加速传感器
        mAccelerometer = mSensormanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //获得方向传感器
        mOrientation = mSensormanager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        //获得光线传感器
        mLight = mSensormanager
                .getDefaultSensor(Sensor.TYPE_LIGHT);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //加速传感器的监听
        mSensormanager.registerListener((SensorEventListener) this,mAccelerometer,
                SensorManager.SENSOR_DELAY_NORMAL);
        mSensormanager.registerListener((SensorEventListener) this,mOrientation,
                SensorManager.SENSOR_DELAY_NORMAL);
        mSensormanager.registerListener((SensorEventListener) this,mLight,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //取消传感器监听器的注册
        mSensormanager.unregisterListener((SensorEventListener) this);
    }
    public void onSensorChanged(SensorEvent event){
        float x = event.values[SensorManager.DATA_X];
        float y = event.values[SensorManager.DATA_Y];
        float z = event.values[SensorManager.DATA_Z];
        if (event.sensor.getType()==Sensor.TYPE_ORIENTATION){
            tOritation.setText("方位："+x+","+y+","+z);

        }//获得加速度值
        else if(event.sensor.getType()== Sensor.TYPE_ACCELEROMETER){
            tAccelerometer.setText("加速度："+x+","+y+","+z);
        }else if (event.sensor.getType() == Sensor.TYPE_LIGHT){
            tLight.setText("光鲜"+x+","+y+","+z);
        }
    }
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }
}
