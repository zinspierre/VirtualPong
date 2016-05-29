package com.mi12.pierre.virtualpong;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mi12.R;
import com.mi12.pierre.virtualpong.three_phones.reseau_local.ReseauLocal3Activity;
import com.mi12.pierre.virtualpong.three_phones.wifidirect.WifiDirect3Activity;
import com.mi12.pierre.virtualpong.two_phones.reseau_local.ReseauLocal2Activity;
import com.mi12.pierre.virtualpong.two_phones.wifidirect.WifiDirect2Activity;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Réseau local 2 Smartphones
        Button button_local_connect2 = (Button) findViewById(R.id.button_local_connect2);
        button_local_connect2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, ReseauLocal2Activity.class);
                startActivity(intent);
            }
        });

        //Réseau local 2 Smartphones
        Button button_local_connect3 = (Button) findViewById(R.id.button_local_connect3);
        button_local_connect3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, ReseauLocal3Activity.class);
                startActivity(intent);
            }
        });

        //Wifi direct 2 smartphones
        Button button_wifi_direct2 = (Button) findViewById(R.id.button_wifi_direct2);
        button_wifi_direct2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, WifiDirect2Activity.class);
                startActivity(intent);
            }
        });

        //Wifi direct 3 smartphones
        Button button_wifi_direct3 = (Button) findViewById(R.id.button_wifi_direct3);
        button_wifi_direct3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, WifiDirect3Activity.class);
                startActivity(intent);
            }
        });
    }

}



