package com.example.pierre.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by pierre on 08/05/16.
 */
public class DrawActivityClient  extends AppCompatActivity implements SensorEventListener {
    private Paint paint = new Paint();
    private ClientComAsyncTask comAT;

    private int posX;
    private int posY;
    private GameView gameView;


    private int playerX;
    private int playerY;

    private SensorManager sensorManager;
    private Sensor gravity;

    private String goIpAddr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        posX = 500;
        posY = 1400;

        playerX = 500;
        playerY = 300;
        gameView = new GameView(this, posX, posY, playerX, playerY);
        setContentView(gameView);
        gameView.setWillNotDraw(false);

        Bundle b = getIntent().getExtras();


        if(b != null) {
            goIpAddr = b.getString("ip");
        }
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        gravity = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);

        comAT = new ClientComAsyncTask(this, goIpAddr, gameView);
        comAT.execute();
    }

    protected void onPause() {
        super.onPause();
        if(sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }

    protected void onResume() {
        super.onResume();
        if(sensorManager != null) {
            sensorManager.registerListener(this, gravity, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor mySensor = event.sensor;
        if(mySensor.getType() == Sensor.TYPE_GRAVITY){
            float x = event.values[0];
            if(x > 1) {
//                gameView.movePlayer("g");
                comAT.setDirection("g");
            }else if (x < -1) {
//                gameView.movePlayer("d");
                comAT.setDirection("d");
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    class GameView extends SurfaceView
    {
        private int x;
        private int y;
        private int px;
        private int py;
        public GameView(Context context, int _x, int _y, int _px, int _py) {
            super(context);
            x = _x;
            y = _y;
            px = _px;
            py = _py;
        }

        public void setPositions(String str){
            String[] array = str.split(" ");
            x = Integer.parseInt(array[0]);
            px = Integer.parseInt(array[1]);
            Log.w("LACAZETTE", Integer.toString(x) + " " + Integer.toString(px));
            invalidate();

        }
        public void move(String str)
        {
            Log.w("DIRECTION = ", str);
            if(str.equals("d"))
            {
                x += 10;
            }else if(str.equals("g"))
            {
                x -= 10;
            }else if(str.equals("h")){
                y += 10;
            }else if(str.equals("b")){
                y -= 10;
            }
            invalidate();
            Log.w("UPDATE", "positions");

        }

        public void movePlayer(String str){
            if(str.equals("d")){
                px += 10;
            } else if(str.equals("g")){
                px -= 10;
            }
            invalidate();
        }

        @Override
        protected void onDraw(Canvas canvas)
        {
            super.onDraw(canvas);
            int radius=40;
            Paint p=new Paint();
            p.setColor(Color.RED);
//            canvas.drawCircle(x, y, radius, p);
            canvas.drawRect(x - 100, y - 52, x + 100, y + 25, p);
            p.setColor(Color.BLUE);
            canvas.drawRect(px - 100, py - 52, px + 100, py + 25, p);
        }

    }

}

