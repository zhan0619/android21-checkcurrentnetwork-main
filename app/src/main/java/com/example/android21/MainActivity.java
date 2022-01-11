package com.example.android21;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv);
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();

        AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
        ad.setMessage("目前沒有網路，是否前往設定？");
        ad.setPositiveButton("設定網路", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS));
            }
        });
        ad.setNegativeButton("不用設定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        ad.setCancelable(false);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if(info.isConnected()){
                        textView.setText("有網路可用");
                    }
                }catch (Exception e){
                    runOnUiThread(()->{
                        ad.show();
                    });
                }
            }
        }).start();
    }
}