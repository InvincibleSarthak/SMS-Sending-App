package com.sarthaksharma.simplemessagesender;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText pNumber, txt_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pNumber = findViewById(R.id.phone_number);
        txt_message = findViewById(R.id.message);
    }

    public void sendMessage(View view) {
        int permission_check = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if(permission_check == PackageManager.PERMISSION_GRANTED){
            MyMessage();
        }
        else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},0);
        }
    }

    private void MyMessage() {
        String phoneNumber = pNumber.getText().toString().trim();
        String message = txt_message.getText().toString().trim();

        if (!pNumber.getText().toString().equals("") && !txt_message.getText().toString().equals("")){
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber,null,message,null,null);
            Toast.makeText(this,"Message sent",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Please Enter Number or Message",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 0:
                if (grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    MyMessage();
                }
                else{
                    Toast.makeText(this,"Permission Not Granted!",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
