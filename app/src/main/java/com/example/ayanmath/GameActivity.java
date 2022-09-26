package com.example.ayanmath;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.TextView;

import com.example.ayanmath.databinding.ActivityGameBinding;

import java.util.Timer;
import java.util.TimerTask;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class GameActivity extends AppCompatActivity {

    private TextView tvcTimer;
    private CountDownTimer cTimer = null;
    private GameScore gScore;
    private int timeTaken;
    private boolean timeOut = false;


    //start timer function
    void startTimer() {
        if(cTimer == null){
            cTimer = new CountDownTimer(10000, 100) {
                public void onTick(long millisUntilFinished) {
                    int secs = (int)(millisUntilFinished/1000);
                    int millisecs = (int)(millisUntilFinished%1000);
                    String strSecs = String.valueOf(secs);
                    String strMilliSecs = String.valueOf(millisecs);
                    String strTime = strSecs + " : " + strMilliSecs;
                    tvcTimer.setText("");
                    tvcTimer.setText(strTime);
                    timeTaken = (int)(10000 - millisUntilFinished);
                }
                public void onFinish() {
                    timeTaken = 10000;
                    tvcTimer.setText("0 : 000");
                    GameResults gResult = new GameResults(timeTaken,String.valueOf(asd.getOperand1()) + " + " + String.valueOf(asd.getOperand2()),false);
                    gScore.addGame(gResult);
                    updateExtraUI("TimeOut!");
                    timeOut = true;
                }
            };
            cTimer.start();
        }
    }


    //cancel timer
    void cancelTimer() {
        if(cTimer!=null){
            cTimer.cancel();
            cTimer = null;
        }
    }

    private ActivityGameBinding binding;
    private AdditionSingleDigit asd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if(getSupportActionBar() != null){
            this.getSupportActionBar().hide();
        }

        tvcTimer = binding.tvTimer;

        asd = new AdditionSingleDigit();
        gScore = new GameScore();
        nextQuestion();
    }

    private void nextQuestion(){
        asd.play();
        updateUI();
        enableOptions();
        if(timeOut){
            cancelTimer();
            timeOut = false;
        }
        startTimer();
    }

    public void clickNext(View view){
        if(asd.getQuestionCounter() >= 10){
            //asd.resetGame();
            //gScore.resetScore();
            Intent intent = new Intent(this, ScoreActivity.class);
            intent.putExtra("ASD_INSTANCE", asd);
            intent.putExtra("SCORE_INSTANCE", gScore);
            startActivity(intent);
        }else {
            nextQuestion();
        }
    }

    private void updateUI(){


        Integer[] opts = asd.getOptionArray();
        binding.tvOps1.setText(String.valueOf(opts[0]));
        binding.tvOps2.setText(String.valueOf(opts[1]));
        binding.tvOps3.setText(String.valueOf(opts[2]));
        binding.tvOps4.setText(String.valueOf(opts[3]));
        binding.tvOps5.setText(String.valueOf(opts[4]));
        binding.tvOps6.setText(String.valueOf(opts[5]));

        binding.tvOps1.setBackgroundColor(Color.parseColor("#004C69"));
        binding.tvOps2.setBackgroundColor(Color.parseColor("#004C69"));
        binding.tvOps3.setBackgroundColor(Color.parseColor("#004C69"));
        binding.tvOps4.setBackgroundColor(Color.parseColor("#004C69"));
        binding.tvOps5.setBackgroundColor(Color.parseColor("#004C69"));
        binding.tvOps6.setBackgroundColor(Color.parseColor("#004C69"));

        String qtnStr = String.valueOf(asd.getQuestionCounter()) + " / " + String.valueOf(asd.getTotalQuestions());
        binding.tvQuestion.setText(qtnStr);

        binding.tvOpr1.setBackgroundColor(Color.parseColor("#FF3A3021"));
        binding.tvOpr1.setText(String.valueOf(asd.getOperand1()));
        binding.tvOpr2.setBackgroundColor(Color.parseColor("#FF3A3021"));
        binding.tvOpr2.setText(String.valueOf(asd.getOperand2()));
        binding.tvOperator.setBackgroundColor(Color.parseColor("#FF3A3021"));
        binding.tvOperator.setText(asd.getOperator());

        binding.tvAns.setBackgroundColor(Color.parseColor("#FF49443D"));
        binding.tvAns.setText(" = ? ");

        binding.btnExit.setBackgroundColor(Color.parseColor("#FFAE2222"));


        binding.tvTimer.setBackgroundColor(Color.parseColor("#FF7C270C"));
        binding.tvQuestion.setBackgroundColor(Color.parseColor("#FF7C270C"));

        binding.tvOpsSelect.setBackgroundColor(Color.parseColor("#FF454733"));
        binding.tvOpsSelect.setText("select option below");
        binding.tvNext.setBackgroundColor(Color.parseColor("#FF6F780D"));
        binding.tvNext.setText("In Game");
        binding.tvNext.setClickable(false);
    }

    private void updateExtraUI(String opsStr){

        binding.tvOpsSelect.setText("select option below");
        binding.tvOpsSelect.setText(opsStr);
        if(asd.getQuestionCounter() == 10){
            binding.tvNext.setText("Finished");
            binding.tvAns.setText("Score : " + String.valueOf(gScore.getTotalScore()));
        }else {
            binding.tvNext.setText("Next");
            binding.tvAns.setText(String.valueOf(asd.getResult()));
        }
        binding.tvNext.setClickable(true);

    }

    public void exitApp(View view){
        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }

    public void optionClick(View view) {
        disableOptions();
        int clickedResult = 0;
        String resultStr = "";
        TextView clickedSquare = null;
        switch (view.getId()){
            case R.id.tvOps1:
                clickedSquare=binding.tvOps1;
                break;
            case R.id.tvOps2:
                clickedSquare=binding.tvOps2;
                break;
            case R.id.tvOps3:
                clickedSquare=binding.tvOps3;
                break;
            case R.id.tvOps4:
                clickedSquare=binding.tvOps4;
                break;
            case R.id.tvOps5:
                clickedSquare=binding.tvOps5;
                break;
            case R.id.tvOps6:
                clickedSquare=binding.tvOps6;
                break;
        }
        if(clickedSquare != null){
            clickedResult = Integer.parseInt(clickedSquare.getText().toString());
        }

        cancelTimer();
        boolean boolResult;

        if( clickedResult ==  asd.getResult()){
            resultStr = "Correct : " + String.valueOf(clickedResult);
            clickedSquare.setBackgroundColor(Color.parseColor("#99cc00"));
            boolResult = true;
        }else{
            resultStr = "Wrong : " + String.valueOf(clickedResult);
            clickedSquare.setBackgroundColor(Color.parseColor("#ff4444"));
            boolResult = false;
        }
        colorCorrectSquare();
        GameResults gResult = new GameResults(timeTaken,String.valueOf(asd.getOperand1()) + " + " + String.valueOf(asd.getOperand2()),boolResult);
        gScore.addGame(gResult);

        updateExtraUI(resultStr);
    }

    private void colorCorrectSquare(){
        int correct = asd.getResult();

        if(binding.tvOps1.getText().toString().equals(String.valueOf(correct))){
            binding.tvOps1.setBackgroundColor(Color.parseColor("#99cc00"));
        }else if (binding.tvOps2.getText().toString().equals(String.valueOf(correct))){
            binding.tvOps2.setBackgroundColor(Color.parseColor("#99cc00"));
        }else if (binding.tvOps3.getText().toString().equals(String.valueOf(correct))){
            binding.tvOps3.setBackgroundColor(Color.parseColor("#99cc00"));
        }else if (binding.tvOps4.getText().toString().equals(String.valueOf(correct))){
            binding.tvOps4.setBackgroundColor(Color.parseColor("#99cc00"));
        }else if (binding.tvOps5.getText().toString().equals(String.valueOf(correct))){
            binding.tvOps5.setBackgroundColor(Color.parseColor("#99cc00"));
        }else if (binding.tvOps6.getText().toString().equals(String.valueOf(correct))){
            binding.tvOps6.setBackgroundColor(Color.parseColor("#99cc00"));
        }

    }

    private void disableOptions(){
        binding.tvOps1.setClickable(false);
        binding.tvOps2.setClickable(false);
        binding.tvOps3.setClickable(false);
        binding.tvOps4.setClickable(false);
        binding.tvOps5.setClickable(false);
        binding.tvOps6.setClickable(false);
    }

    private void enableOptions(){
        binding.tvOps1.setClickable(true);
        binding.tvOps2.setClickable(true);
        binding.tvOps3.setClickable(true);
        binding.tvOps4.setClickable(true);
        binding.tvOps5.setClickable(true);
        binding.tvOps6.setClickable(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.

    }
}