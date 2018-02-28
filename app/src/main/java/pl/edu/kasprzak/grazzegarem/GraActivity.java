package pl.edu.kasprzak.grazzegarem;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GraActivity extends AppCompatActivity {

    static private boolean runningClock = false;
    static private int counter = 100;
    private Runnable worker;
    private Button action;
    private TextView clock;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gra);
        action = (Button)findViewById(R.id.action);
        clock = (TextView)findViewById(R.id.clock);
        worker = new Runnable() {
            @Override
            public void run() {
                Log.d("LICZNIK", "DZIAŁAM " + counter);
                clock.setText("" + counter);
                --counter;
                if (runningClock) {
                    handler.postDelayed(worker, 1);
                }
            }
        };
        handler = new Handler();
        action.setOnClickListener(new View.OnClickListener() {
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
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(worker);
        Log.d("CYKL_ZYCIA", "ONPAUSE");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (runningClock) {
            handler.postDelayed(worker, 1);
        }
        Log.d("CYKL_ZYCIA", "ONRESUME");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("CYKL_ZYCIA", "ONSTART");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("CYKL_ZYCIA", "ONSTOP");
    }
}
