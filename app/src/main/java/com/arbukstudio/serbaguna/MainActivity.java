package com.arbukstudio.serbaguna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private EditText numberToSend, websiteText, textText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton callButton = findViewById(R.id.callButton);
        ImageButton smsButton = findViewById(R.id.smsButton);
        ImageButton websiteButton = findViewById(R.id.websiteButton);
        ImageButton emailButton = findViewById(R.id.emailButton);
        ImageButton mapsButton = findViewById(R.id.mapsButton);
        ImageButton calenderButton = findViewById(R.id.calenderButton);



        numberToSend = findViewById(R.id.numberToSend);

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = numberToSend.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phone));
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),"aplikasi belum di izinkan oleh sistem!", Toast.LENGTH_SHORT).show();
                    return;
                }
                startActivity(callIntent);
            }
        });

        smsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = numberToSend.getText().toString();
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:"+phone));
                sendIntent.putExtra("sms_body", "saya dari gojek");
                startActivity(sendIntent);
            }
        });

        websiteText = findViewById(R.id.websiteText);

        websiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String website = websiteText.getText().toString();
                Intent openWebsite = new Intent(Intent.ACTION_VIEW);
                openWebsite.setData(Uri.parse(website));
                startActivity(openWebsite);

            }
        });

        textText = findViewById(R.id.textText);

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                String tempat = textText.getText().toString();
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ "systempatched@gmail.com"});
                email.putExtra(Intent.EXTRA_SUBJECT, "Jemput saya di ");
                email.putExtra(Intent.EXTRA_TEXT, tempat);

                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });

        mapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempat = textText.getText().toString();
                Intent maps = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("google.navigation:q="+tempat));
                startActivity(maps);
            }
        });

        calenderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempatEvent = textText.getText().toString();
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra(CalendarContract.Events.TITLE, "Pengumpulan Tugas Android ");
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION, tempatEvent);
                intent.putExtra(CalendarContract.Events.DESCRIPTION, "Pengumpulan berbentuk video karena adanya virus covid19");

                GregorianCalendar calDate = new GregorianCalendar(2020, 03, 20);
                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                        calDate.getTimeInMillis());
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                        calDate.getTimeInMillis());
                intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
                intent.putExtra(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PRIVATE);
                intent.putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
                startActivity(intent);
            }
        });
    }

    public void info(View view) {
        Intent profile = new Intent(MainActivity.this, aboutme.class);
        startActivity(profile);
    }
}
