package xyz.camiloarguello.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView secondsLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void startGame(View view){
        LinearLayout startContainer = findViewById(R.id.startContainer);
        startContainer.animate().translationY(-1200f).setDuration(500);

        secondsLeft = findViewById(R.id.secondsleft);
        CountDownTimer countDown = new CountDownTimer(30000, 1000){

            @Override
            public void onTick(long l) {
                Log.i("SECONDS", Long.toString(l));
            }

            @Override
            public void onFinish() {
               Log.i("FINISH", "Finished");
            }
        }.start();



    }
}
