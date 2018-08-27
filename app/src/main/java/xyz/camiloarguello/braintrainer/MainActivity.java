package xyz.camiloarguello.braintrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void startGame(View view){
        LinearLayout startContainer = findViewById(R.id.startContainer);

        startContainer.animate().translationYBy(-1000f).setDuration(500);
        
    }
}
