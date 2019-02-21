package com.yangfan.camerakit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.camerakit.CameraKitView;

public class MainActivity extends AppCompatActivity {
    private String TAG=this.getClass().getName();
    private CameraKitView cameraKitView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cameraKitView=findViewById(R.id.camera);
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraKitView.captureImage(new CameraKitView.ImageCallback() {
                    @Override
                    public void onImage(CameraKitView cameraKitView, byte[] bytes) {
                        Log.e(TAG, "onImage: "+bytes.length );
                    }
                });
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraKitView.startVideo();
                cameraKitView.captureVideo(new CameraKitView.VideoCallback() {
                    @Override
                    public void onVideo(CameraKitView cameraKitView, Object o) {
                        Log.e(TAG, "onVideo: "+o.toString() );
                    }
                });
            }
        });
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cameraKitView.stopVideo();
            }
        });
        cameraKitView.setCameraListener(new CameraKitView.CameraListener() {
            @Override
            public void onOpened() {
                Log.e(TAG, "onOpened: ");
            }

            @Override
            public void onClosed() {
                Log.e(TAG, "onClosed: ");
            }
        });
        cameraKitView.setErrorListener(new CameraKitView.ErrorListener() {
            @Override
            public void onError(CameraKitView cameraKitView, CameraKitView.CameraException e) {
                Log.e(TAG, "onError: "+e );
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        cameraKitView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraKitView.onResume();
    }

    @Override
    protected void onPause() {
        cameraKitView.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        cameraKitView.onStop();
        super.onStop();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        cameraKitView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
