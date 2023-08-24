package com.example.car_guide_ray;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.util.List;


public class OuterFragment extends Fragment implements CameraBridgeViewBase.CvCameraViewListener2 {

    CameraBridgeViewBase cameraBridgeViewBase;
    BaseLoaderCallback baseLoaderCallback;
    int counter = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_outer, container, false);
        cameraBridgeViewBase = (JavaCameraView)v.findViewById(R.id.CameraView);
        cameraBridgeViewBase.setVisibility(SurfaceView.VISIBLE);
        cameraBridgeViewBase.setCvCameraViewListener(this);

        baseLoaderCallback = new BaseLoaderCallback(getActivity()) {
            @Override
            public void onManagerConnected(int status){
                super.onManagerConnected(status);

                if (status == BaseLoaderCallback.SUCCESS) {
                    cameraBridgeViewBase.enableView();
                } else {
                    super.onManagerConnected(status);
                }
            }
        };


        return v;
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {

        Mat frame = inputFrame.rgba();

        if(counter % 2 == 0){
            Core.flip(frame, frame, 1);
            Imgproc.cvtColor(frame, frame, Imgproc.COLOR_RGBA2GRAY);
        }

        counter = counter + 1;

        return frame;
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
    }

    @Override
    public void onCameraViewStopped() {
    }

    @Override
    public List<? extends CameraBridgeViewBase> getCameraViewList() {
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(!OpenCVLoader.initDebug()){
            Toast.makeText(getActivity(),"There's a problem.", Toast.LENGTH_SHORT).show();
        }
        else{
            baseLoaderCallback.onManagerConnected(baseLoaderCallback.SUCCESS);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(cameraBridgeViewBase != null){
            cameraBridgeViewBase.disableView();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(cameraBridgeViewBase != null){
            cameraBridgeViewBase.disableView();
        }
    }
}