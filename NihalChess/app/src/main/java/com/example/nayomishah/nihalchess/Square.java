package com.example.nayomishah.nihalchess;

/**
 * Created by nayomishah on 6/1/17.
 */

import java.io.Serializable;


public class Square implements Serializable{


    private int x;	//Rank is row, x value
    private int y;	//File is column, y value

    private static final long serialVersionUID = 1L;

    private ChessPiece piece; //this chess piece is occupying the current position (square)

    public int getX() { return x; }

    public int getY() { return y; }

    public Square(int r, int c) {
        this.y = r;
        this.x = c;
    }

    public ChessPiece getPiece() { return piece; }

    public void setPiece(ChessPiece piece) { this.piece = piece; }

    @Override
    public boolean equals(Object o) {

        if (!(o instanceof Square)) return false;

        if (this.getX() == ((Square) o).getX() && this.getY() == ((Square) o).getY()) return true;

        return false;
    }

}
