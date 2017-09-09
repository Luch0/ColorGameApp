package com.example.lucho.colorgame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.TextView;
import java.util.Locale;
import java.util.Random;
import android.graphics.Color;
import android.animation.ValueAnimator;
import android.animation.ObjectAnimator;
import android.animation.ArgbEvaluator;
import android.graphics.drawable.ColorDrawable;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.MenuItem;

//e91e63
//ac003a
public class MainActivity extends AppCompatActivity {

    SharedPreferences prefs;
    public static TextView tvHS; //static variable textview for updating high score from other activity
    //change font
    AssetManager am;
    Typeface face;
    Button settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); //lock orientation

        //setTaskDescription(new ActivityManager.TaskDescription(null, null, Color.GRAY));

        //change font
        am = this.getApplicationContext().getAssets();
        face = Typeface.createFromAsset(am, String.format(Locale.US, "fonts/%s", "gillsans-ultrabold.ttf"));

        /*
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        */

        /*
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        */



        tvHS = (TextView) findViewById(R.id.tvHighScore);
        tvHS.setTypeface(face);

        //get stored high score
        prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        int hs = prefs.getInt("key", 0);
        tvHS.setText("High Score: " + hs);
        //tvHS.setText(Integer.toString(hs));


        settingsButton = (Button) findViewById(R.id.infoButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openOptionsMenu();
            }
        });


        ArrayList<TextView> tvs = new ArrayList<>();
        tvs.add((TextView) findViewById(R.id.tvC1));
        tvs.add((TextView) findViewById(R.id.tvC2));
        tvs.add((TextView) findViewById(R.id.tvC3));
        tvs.add((TextView) findViewById(R.id.tvC4));
        tvs.add((TextView) findViewById(R.id.tvC5));
        tvs.add((TextView) findViewById(R.id.tvC6));
        tvs.add((TextView) findViewById(R.id.tvC7));
        tvs.add((TextView) findViewById(R.id.tvC8));
        tvs.add((TextView) findViewById(R.id.tvC9));
        tvs.add((TextView) findViewById(R.id.tvC10));
        tvs.add((TextView) findViewById(R.id.tvC11));
        tvs.add((TextView) findViewById(R.id.tvC12));

        for (final TextView tv : tvs) {
            tv.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Random rnd = new Random();
                    //tv.setBackgroundColor(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));

                    ColorDrawable cd = (ColorDrawable) tv.getBackground();
                    int colorCode = cd.getColor();
                    ValueAnimator colorAnim = ObjectAnimator.ofInt(tv, "backgroundColor",colorCode, colorCode, Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
                    colorAnim.setDuration(500);
                    colorAnim.setEvaluator(new ArgbEvaluator());
                    //colorAnim.setRepeatCount(ValueAnimator.INFINITE);
                    //colorAnim.setRepeatMode(ValueAnimator.REVERSE);
                    colorAnim.start();
                }
            });
        }

        startGameButton();
    }

    private void startGameButton() {
        final Button btn = (Button)findViewById(R.id.buttonStartGame);
        btn.setTypeface(face);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.start);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mp.start();
                startActivity(new Intent(getApplicationContext(), GameActivity.class));
            }
        });

        //info box
        final Button btninfo = (Button)findViewById(R.id.infoButton);

        btninfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Color Quick")
                        .setMessage("Luis Calle")
                        .setIcon(android.R.drawable.alert_dark_frame)
                        .show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                //startActivity(new Intent(this, About.class));
                return true;
            case R.id.help:
                //startActivity(new Intent(this, Help.class));
                return true;
            case R.id.other:
                //startActivity(new Intent(this, Other.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}