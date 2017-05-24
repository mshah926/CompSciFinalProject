package com.example.nayomishah.nihalchess;

/**
 * Created by nayomishah on 5/24/17.
 */

public class ChessBoard{
    private ChessPiece [][] x;
    public ChessBoard() {

        x = new ChessPiece[8][8];
        // Black
        x[0][0] = new Rook(R.drawable.Rook);//Rook 1
        x[0][1] = new Knight(R.drawable.Knight);//Knight 1
        x[0][2] = new Bishop(R.drawable.Bishop);//Bishop 1
        x[0][3] = new King(R.drawable.King);
        x[0][4] = new Queen(R.drawable.Queen);
        x[0][5] = new Bishop(R.drawable.Bishop);//Bishop 2
        x[0][6] = new Knight(R.drawable.Knight);//Knight 2
        x[0][7] = new Rook(R.drawable.Rook);//Rook 2
        x[1][0] = new Pawn(R.drawable.Pawn);//Pawn 1
        x[1][1] = new Pawn(R.drawable.Pawn);//Pawn 2
        x[1][2] = new Pawn(R.drawable.Pawn);//Pawn 3
        x[1][3] = new Pawn(R.drawable.Pawn);//Pawn 4
        x[1][4] = new Pawn(R.drawable.Pawn);//Pawn 5
        x[1][5] = new Pawn(R.drawable.Pawn);//Pawn 6
        x[1][6] = new Pawn(R.drawable.Pawn);//Pawn 7
        x[1][7] = new Pawn(R.drawable.Pawn);//Pawn 8

        //White
        x[7][0] = new Rook(R.drawable.WhiteRook);//Rook 3
        x[7][1] = new Knight(R.drawable.WhiteKnight);//Knight 3
        x[7][2] = new Bishop(R.drawable.WhiteBishop);//Bishop 3
        x[7][3] = new King(R.drawable.WhiteKing);
        x[7][4] = new Queen(R.drawable.WhiteQueen);
        x[7][5] = new Bishop(R.drawable.WhiteBishop);//Bishop 4
        x[7][6] = new Knight(R.drawable.WhiteKnight);//Knight 4
        x[7][7] = new Rook(R.drawable.WhiteRook);//Rook 4
        x[6][0] = new Pawn(R.drawable.WhitePawn);//Pawn 9
        x[6][1] = new Pawn(R.drawable.WhitePawn);//Pawn 10
        x[6][2] = new Pawn(R.drawable.WhitePawn);//Pawn 11
        x[6][3] = new Pawn(R.drawable.WhitePawn);//Pawn 12
        x[6][4] = new Pawn(R.drawable.WhitePawn);//Pawn 13
        x[6][5] = new Pawn(R.drawable.WhitePawn);//Pawn 14
        x[6][6] = new Pawn(R.drawable.WhitePawn);//Pawn 15
        x[6][7] = new Pawn(R.drawable.WhitePawn);//Pawn 16
    }
}

