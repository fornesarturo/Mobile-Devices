package mx.itesm.class8;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //Return from different activities.
    private final static int TAKE_PICTURE = 0;
    private final static int SAVE_PICTURE = 1;
    private final static int TAKE_VIDEO = 2;
    private final static int STORAGE_PERMISSION = 3;

    private ImageView photo;
    private String lastImageURI;
    private VideoView video;
    private MediaController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        photo = (ImageView) findViewById(R.id.imageview_photo);
        video = (VideoView) findViewById(R.id.videoview_video);

        controller = new MediaController(this);
        controller.setMediaPlayer(video);
        video.setMediaController(controller);
    }

    public void savePicture(View v){
        if(Build.VERSION.SDK_INT >= 23 &&
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    STORAGE_PERMISSION);
        }
        else{
            savePicturePermitted();
        }
    }

    public void savePicturePermitted(){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(i.resolveActivity(getPackageManager()) != null){
            File photo = null;
            try{
                String time = new SimpleDateFormat("yyyMMdd-HHmmss").format(new Date());
                String name  = "IMAGE_"+time;
                File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                photo = File.createTempFile(name, "jpg", directory);
                lastImageURI = photo.getAbsolutePath();
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
            if(photo != null){
                i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
                startActivityForResult(i, SAVE_PICTURE);
            }
        }
    }

    public void takeVideo(View v){
        Intent i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        if(i.resolveActivity(getPackageManager()) != null){
            startActivityForResult(i, TAKE_VIDEO);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if(requestCode == STORAGE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            savePicturePermitted();
        }
    }

    @Override
    public void onActivityResult(int req, int res, Intent data){
        Toast.makeText(this, getString(R.string.toast), Toast.LENGTH_SHORT).show();
        if(res == Activity.RESULT_OK){
            switch(req){
                case TAKE_PICTURE:
                    Bundle extra = data.getExtras();
                    Bitmap image = (Bitmap) extra.get("data");
                    photo.setImageBitmap(image);
                    break;
                case SAVE_PICTURE:
                    Bitmap image2 = BitmapFactory.decodeFile(lastImageURI);
                    photo.setImageBitmap(image2);
                    break;
                case TAKE_VIDEO:
                    Uri videoData = data.getData();
                    video.setVideoURI(videoData);
                    video.start();
                    break;
            }
        }
    }
}