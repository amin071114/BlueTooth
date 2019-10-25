package com.amin.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothHeadset;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.amin.myapplication.activity.ControlActivity;

public class Splash extends AppCompatActivity {

    private final int REQUEST_ENABLE_BT = 1;
    private BluetoothHeadset bluetoothHeadset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter == null){
            new AlertDialog.Builder(this)
                    .setTitle(R.string.warning)
                    .setMessage(R.string.no_avilabel)
                    .setPositiveButton(R.string.exit, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        else{
            new Handler().postDelayed(new Runnable(){

                @Override
                public void run() {

                    if(!bluetoothAdapter.isEnabled()){
                        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
                    }else{
                        Toast.makeText(Splash.this,R.string.neetconnet,
                                Toast.LENGTH_SHORT).show();
                    }

                }
            }, 3000);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == REQUEST_ENABLE_BT) {
                Toast.makeText(this,R.string.bluetooth_active,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ControlActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(Splash.this,R.string.bluetooth_deactive,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,ControlActivity.class);
                startActivity(intent);
            }
        }
    }
}
