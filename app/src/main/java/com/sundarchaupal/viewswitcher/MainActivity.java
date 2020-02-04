package com.sundarchaupal.viewswitcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {

    //the ViewSwitcher
    private ViewSwitcher switcher;
    private static final int REFRESH_SCREEN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        switcher = (ViewSwitcher) findViewById(R.id.ViewSwitcher);
        startScan();
    }

    public void startScan() {

        new Thread() {

            public void run() {

                try{
                    // This is just a tmp sleep so that we can emulate something loading
                    Thread.sleep(5000);
                    // Use this handler so than you can update the UI from a thread
                    Refresh.sendEmptyMessage(REFRESH_SCREEN);
                } catch(Exception e){
                }
            }
        }.start();
    }

    // Refresh handler, necessary for updating the UI in a/the thread
    Handler Refresh = new Handler(){
        public void handleMessage(Message msg) {

            switch(msg.what){

                case REFRESH_SCREEN:
                    switcher.showNext();
                    // To go back to the first view, use switcher.showPrevious()
                    break;

                default:
                    break;
            }
        }
    };
}

