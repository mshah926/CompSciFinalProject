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

    public boolean noMovesToSaveKing(BoardSpot kingLoc)
    {
        BoardSpot forward = new BoardSpot(kingLoc.getY() + 1, kingLoc.getX());
        BoardSpot backward = new BoardSpot(kingLoc.getY() - 1, kingLoc.getX());
        BoardSpot rightUp = new BoardSpot(kingLoc.getY() + 1, kingLoc.getX() + 1);
        BoardSpot leftUp = new BoardSpot(kingLoc.getY() + 1, kingLoc.getX() - 1);
        BoardSpot rightDown = new BoardSpot(kingLoc.getY() - 1, kingLoc.getX() + 1);
        BoardSpot leftDown = new BoardSpot(kingLoc.getY() - 1, kingLoc.getX() - 1);

        Bishop bishop = new Bishop();

        if(bishop.isValidMove(forward)==false && bishop.isValidMove(backward) == false
                && bishop.isValidMove(rightUp) == false && bishop.isValidMove(leftUp) == false && bishop.isValidMove(rightDown) == false
                && bishop.isValidMove(leftDown) == false)
        {
            return true;
        }
        return false;
    }

}

