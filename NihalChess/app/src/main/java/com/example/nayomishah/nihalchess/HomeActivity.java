package com.example.nayomishah.nihalchess;

/**
 * Created by nayomishah on 6/1/17.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    public static boolean RUN_ONCE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        initPlayButton();

    }


    private void initPlayButton() {

        Button playButton = (Button) findViewById(R.id.playButton);
        playButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View argo) {

                startActivity(new Intent(HomeActivity.this, ChessActivity.class));
            }
        });
    }



}

