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

    public boolean noMovesToSaveKing(BoardSpot kingLoc)
    {
        BoardSpot forward = new BoardSpot(kingLoc.getY() + 1, kingLoc.getX());
        BoardSpot backward = new BoardSpot(kingLoc.getY() - 1, kingLoc.getX());
        BoardSpot rightUp = new BoardSpot(kingLoc.getY() + 1, kingLoc.getX() + 1);
        BoardSpot leftUp = new BoardSpot(kingLoc.getY() + 1, kingLoc.getX() - 1);
        BoardSpot rightDown = new BoardSpot(kingLoc.getY() - 1, kingLoc.getX() + 1);
        BoardSpot leftDown = new BoardSpot(kingLoc.getY() - 1, kingLoc.getX() - 1);

        Rook rook = new Rook();

        if(!rook.isValidMove(forward) && !rook.isValidMove(backward)
                && !rook.isValidMove(rightUp) && !rook.isValidMove(leftUp) && !rook.isValidMove(rightDown)
                && !rook.isValidMove(leftDown))
        {
            return true;
        }
        return false;
    }


}
