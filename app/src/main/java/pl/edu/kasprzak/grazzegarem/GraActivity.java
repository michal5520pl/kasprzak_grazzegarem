package pl.edu.kasprzak.grazzegarem;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GraActivity extends AppCompatActivity {


    class ClockWorker implements Runnable {
        @Override
        public void run() {
            clock.setText("" + counter);
            --counter;
            if (runningClock) {
                handler.postDelayed(worker, 1);
            }
        }
    }

    class ButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (!runningClock) {
                counter = 100;
                runningClock = true;
                handler.postDelayed(worker, 1);
            } else {
                runningClock = false;
            }
        }
    }
    boolean runningClock = false;
    int counter;

    ClockWorker worker;
    Button action;
    ButtonClick click;
    TextView clock;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gra);
        action = (Button)findViewById(R.id.action);
        clock = (TextView)findViewById(R.id.clock);
        click = new ButtonClick();
        worker = new ClockWorker();
        handler = new Handler();
        counter = 100;
        action.setOnClickListener(click);
    }
}
