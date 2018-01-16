package student.cdacsensorsdemoapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    TextView sensorOutputTextView;
    SensorManager sensorManager;
    Sensor sensor;
    AccelListener accelListener;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorOutputTextView = (TextView) findViewById(R.id.textViewOutput);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensor = sensorManager.getDefaultSensor(SensorManager.SENSOR_ACCELEROMETER);

        accelListener = new AccelListener();

        sensorManager.registerListener(accelListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        sensorManager.unregisterListener(accelListener,sensor);
    }

    class AccelListener implements SensorEventListener
    {
        @Override
        public void onSensorChanged(SensorEvent event)
        {
            String sensorName = event.sensor.getName();
            sensorOutputTextView.setText("Sensor Name :\n" + sensorName + "\n\nOutput : \n***********\nX : " + event.values[0] + "\nY : " + event.values[1] + "\nZ : " + event.values[2]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy)
        {

        }
    }
}