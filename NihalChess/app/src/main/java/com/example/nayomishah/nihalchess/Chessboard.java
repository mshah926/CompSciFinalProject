package com.example.nayomishah.nihalchess;

/**
 * Created by nayomishah on 5/24/17.
 */

public class ChessBoard{
    private ChessPiece [][] x;
    public ChessBoard() {

        x = new ChessPiece[8][8];
        // White
        x[0][0] = Rook1;
        x[0][1] = Knight1;
        x[0][2] = Bishop1;
        x[0][3] = new King(R.drawable.King);
        x[0][4] = Queen;
        x[0][5] = Bishop2;
        x[0][6] = Knight2;
        x[0][7] = Rook2;
        x[1][0] = Pawn1;
        x[1][1] = Pawn2;
        x[1][2] = Pawn3;
        x[1][3] = Pawn4;
        x[1][4] = Pawn5;
        x[1][5] = Pawn6;
        x[1][6] = Pawn7;
        x[1][7] = Pawn8;

        //Black
        x[7][0] = Rook3;
        x[7][1] = Knight3;
        x[7][2] = Bishop3;
        x[7][3] = King2;
        x[7][4] = Queen2;
        x[7][5] = Bishop4;
        x[7][6] = Knight4;
        x[7][7] = Rook4;
        x[6][0] = Pawn9;
        x[6][1] = Pawn10;
        x[6][2] = Pawn11;
        x[6][3] = Pawn12;
        x[6][4] = Pawn13;
        x[6][5] = Pawn14;
        x[6][6] = Pawn15;
        x[6][7] = Pawn16;
    }
}
