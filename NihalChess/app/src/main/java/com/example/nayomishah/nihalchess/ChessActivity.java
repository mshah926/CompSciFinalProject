package com.example.nayomishah.nihalchess;

/**
 * Created by nayomishah on 6/1/17.
 */
import java.util.ArrayList;
import java.util.Random;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class ChessActivity extends AppCompatActivity implements OnItemClickListener {

    private Game game;
    private boolean record;
    private static boolean RUN_ONCE = false;
    private String gameName;
    private TextView turnView;
    private GridView chessboard;
    private View[] squaresSelected;
    private int[] squarePositions;
    private SquareAdapter adapter;
    private boolean drawPressed;
    private boolean drawPressedThisTurn;
    private boolean undoPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chess_activity);

        if (!RUN_ONCE) {

            RUN_ONCE = true;
            recordGame();
            this.game = new Game();
            squaresSelected = new View[2];
            squarePositions = new int[2];
            adapter = new SquareAdapter(this, game.getBoard());
            turnView = (TextView)findViewById(R.id.turnView);

        }


        initAIButton();
        initResignButton();
        initDrawButton();
        initUndoButton();

        final GridView chessBoardGridView = (GridView)findViewById(R.id.chessboard);

        chessBoardGridView.setAdapter(adapter);

        chessBoardGridView.setOnItemClickListener(this);

        this.chessboard = chessBoardGridView;


    }

    private void recordGame() {


        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        record = true;

                        AlertDialog.Builder alert = new AlertDialog.Builder(ChessActivity.this);
                        alert.setTitle("Record Game");
                        alert.setMessage("Enter a name for this game");

                        // Set an EditText view to get user input
                        final EditText input = new EditText(ChessActivity.this);
                        alert.setView(input);
                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                String value = input.getText().toString();
                                gameName = value;
                            }
                        });

                        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                record = false;
                            }
                        });

                        alert.show();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        record = false;
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Want to record this game?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.chess, menu);
        return true;
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

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Quit game?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                startActivity(new Intent(ChessActivity.this, HomeActivity.class));
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
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        //selecting a piece to move.
        if (squaresSelected[0] == null) {

            Square selectedSquare = game.getBoard()[position/8][position%8];

            if (selectedSquare.getPiece() == null) return;

            if (selectedSquare.getPiece().getPlayer().getColor() != game.getCurrentPlayer().getColor()) return;

            squaresSelected[0] = view;
            squarePositions[0] = position;

            view.setBackgroundColor(Color.BLUE);

        }
        //selecting a square to move to
        else {

            squaresSelected[1] = view;
            squarePositions[1] = position;

            if (game.move(squarePositions[0], squarePositions[1])) {

                adapter.notifyDataSetChanged();
                chessboard.setAdapter(adapter);
                changeTurnText();

                String toastMessage = "";
                Toast toast = null;
                if (game.whiteWin() || game.blackWin()) {

                    if (record) {
                        PlayedGames.playedGames.add(game.getMoves());
                        PlayedGames.gameNames.add(gameName);
                    }

                    final String winner = game.whiteWin() == true ? "White" : "Black";

                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    Intent intent = getIntent();
                                    finish();
                                    RUN_ONCE = false;
                                    startActivity(intent);
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    startActivity(new Intent(ChessActivity.this, HomeActivity.class));
                                    RUN_ONCE = false;
                                    finish();
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(winner + " wins! Want to play again?").setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();

                }
                else if (game.blackInCheck()) {

                    toastMessage = "Black King in check.";
                    toast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);
                    toast.show();
                }
                else if (game.whiteInCheck()) {

                    toastMessage = "White King in check.";
                    toast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);
                    toast.show();
                }


            } else {
                Toast toast = Toast.makeText(this, "Illegal Move", Toast.LENGTH_SHORT);
                toast.show();
            }

            squaresSelected[0].setBackgroundColor(updateColor(squarePositions[0]));
            squaresSelected[0] = null;
            squaresSelected[1] = null;
            undoPressed = false;

        }

        checkDraw();

    }

    private void checkDraw() {

        if (drawPressed && !drawPressedThisTurn) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Draw");
            builder.setMessage("Accept draw?");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    Intent intent = getIntent();
                                    finish();
                                    RUN_ONCE = false;
                                    startActivity(intent);
                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    dialog.dismiss();
                                    startActivity(new Intent(ChessActivity.this, HomeActivity.class));
                                    RUN_ONCE = false;
                                    finish();
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(ChessActivity.this);
                    builder.setMessage("Draw. Want to play again?").setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    drawPressed = false;
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    private void changeTurnText() {

        if (turnView.getText().toString().compareTo(getResources().getString(R.string.white_turn)) == 0) {

            turnView.setText(getResources().getString(R.string.black_turn));
        }
        else {

            turnView.setText(getResources().getString(R.string.white_turn));
        }

        drawPressedThisTurn = false;
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

    private void initAIButton() {

        Button AIButton = (Button) findViewById(R.id.AIButton);
        AIButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View argo) {

                Toast toast = Toast.makeText(ChessActivity.this, "Available in Premium Version Only", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    private void initDrawButton() {

        Button drawButton = (Button) findViewById(R.id.drawButton);
        drawButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View argo) {
                draw();
            }
        });
    }

    private void initResignButton() {

        Button resignButton = (Button) findViewById(R.id.resignButton);
        resignButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View argo) {

                resign();
            }
        });
    }

    private void initUndoButton() {

        Button undoButton = (Button) findViewById(R.id.undoButton);
        undoButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View argo) {

                if (!undoPressed) {
                    undoPressed = true;
                    if (game.undo()) changeTurnText();
                    adapter.notifyDataSetChanged();
                    chessboard.setAdapter(adapter);
                }

            }
        });
    }

    private void AI() {

    }

    private void resign() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Resign");
        builder.setMessage("Are you sure?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                final String winner = game.getCurrentPlayer().getColor() == PlayerColor.WHITE ? "Black" : "White";

                if (record) {
                    PlayedGames.playedGames.add(game.getMoves());
                    PlayedGames.gameNames.add(gameName);
                }

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                Intent intent = getIntent();
                                finish();
                                RUN_ONCE = false;
                                startActivity(intent);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                startActivity(new Intent(ChessActivity.this, HomeActivity.class));
                                RUN_ONCE = false;
                                finish();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(ChessActivity.this);
                builder.setMessage(winner + " wins! Want to play again?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
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


    private void draw() {

        if (!drawPressed) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Draw");
            builder.setMessage("Request draw?");

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    drawPressed = true;
                    drawPressedThisTurn = true;
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    drawPressed = false;
                    drawPressedThisTurn = false;
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }
}
