package com.example.nayomishah.nihalchess;

/**
 * Created by nayomishah on 5/24/17.
 */
public class Pawn extends ChessPiece {

    public String getInitial() { return "p"; }

    public String getPieceName() { return "pawn"; }

    public boolean isValidMove(BoardSpot dest) {

        int yPos = getPlayer().getColor() == PlayerColor.WHITE ? 1 : -1;

        if (getLocation().getX() == dest.getX()) {

            return (dest.getPiece() == null
                    && getLocation().getY() == dest.getY() + yPos)
                    ||
                    (numberOfMoves() == 0
                            && dest.getPiece() == null
                            && getLocation().getY() == dest.getY() + (2*yPos));

        }

        if (Math.abs(getLocation().getX() - dest.getX()) == 1 && getLocation().getY() == dest.getY() + yPos) {


            if (dest.getPiece() != null) return true;


            ChessPiece piece = getBoard()[getLocation().getY()][dest.getX()].getPiece();

            if (piece != null && piece instanceof Pawn && piece.numberOfMoves() == 1) {


                if (piece.getPlayer().getColor() == getPlayer().getColor()) return false;

                return ((getPlayer().getColor() == PlayerColor.WHITE && dest.getY() == 2 || getPlayer().getColor() == PlayerColor.BLACK && dest.getY() == 5));

            }
        }

        return false;
    }


}
