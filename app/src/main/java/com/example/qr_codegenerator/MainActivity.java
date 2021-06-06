package com.example.qr_codegenerator;

import androidx.appcompat.app.AppCompatActivity;

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
    private TextView text;
    private EditText inputText;
    private Button generateButton;
    Bitmap bitmap;
    private ImageView qrImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appName = findViewById(R.id.appName);
        text = findViewById(R.id.text);
        inputText = findViewById(R.id.inputText);
        generateButton = findViewById(R.id.generateButton);
        qrImage = findViewById(R.id.image);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QRGEncoder qrgEncoder = new QRGEncoder(inputText.getText().toString(), null, QRGContents.Type.TEXT, 200);
                try {
                    bitmap = qrgEncoder.getBitmap();
                    qrImage.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}