package ma.time.crono;

import android.os.Handler;
import android.view.View;
import android.widget.TextView;
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
        timer_tv = (TextView) findViewById(R.id.timer_tv);
        System.out.println("The current Thread oncreate : " + Thread.currentThread().getId());
        runTimer();
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
                  handler.postDelayed(this,1000);

            }

        });
    }
}
