package com.example.missensores22_23;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView textView,textView2;
    SensorManager sensorManager;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);

        String cadena="";
        int contador=0;
        for (Sensor s: deviceSensors){
            cadena+= (contador++)+ "==> Sensor: "+s.getName()+ " Tipo: "+s.getStringType()+" Fabricante: "+s.getVendor()+"\n";
        }
        textView.setText(cadena);

        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),SensorManager.SENSOR_DELAY_NORMAL);



    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor.getType()== Sensor.TYPE_PROXIMITY){
            textView2.setText("Valor: "+sensorEvent.values[0]);
            if (sensorEvent.values[0]==0){
                vibrator.vibrate(500);
            }
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT){
            textView2.setText("Luminosidad: "+ sensorEvent.values[0]);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}