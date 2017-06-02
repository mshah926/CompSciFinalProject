package com.example.nayomishah.nihalchess;

/**
 * Created by nayomishah on 5/24/17.
 */

public class Bishop extends ChessPiece {



    public String getInitial() { return "B"; }

    public String getPieceName() { return "bishop"; }

    public boolean isValidMove(BoardSpot dest) {
        int xPos = Math.abs( dest.getX() - getLocation().getX());
        int yPos= Math.abs( dest.getY() - getLocation().getY());
        if(xPos != yPos) //checking diagonal positions
        {
            return false;
        }
        return this.clearPathTo(dest);	//it can move if the diagonals are clear
    }

}

