package com.example.qr_codegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

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
                SaveImage(bitmap);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "QR-код успешно сохранен!", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.homeButton:
                Intent i = new Intent(SaveActivity.this, MainActivity.class);
                startActivity(i);
                break;
        }
    }
    private void SaveImage(Bitmap finalBitmap) {
        String root = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES).toString();
        File myDir = new File(root + "/qr_codes");
        myDir.mkdirs();
        Random generator = new Random();

        int n = 10000;
        n = generator.nextInt(n);
        int i=1;
        String fname = "QR_code_" + i + ".jpg";
        File file = new File(myDir, fname);
        while(file.exists()) {
            ++i;
            fname = "QR_code_" + i + ".jpg";
            file = new File(myDir, fname);
        }
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        MediaScannerConnection.scanFile(this, new String[]{file.toString()}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });
    }
}