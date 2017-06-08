package com.example.nayomishah.nihalchess;

/**
 * Created by nayomishah on 5/24/17.
 */
public class Queen extends ChessPiece {

    public String getInitial() { return "Q"; }

    public String getPieceName() { return "queen"; }

    public boolean isValidMove(BoardSpot dest) {

        if ((getLocation().getX() == dest.getX()) ||
                getLocation().getY() == dest.getY() ||
                Math.abs(getLocation().getX() - dest.getX()) == Math.abs(getLocation().getY() - dest.getY())) {
            return this.clearPathTo(dest);
        }
        return false;
    }
    public boolean noMovesToSaveKing(BoardSpot kingLoc)
    {
        BoardSpot forward = new BoardSpot(kingLoc.getY() + 1, kingLoc.getX());
        BoardSpot backward = new BoardSpot(kingLoc.getY() - 1, kingLoc.getX());
        BoardSpot rightUp = new BoardSpot(kingLoc.getY() + 1, kingLoc.getX() + 1);
        BoardSpot leftUp = new BoardSpot(kingLoc.getY() + 1, kingLoc.getX() - 1);
        BoardSpot rightDown = new BoardSpot(kingLoc.getY() - 1, kingLoc.getX() + 1);
        BoardSpot leftDown = new BoardSpot(kingLoc.getY() - 1, kingLoc.getX() - 1);

        Queen queen = new Queen();

        if(queen.isValidMove(forward)==false && queen.isValidMove(backward) == false
                && queen.isValidMove(rightUp) == false && queen.isValidMove(leftUp) == false && queen.isValidMove(rightDown) == false
                && queen.isValidMove(leftDown) == false)
        {
            return true;
        }
        return false;
    }


}

