package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Blob;
import java.util.Set;

public class ItemAdder extends AppCompatActivity {
    Button AddImage;
    Button SetItem;
    ImageView ProductImage;
    EditText ProductName;
    EditText ProductQuantity;
    ImageView image;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_adder);
        AddImage = findViewById(R.id.AddPhoto);
        SetItem = findViewById(R.id.SetItem);
        image=findViewById(R.id.track_image_txt);
        ProductImage = findViewById(R.id.ProductImage);
        ProductName = findViewById(R.id.ProductName);
        ProductQuantity = findViewById(R.id.ProductQuantity);
        AddImage.setOnClickListener(v -> {
            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(gallery, PICK_IMAGE);

        });
        SetItem.setOnClickListener(v -> {
            CartDataBase myDB=new CartDataBase(ItemAdder.this);
            myDB.AddItem1(ProductName.getText().toString().trim(),ProductQuantity.getText().toString().trim());
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            ProductImage.setImageURI(imageUri);
            try {
                useImage(imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    void useImage(Uri uri) throws IOException {
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] bArray = bos.toByteArray();
    }
}