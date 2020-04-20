package pl.edu.pb.sensorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static pl.edu.pb.sensorapp.R.string.light_sensor_label;

public class SensorDetailsActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;
    private TextView sensorNameTextView;
    private TextView sensorValueTextView;
    private LinearLayout details;
    private int type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type=(int)getIntent().getSerializableExtra(SensorActivity.KEY_EXTRA_SENSOR_ID);

        setContentView(R.layout.activity_sensor_details);

        sensorNameTextView=findViewById(R.id.name_selector);
        sensorValueTextView=findViewById(R.id.value_selector);
        details=findViewById(R.id.sensor_details);

        if(type==Sensor.TYPE_LIGHT){
            sensorNameTextView.setText(getResources().getString(R.string.light_sensor_label));
        }
        else if(type==Sensor.TYPE_ROTATION_VECTOR){
            sensorNameTextView.setText(getResources().getString(R.string.gravity_sensor_label));
        }

        sensorManager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor=sensorManager.getDefaultSensor(type);

        if(sensor==null){
            sensorNameTextView.setText(R.string.missing_sensor);
        }
    }



    @Override
    public void onSensorChanged(SensorEvent event) {
       int sensorType=event.sensor.getType();
        float currentValue=event.values[0];

        switch (sensorType){
            case Sensor.TYPE_LIGHT:
                sensorValueTextView.setText(String.valueOf(currentValue));
                if(currentValue>=400){
                    details.setBackgroundColor(0);
                }
                else {
                    details.setBackgroundColor(getResources().getColor(R.color.change));

                }

                break;
            case Sensor.TYPE_ROTATION_VECTOR:
                sensorValueTextView.setText(String.valueOf(currentValue));

            default:

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onStart(){
        super.onStart();

        if(sensor!=null){
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop(){
        super.onStop();

        sensorManager.unregisterListener(this);
    }
}
