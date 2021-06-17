package com.example.qr_codegenerator;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.io.IOException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class LogoActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    Bitmap bitmap, code;
    Bitmap logo = null;
    ImageView qrcode;
    SeekBar seekBar;
    static final int GALLERY_REQUEST = 1;
    private double indexWH=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);
        qrcode = findViewById(R.id.qrcode);
        try {
            code = DataTransfer.getQRcode();
            bitmap = code;
            qrcode.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        seekBar.setMax((int)Math.min((bitmap.getWidth()/16 - 20)*16, Math.sqrt(0.3 * (bitmap.getWidth() * bitmap.getWidth()-(216+bitmap.getWidth()*16)))));
    }

    public void onClickButton(View view) {
        switch (view.getId()) {
            case R.id.addLogoButton:
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
                break;
            case R.id.nextButton:
                DataTransfer.setQRcode(bitmap);
                Intent i = new Intent(LogoActivity.this, SaveActivity.class);
                startActivity(i);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        ImageView imageView = (ImageView) findViewById(R.id.qrcode);
        switch(requestCode) {
            case GALLERY_REQUEST:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    try {
                        logo = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                        double w =logo.getWidth();
                        double h = logo.getHeight();
                        indexWH = h > w ? h/w : w/h;
                        bitmap = overlay(code, logo, seekBar.getProgress());
                        qrcode.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }
    }
    public Bitmap overlay(Bitmap code, Bitmap logo, int size) {
        Bitmap bmOverlay = Bitmap.createBitmap(code.getWidth(), code.getHeight(), code.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(code, new Matrix(), null);
        int w = logo.getWidth();
        int h = logo.getHeight();
        if(h > w){
            h = size;
            w = (int)(h/indexWH);
        }
        else{
            w = size;
            h = (int)(w/indexWH);
        }
        Bitmap logo2 = Bitmap.createScaledBitmap(logo, w, h, true);
        canvas.drawBitmap(logo2, (code.getWidth()-logo2.getWidth())/2, (code.getHeight()-logo2.getHeight())/2, null);
        return bmOverlay;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(logo != null) {
            bitmap = overlay(code, logo, progress);
            qrcode.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}