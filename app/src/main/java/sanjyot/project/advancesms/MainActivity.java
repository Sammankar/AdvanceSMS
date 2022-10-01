package sanjyot.project.advancesms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.Permission;

public class MainActivity extends AppCompatActivity {

    EditText editTextPh_no, editTextMessage;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextPh_no=findViewById(R.id.ph_no);
        editTextMessage=findViewById(R.id.message);
        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED){
                    sendSMS();
                }else{
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS},100);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100 & grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            sendSMS();
        }else{
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();

        }

    }

    private void sendSMS() {
        String Phone= editTextPh_no.getText().toString();
        String Message= editTextMessage.getText().toString();

        if (!Phone.isEmpty() && !Message.isEmpty()) {

            SmsManager smsManager = SmsManager.getDefault();

            smsManager.sendTextMessage(Phone, null, Message, null, null);

            Toast.makeText(this, "SMS Sent Successfully", Toast.LENGTH_SHORT).show();

        }else{

            Toast.makeText(this, "Please enter phone no and message", Toast.LENGTH_SHORT).show();

        }
    }
}