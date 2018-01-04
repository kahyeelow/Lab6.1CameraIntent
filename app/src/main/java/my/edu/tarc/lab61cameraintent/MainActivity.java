package my.edu.tarc.lab61cameraintent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1; //request for image
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (ImageView)findViewById(R.id.imageViewThumbnail);
        Button buttonCamera = (Button) findViewById(R.id.buttonCamera);
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
    }

    //call back the result from the intent
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //get intent result from another page/intent

        if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            //if user successfully took picture, and click ok

            //use bundle to get data
            Bundle extras = data.getExtras();

            //obtain a thumbnail image from "data'
            //bitmap is use to compress the images
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            //display the image = data, in mImageView
            mImageView.setImageBitmap(imageBitmap);
        }
    }

    //send request to google Playstore to see whether have any apps to handle the camera
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //resolveActivity method determines the best action to perform for a given intent
        if(takePictureIntent.resolveActivity(getPackageManager()) != null){
            //return is there any app that can handle image capture
            //when return not null, means there's an app to handle
            //pass the request
            startActivityForResult(takePictureIntent,REQUEST_IMAGE_CAPTURE);
        }
    }
}
