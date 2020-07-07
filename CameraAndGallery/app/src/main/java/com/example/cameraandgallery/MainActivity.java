package com.example.cameraandgallery;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.net.URI;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b_camera,b_gallery;
    ImageView iv;
    public final static int CAMERA_REQUEST_CODE = 22;
    public final static int GALLERY_REQUEST_CODE = 33;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b_camera = findViewById(R.id.camera_button);
        b_gallery = findViewById(R.id.gallery_button);
        iv = findViewById(R.id.image_view);
        b_camera.setOnClickListener(this);
        b_gallery.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.camera_button:
                open_camera();
                break;

            case R.id.gallery_button:
                open_gallery();
                break;
        }
    }

    private void open_camera() {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i,CAMERA_REQUEST_CODE);

    }

    private void open_gallery() {
        Intent i = new Intent();
        i.setType("Image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                Bitmap b = (Bitmap) data.getExtras().get("data");
                iv.setImageBitmap(b);

            }
        }
        if(requestCode == GALLERY_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                Uri u = data.getData();
                iv.setImageURI(u);
            }
        }
    }
}