package com.example.nayomishah.nihalchess;

/**
 * Created by nayomishah on 6/1/17.
 */
import java.util.LinkedList;
import java.util.ListIterator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;

public class ReplayActivity extends Activity {

    private Game game;
    private static boolean RUN_ONCE = false;
    private SquareAdapter adapter;
    private GridView chessboard;
    private LinkedList<Move> moves;
    private ListIterator<Move> listIterator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.replay_activity);

        if (!RUN_ONCE) {

            RUN_ONCE = true;
            this.game = new Game();
            moves = PlayedGames.playedGames.get(PlayedGames.activeIndex);
            listIterator = moves.listIterator();
            adapter = new SquareAdapter(this, game.getBoard());

        }

        final GridView chessBoardGridView = (GridView)findViewById(R.id.chessboard);

        initNextButton();
        initPreviousButton();
        chessBoardGridView.setAdapter(adapter);


        this.chessboard = chessBoardGridView;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.replay, menu);
        return true;
    }

    private int updateColor(int position) {

        //background brown or light brown depending of the position
        int col = position/8 %2;
        if (col == 0)
        {
            if (position%2 == 0)
                return Color.parseColor("#DFAE74");
            else
                return Color.parseColor("#6B4226");

        }
        else
        {
            if (position%2 == 0)
                return Color.parseColor("#6B4226");
            else
                return Color.parseColor("#DFAE74");
        }
    }

    private void initNextButton() {

        Button nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View argo) {

                if (!listIterator.hasNext()) return;

                Move move = listIterator.next();
                game.move(move.getSourcePosition(), move.getDestPosition());
                adapter.notifyDataSetChanged();
                chessboard.setAdapter(adapter);
            }
        });
    }

    private void initPreviousButton() {

        Button previousButton = (Button) findViewById(R.id.previousButton);
        previousButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View argo) {

                if (!listIterator.hasPrevious()) return;
                listIterator.previous();
                game.undo();
                adapter.notifyDataSetChanged();
                chessboard.setAdapter(adapter);
            }
        });
    }


    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Quit game?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                startActivity(new Intent(ReplayActivity.this, HomeActivity.class));
                RUN_ONCE = false;
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()) {
            case (R.id.action_settings):
                return true;
            case (android.R.id.home):
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

