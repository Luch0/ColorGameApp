package com.example.lucho.colorgame;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import android.widget.TextView;
import android.widget.RelativeLayout;
import android.graphics.drawable.ColorDrawable;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AlphaAnimation;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.content.SharedPreferences;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import java.util.Locale;
import android.content.res.AssetManager;
import android.graphics.Typeface;

public class GameActivity extends AppCompatActivity {

    ArrayList<Button> buttons = new ArrayList<>();
    String[] colors = {"RED", "BLUE", "BLACK", "WHITE", "GREEN", "GRAY", "PINK", "YELLOW", "ORANGE"};
    int score = 0;
    TextView tvScore;
    SharedPreferences prefs;
    CountDownTimer cdt;
    long gameTime;
    long millisRem;
    boolean flag;
    //change font
    AssetManager am;
    Typeface face;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //lock orientation

        //setTaskDescription(new ActivityManager.TaskDescription(null, null, Color.GRAY));

        //change font
        am = this.getApplicationContext().getAssets();
        face = Typeface.createFromAsset(am, String.format(Locale.US, "fonts/%s", "gillsans-ultrabold.ttf"));

        flag = true;

        /*
        View decor_View = getWindow().getDecorView();
        int ui_Options = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decor_View.setSystemUiVisibility(ui_Options);
        */


        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        /*
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        */

        tvScore = (TextView) findViewById(R.id.tvScore);
        tvScore.setTypeface(face);



        buttons.add((Button) findViewById(R.id.button1));
        buttons.add((Button) findViewById(R.id.button2));
        buttons.add((Button) findViewById(R.id.button3));
        buttons.add((Button) findViewById(R.id.button4));
        buttons.add((Button) findViewById(R.id.button5));
        buttons.add((Button) findViewById(R.id.button6));
        buttons.add((Button) findViewById(R.id.button7));
        buttons.add((Button) findViewById(R.id.button8));
        buttons.add((Button) findViewById(R.id.button9));

        buttonsOnClick(buttons);

        gameStart(colors, buttons);
        gameTime = 30000;
        countdownTimer(gameTime, 1000);
    }

    private void countdownTimer(long time, long interval) {
        cdt = new CountDownTimer(time, interval) {
            public void onTick(long millisUntilFinished) {
                millisRem = millisUntilFinished;
                TextView tvt = (TextView) findViewById(R.id.textViewTime);
                tvt.setTypeface(face);
                tvt.setText("Time: " + millisUntilFinished / 1000);
            }
            public void onFinish() {
                TextView tvt = (TextView) findViewById(R.id.textViewTime);
                tvt.setText("Game Over!");
                for (int i = 0; i < 9; i++) {
                    buttons.get(i).setEnabled(false);
                }

                //save if there is a new high score
                prefs = GameActivity.this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
                int oldScore = prefs.getInt("key", 0);
                if(score > oldScore ){
                    Editor edit = prefs.edit();
                    edit.putInt("key", score);
                    edit.commit();
                    MainActivity.tvHS.setText("High Score: " + score);
                    //MainActivity.tvHS.setText(Integer.toString(score));
                }

            }
        }.start();
    }

    private void buttonsOnClick(final ArrayList<Button> buttons) {
        final MediaPlayer mpRight = MediaPlayer.create(this, R.raw.right);
        final MediaPlayer mpWrong = MediaPlayer.create(this, R.raw.wrong);
        for(int i = 0; i < buttons.size(); i++){
            buttons.get(i).setTypeface(face);
            buttons.get(i).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    RelativeLayout rl = (RelativeLayout) findViewById(R.id.rlGame);
                    ColorDrawable bgColor = (ColorDrawable) rl.getBackground();
                    int col = bgColor.getColor();
                    TextView tvAnswer = (TextView) findViewById(R.id.textViewAnswer);
                    tvAnswer.setTypeface(face);
                    TextView tvAddSubs = (TextView) findViewById(R.id.tvAddSub);
                    tvAddSubs.setTypeface(face);

                    if (col == Color.WHITE && ((Button) v).getText().toString().equals("WHITE")) {
                        addSecond();
                        tvAddSubs.setText("+1");
                        fadeOutAnimate(tvAddSubs,250);
                        mpRight.start();
                        tvAnswer.setText("CORRECT");
                        tvScore.setText("Score: " + ++score);
                        fadeOutAnimate(tvAnswer,2000);
                    }
                    else if (col == Color.YELLOW && ((Button) v).getText().toString().equals("YELLOW")) {
                        addSecond();
                        tvAddSubs.setText("+1");
                        fadeOutAnimate(tvAddSubs,250);
                        mpRight.start();
                        tvAnswer.setText("CORRECT");
                        tvScore.setText("Score: " + ++score);
                        fadeOutAnimate(tvAnswer,2000);
                    }
                    else if (col == Color.RED && ((Button) v).getText().toString().equals("RED")) {
                        addSecond();
                        tvAddSubs.setText("+1");
                        fadeOutAnimate(tvAddSubs,250);
                        mpRight.start();
                        tvAnswer.setText("CORRECT");
                        tvScore.setText("Score: " + ++score);
                        fadeOutAnimate(tvAnswer,2000);
                    }
                    else if (col == Color.BLUE && ((Button) v).getText().toString().equals("BLUE")) {
                        addSecond();
                        tvAddSubs.setText("+1");
                        fadeOutAnimate(tvAddSubs,250);
                        mpRight.start();
                        tvAnswer.setText("CORRECT");
                        tvScore.setText("Score: " + ++score);
                        fadeOutAnimate(tvAnswer,2000);
                    }
                    else if (col == Color.GRAY && ((Button) v).getText().toString().equals("GRAY")) {
                        addSecond();
                        tvAddSubs.setText("+1");
                        fadeOutAnimate(tvAddSubs,250);
                        mpRight.start();
                        tvAnswer.setText("CORRECT");
                        tvScore.setText("Score: " + ++score);
                        fadeOutAnimate(tvAnswer,2000);
                    }
                    else if (col == Color.GREEN && ((Button) v).getText().toString().equals("GREEN")) {
                        addSecond();
                        tvAddSubs.setText("+1");
                        fadeOutAnimate(tvAddSubs,250);
                        mpRight.start();
                        tvAnswer.setText("CORRECT");
                        tvScore.setText("Score: " + ++score);
                        fadeOutAnimate(tvAnswer,2000);
                    }
                    else if (col == Color.rgb(255, 192, 203) && ((Button) v).getText().toString().equals("PINK")) {
                        addSecond();
                        tvAddSubs.setText("+1");
                        fadeOutAnimate(tvAddSubs,250);
                        mpRight.start();
                        tvAnswer.setText("CORRECT");
                        tvScore.setText("Score: " + ++score);
                        fadeOutAnimate(tvAnswer,2000);
                    }
                    else if (col == Color.BLACK && ((Button) v).getText().toString().equals("BLACK")) {
                        addSecond();
                        tvAddSubs.setText("+1");
                        fadeOutAnimate(tvAddSubs,250);
                        mpRight.start();
                        tvAnswer.setText("CORRECT");
                        tvScore.setText("Score: " + ++score);
                        fadeOutAnimate(tvAnswer,2000);
                    }
                    else if (col == Color.rgb(255, 165, 0) && ((Button) v).getText().toString().equals("ORANGE")) {
                        addSecond();
                        tvAddSubs.setText("+1");
                        fadeOutAnimate(tvAddSubs,250);
                        mpRight.start();
                        tvAnswer.setText("CORRECT");
                        tvScore.setText("Score: " + ++score);
                        fadeOutAnimate(tvAnswer,2000);
                    }
                    else {
                        subsSecond();
                        tvAddSubs.setText("-1");
                        fadeOutAnimate(tvAddSubs,250);
                        mpWrong.start();
                        tvAnswer.setText("WRONG");
                        fadeOutAnimate(tvAnswer,2000);
                    }

                    randomizeColor(colors);
                    randomizeButtons(colors, buttons);
                }
            });
        }
    }

    private void gameStart(String[] c, ArrayList<Button> bs) {
        randomizeColor(c);
        randomizeButtons(c, bs);
    }

    private void randomizeButtons(String[] c, ArrayList<Button> bs) {
        ArrayList<Integer> nums = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) {
            nums.add(i);
        }
        Collections.shuffle(nums);
        for (int i = 0; i < 9; i++) {
            Button b = bs.get(i);
            b.setText(c[nums.get(i)]);
        }

    }

    private void randomizeColor(String[] c) {
        Random rand = new Random();
        int n = rand.nextInt(9);
        switch (c[n]) {
            case "WHITE":
                findViewById(R.id.rlGame).setBackgroundColor(Color.WHITE);
                checkBGcolor(c[n]);
                break;
            case "YELLOW":
                findViewById(R.id.rlGame).setBackgroundColor(Color.YELLOW);
                checkBGcolor(c[n]);
                break;
            case "RED":
                findViewById(R.id.rlGame).setBackgroundColor(Color.RED);
                checkBGcolor(c[n]);
                break;
            case "BLUE":
                findViewById(R.id.rlGame).setBackgroundColor(Color.BLUE);
                checkBGcolor(c[n]);
                break;
            case "GRAY":
                findViewById(R.id.rlGame).setBackgroundColor(Color.GRAY);
                checkBGcolor(c[n]);
                break;
            case "GREEN":
                findViewById(R.id.rlGame).setBackgroundColor(Color.GREEN);
                checkBGcolor(c[n]);
                break;
            case "PINK":
                findViewById(R.id.rlGame).setBackgroundColor(Color.rgb(255, 192, 203));
                checkBGcolor(c[n]);
                break;
            case "BLACK":
                findViewById(R.id.rlGame).setBackgroundColor(Color.BLACK);
                checkBGcolor(c[n]);
                break;
            case "ORANGE":
                findViewById(R.id.rlGame).setBackgroundColor(Color.rgb(255, 165, 0));
                checkBGcolor(c[n]);
                break;
        }
    }

    private void fadeOutAnimate(final TextView tv, long duration){
        Animation fadeOut = new AlphaAnimation(1f, 0f);
        fadeOut.setStartOffset(1000);
        fadeOut.setDuration(duration);
        fadeOut.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                tv.setVisibility(View.VISIBLE);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                tv.setVisibility(View.INVISIBLE);
            }
        });
        tv.startAnimation(fadeOut);
    }

    private void checkBGcolor(String col){
        TextView tv1 = (TextView) findViewById(R.id.textViewTime);
        TextView tv2 = (TextView) findViewById(R.id.tvScore);
        TextView tv3 = (TextView) findViewById(R.id.textViewAnswer);
        TextView tv4 = (TextView) findViewById(R.id.tvAddSub);
        if(col.equals("BLACK")){
            tv1.setTextColor(Color.WHITE);
            tv2.setTextColor(Color.WHITE);
            tv3.setTextColor(Color.WHITE);
            tv4.setTextColor(Color.WHITE);
        }
        else{
            tv1.setTextColor(Color.BLACK);
            tv2.setTextColor(Color.BLACK);
            tv3.setTextColor(Color.BLACK);
            tv4.setTextColor(Color.BLACK);
        }

    }

    private void addSecond(){
        cdt.cancel();
        countdownTimer(millisRem + 1000, 1000);
    }

    private void subsSecond(){
        cdt.cancel();
        countdownTimer(millisRem - 1000, 1000);
    }


    @Override
    public void onPause() {
        super.onPause();
        cdt.cancel();
    }

    @Override
    public void onResume() {
        super.onResume();
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        if(flag==false) countdownTimer(millisRem , 1000);
        flag = false;
    }


    /*
    @Override
    public void onStop() {
        super.onStop();
        cdt.cancel();
    }
    */

    /*
    @Override
    public void onBackPressed() { //disable back button
    }
    */
}