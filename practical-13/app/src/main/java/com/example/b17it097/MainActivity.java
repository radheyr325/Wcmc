package com.example.b17it097;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Notification;
import android.app.PictureInPictureParams;
import android.drm.DrmStore;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Rational;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    Button pipbtn;
    String path = "/storage/DCIM/music.mp4";
    ActionBar actionBar;
    VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        video = (VideoView)findViewById(R.id.video);
        actionBar = getActionBar();
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(video);
        video.setMediaController(mediaController);
        video.setVideoURI(Uri.parse(path));
        video.requestFocus();
        video.start();

        pipbtn = (Button)findViewById(R.id.pipbtn);

        pipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Display display = getWindowManager().getDefaultDisplay();
                Point point = new Point();
                display.getSize(point);
                int width = point.x;
                int height = point.y;
                Rational ratio = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ratio = new Rational(width,height);
                }
                PictureInPictureParams.Builder pip_builder = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    pip_builder = new PictureInPictureParams.Builder();
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    pip_builder.setAspectRatio(ratio).build();
                }
                pipbtn.setVisibility(View.INVISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    enterPictureInPictureMode(pip_builder.build());
                }

            }
        });
    }
}