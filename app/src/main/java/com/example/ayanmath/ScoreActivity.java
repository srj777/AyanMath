package com.example.ayanmath;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;

import com.example.ayanmath.databinding.ActivityScoreBinding;

import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ScoreActivity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler(Looper.myLooper());
    private View mContentView;

    private AdditionSingleDigit asd;
    private GameScore gScore;

    private View mControlsView;

    private ActivityScoreBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityScoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mContentView = binding.fullscreenContent;

        if(getSupportActionBar() != null){
            this.getSupportActionBar().hide();
        }

        Intent i = getIntent();
        asd = (AdditionSingleDigit) i.getSerializableExtra("ASD_INSTANCE");
        gScore = (GameScore) i.getSerializableExtra("SCORE_INSTANCE");

        binding.tvTotalScore.setText("Total Score : " + String.valueOf(gScore.getTotalScore()));

        List<GameResults> lst = gScore.getList();
        binding.tvQtn1.setText(lst.get(0).getMathOperation());
        binding.tvQtn2.setText(lst.get(1).getMathOperation());
        binding.tvQtn3.setText(lst.get(2).getMathOperation());
        binding.tvQtn4.setText(lst.get(3).getMathOperation());
        binding.tvQtn5.setText(lst.get(4).getMathOperation());
        binding.tvQtn6.setText(lst.get(5).getMathOperation());
        binding.tvQtn7.setText(lst.get(6).getMathOperation());
        binding.tvQtn8.setText(lst.get(7).getMathOperation());
        binding.tvQtn9.setText(lst.get(8).getMathOperation());
        binding.tvQtn10.setText(lst.get(9).getMathOperation());

        binding.tvTm1.setText(String.valueOf(lst.get(0).getTimeTaken()));
        binding.tvTm2.setText(String.valueOf(lst.get(1).getTimeTaken()));
        binding.tvTm3.setText(String.valueOf(lst.get(2).getTimeTaken()));
        binding.tvTm4.setText(String.valueOf(lst.get(3).getTimeTaken()));
        binding.tvTm5.setText(String.valueOf(lst.get(4).getTimeTaken()));
        binding.tvTm6.setText(String.valueOf(lst.get(5).getTimeTaken()));
        binding.tvTm7.setText(String.valueOf(lst.get(6).getTimeTaken()));
        binding.tvTm8.setText(String.valueOf(lst.get(7).getTimeTaken()));
        binding.tvTm9.setText(String.valueOf(lst.get(8).getTimeTaken()));
        binding.tvTm10.setText(String.valueOf(lst.get(9).getTimeTaken()));

        binding.tvRs1.setText(lst.get(0).getResult()?"PASS":"FAIL");
        binding.tvRs2.setText(lst.get(1).getResult()?"PASS":"FAIL");
        binding.tvRs3.setText(lst.get(2).getResult()?"PASS":"FAIL");
        binding.tvRs4.setText(lst.get(3).getResult()?"PASS":"FAIL");
        binding.tvRs5.setText(lst.get(4).getResult()?"PASS":"FAIL");
        binding.tvRs6.setText(lst.get(5).getResult()?"PASS":"FAIL");
        binding.tvRs7.setText(lst.get(6).getResult()?"PASS":"FAIL");
        binding.tvRs8.setText(lst.get(7).getResult()?"PASS":"FAIL");
        binding.tvRs9.setText(lst.get(8).getResult()?"PASS":"FAIL");
        binding.tvRs10.setText(lst.get(9).getResult()?"PASS":"FAIL");

        //asd.resetGame();
        //gScore.resetScore();
    }

    public void exitGame(View view){
        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }

    public void restartGame(View view){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra(EXTRA_MESSAGE, "msg from score activity");
        startActivity(intent);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }
}