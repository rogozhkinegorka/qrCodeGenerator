package com.example.qr_codegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SaveActivity extends AppCompatActivity {

    private ImageView qrcode;
    private Bitmap bitmap = DataTransfer.getQRcode();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        qrcode = findViewById(R.id.qrcode);
        qrcode.setImageBitmap(bitmap);
    }
    public void onClickButton(View view) {
        switch (view.getId()) {
            case R.id.saveButton:

                break;
            case R.id.shareButton:
                //TODO
                break;
        }
    }
}