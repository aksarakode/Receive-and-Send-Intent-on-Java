package com.arbukstudio.serbaguna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class IntentReceived extends AppCompatActivity {

    private ImageView picView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_received);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent);
            } else if (type.startsWith("image/")) {
                handleSendImage(intent);
            }
        }

    }

    private void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        TextView sentText = findViewById(R.id.sentText);

        if (sharedText != null) {
            sentText.setText(sharedText);
        }
    }

    private void handleSendImage(Intent intent) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        ImageView picView = findViewById(R.id.sentImage);

        if (imageUri != null) {
            picView.setImageURI(imageUri);
        } else {

            Toast.makeText(this, "Error occured, URI is invalid", Toast.LENGTH_LONG).show();

        }
    }
}