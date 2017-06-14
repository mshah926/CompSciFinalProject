package com.example.nayomishah.nihalchess;

/**
 * Created by nayomishah on 5/24/17.
 */
public class Knight extends ChessPiece {

    public String getInitial() { return "N"; }

    public String getPieceName() { return "knight"; }

	/*
	 * Specific for rules of Knight chess piece:
	 * Only piece that can jump over pieces
	 * "L" shape movement
	 */

    public boolean isValidMove(BoardSpot dest)
    {
        int xPos = Math.abs( dest.getX() - getLocation().getX());
        int yPos= Math.abs( dest.getY() - getLocation().getY());

        if ((xPos == 2 && yPos == 1)) return true;

        if ((xPos == 1 && yPos == 2)) return true;


        return false;
    }


}
