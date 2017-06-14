package com.example.nayomishah.nihalchess;

/**
 * Created by nayomishah on 5/24/17.
 */
public class Rook extends ChessPiece {

    public String getInitial() { return "R"; }

    public String getPieceName() { return "rook"; }

    public boolean isValidMove(BoardSpot dest) {

        //means not horizontal or vertical move.
        if (getLocation().getY() != dest.getY() && getLocation().getX() != dest.getX()) return false;

        return this.clearPathTo(dest);
    }





}
