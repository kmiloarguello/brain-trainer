package xyz.camiloarguello.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    TextView secondsLeft;
    TextView question;
    LinearLayout startContainer;
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void startGame(View view){
        startContainer = findViewById(R.id.startContainer);
        startButton = findViewById(R.id.startButton);

        startContainer.animate().translationY(-1200f).setDuration(500);

        secondsLeft = findViewById(R.id.secondsleft);
        CountDownTimer countDown = new CountDownTimer(30000, 1000){

            @Override
            public void onTick(long l) {
                secondsLeft.setText(Long.toString( l / 1000 ) + "s");
            }

            @Override
            public void onFinish() {
                startButton.setText("Start again");
                startContainer.animate().translationY(0).setDuration(500);
            }
        }.start();

        String a = createQuestion("2+2",2,4,6,8).toString();
        JSONObject b = createQuestion("2+3",5,4,3,2);
        Log.i("MY ARRAY", b. );
    }


    private JSONObject createQuestion(String ask, int rta1, int rta2, int rta3, int rta4){
        JSONObject question = new JSONObject();
        try{
            question.put("ask", ask);
            question.put("a",rta1);
            question.put("b",rta2);
            question.put("c",rta3);
            question.put("d",rta4);
        }catch (JSONException e){
            e.printStackTrace();
        }

        return question;
    }

}
