package ma.time.crono;

import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView timer_tv ;
    private int second = 0 ;
    private int minute = 0 ;
    private int hour = 0 ;
    private boolean isStart = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // we verify if is it the first time ( no Bundle Object has been returned ) if it's not we retrieve the value stored in the
        // savedInstanceState of Bundle Object type
        if(savedInstanceState != null){
            second = savedInstanceState.getInt("second");
            isStart = savedInstanceState.getBoolean("isStart");
        }
        timer_tv = (TextView) findViewById(R.id.timer_tv);
        runTimer();
    }

    //this method to save the state of the activity when the device configuration get changed ( screen rotated, language changed ... )
    //the activity will be destroyed so we put the second variable into a Bundle Object
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("second",second);
        outState.putBoolean("isStart",isStart);
    }

    public void onClickStart(View view){
        isStart = true ;
        System.out.println("The current Thread btn : " + Thread.currentThread().getId());

    }

    public void onClickStop(View view){
        isStart = false ;
    }

    public void onClickReset(View view){
        isStart = false ;
        second = hour = minute = 0;
        timer_tv.setText(R.string.timer);
    }
    public void runTimer(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override

            public void run() {

                if (isStart) {
                    second += 1;
                    hour = (second / 3600)%24;
                    minute = (second / 60)%60;
                    String time = String.format(Locale.getDefault(), "%d:%02d:%02d", hour, minute, second % 60);
                    timer_tv.setText(time);
                }
                  System.out.println("The current Thread runtimer : " + Thread.currentThread().getId());
                 // handler.postDelayed(this,1000);
                  handler.post(this);

            }

        });
    }
}
