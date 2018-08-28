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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    TextView secondsLeft;
    TextView rQuestion;
    TextView rAnswer1;
    TextView rAnswer2;
    TextView rAnswer3;
    TextView rAnswer4;
    LinearLayout startContainer;
    Button startButton;
    int questionNumber;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score = 0;

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
                questionNumber = 1;
            }
        }.start();

        questionNumber = 1;
        //renderFirstQuestion();
        decideQuestion(questionNumber,"");
    }

    public void renderFirstQuestion(){
        rQuestion = findViewById(R.id.question);
        rAnswer1 = findViewById(R.id.Rta1);
        rAnswer2 = findViewById(R.id.Rta2);
        rAnswer3 = findViewById(R.id.Rta3);
        rAnswer4 = findViewById(R.id.Rta4);

        try{
            JSONObject q1 = createQuestion("3*2",3,5,8,6,"rta4");

            rQuestion.setText(q1.get("ask").toString());
            rAnswer1.setText(q1.get("a").toString());
            rAnswer2.setText(q1.get("b").toString());
            rAnswer3.setText(q1.get("c").toString());
            rAnswer4.setText(q1.get("d").toString());

        }catch (JSONException e){
            Log.e("RENDER QUESTION", "Error in renderQuestion");
            e.printStackTrace();
        }
    }
    public void selectAnswer(View view){
        String selected = view.getTag().toString();

        Log.i("SELECTED", selected);

        decideQuestion(questionNumber, selected);
        questionNumber++;
    }

    public void decideQuestion(int question, String userSelection){
        switch (question){
            case 1:
                renderQuestion("10+3",12,13,14,15,"rta2", userSelection);
                break;
            case 2:
                renderQuestion("2+2",4,5,6,7,"rta1", userSelection);
                break;
            case 3:
                renderQuestion("3*3",10,6,8,9,"rta4", userSelection);
                break;
            case 4:
                renderQuestion("3*3",10,6,8,9,"rta4", userSelection);
                break;
            default:
                renderQuestion("4+4",7,8,9,10,"rta2", userSelection);
        }
    }
    private void renderQuestion(String ask, int rta1, int rta2, int rta3, int rta4, String correct, String userSelection){
        rQuestion = findViewById(R.id.question);
        rAnswer1 = findViewById(R.id.Rta1);
        rAnswer2 = findViewById(R.id.Rta2);
        rAnswer3 = findViewById(R.id.Rta3);
        rAnswer4 = findViewById(R.id.Rta4);

        try{
            JSONObject q1 = createQuestion(ask,rta1,rta2,rta3,rta4,correct);

            rQuestion.setText(q1.get("ask").toString());
            rAnswer1.setText(q1.get("a").toString());
            rAnswer2.setText(q1.get("b").toString());
            rAnswer3.setText(q1.get("c").toString());
            rAnswer4.setText(q1.get("d").toString());

            if(!userSelection.isEmpty()){

                //Log.i("USER",userSelection);
                //Log.i("SYSTEMA" , q1.get("r").toString());
                Log.i("PREGUNTA #", Integer.toString(questionNumber));
                Log.i("QUESTION",q1.get("ask").toString());

                if(q1.get("r").toString().equals(userSelection)){
                    score++;
                    Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
                }

            }

        }catch (JSONException e){
            Log.e("RENDER QUESTION", "Error in renderQuestion");
            e.printStackTrace();
        }
    }
    private JSONObject createQuestion(String ask, int rta1, int rta2, int rta3, int rta4, String correct){
        JSONObject question = new JSONObject();
        try{
            question.put("ask", ask);
            question.put("a",rta1);
            question.put("b",rta2);
            question.put("c",rta3);
            question.put("d",rta4);
            question.put("r",correct);

        }catch (JSONException e){
            Log.e("CREATE QUESTION", "Error in createQuestion");
            e.printStackTrace();
        }

        return question;
    }

}
