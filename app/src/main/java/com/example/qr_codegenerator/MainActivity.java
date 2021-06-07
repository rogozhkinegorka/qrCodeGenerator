 package com.example.qr_codegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class MainActivity extends AppCompatActivity {
    private TextView appName;
    private TextView textView;
    private EditText inputText;
    private Button generateButton;
    Bitmap bitmap;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appName = findViewById(R.id.appName);
        textView = findViewById(R.id.textView);
        inputText = findViewById(R.id.inputText);
        generateButton = findViewById(R.id.generateButton);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = inputText.getText().toString();
                QRGEncoder qrgEncoder = new QRGEncoder(text, null, QRGContents.Type.TEXT, 200);
                //qrgEncoder.setColorBlack(Color.RED);
                //qrgEncoder.setColorWhite(Color.BLUE);
                try {
                    bitmap = qrgEncoder.getBitmap();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(MainActivity.this, DesignChooseActivity.class);
                startActivity(i);

            }
        });
    }
}