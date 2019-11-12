package com.eliffuruncu.catchthekenny;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timeTxt,scoreTxt;
    int score;
    ImageView img0,img1,img2,img3,img4,img7,img5,img6,img8;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeTxt=(TextView)findViewById(R.id.timeTxtView);
        scoreTxt=(TextView)findViewById(R.id.scoreTxtView);

        img0=(ImageView)findViewById(R.id.imageView0);
        img1=(ImageView)findViewById(R.id.imageView1);
        img2=(ImageView)findViewById(R.id.imageView2);
        img3=(ImageView)findViewById(R.id.imageView3);
        img4=(ImageView)findViewById(R.id.imageView4);
        img5=(ImageView)findViewById(R.id.imageView5);
        img6=(ImageView)findViewById(R.id.imageView6);
        img7=(ImageView)findViewById(R.id.imageView7);
        img8=(ImageView)findViewById(R.id.imageView8);

        imageArray=new ImageView[]{img0,img1,img2,img3,img4,img5,img6,img7,img8};

        hideImages();

        score=0;

        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long l) {
            timeTxt.setText("Time : "+l/1000);
            }

            @Override
            public void onFinish() {
            timeTxt.setText("Time off!");
            handler.removeCallbacks(runnable);
                for(ImageView imageView:imageArray)
                {
                    imageView.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart Game");
                alert.setMessage("Are you sure to restart game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                     //restart
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Game Over!", Toast.LENGTH_LONG).show();
                    }
                });

                alert.show();
            }
        }.start();
    }
    public void increaseScore(View view)
    {
        score++;
        scoreTxt.setText("Score : "+score);
    }

    public void hideImages()
    {
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for(ImageView imageView:imageArray)
                {
                    imageView.setVisibility(View.INVISIBLE);
                }
                Random random=new Random();
                int i=random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,500);
            }
        };
        handler.post(runnable);


    }
}
