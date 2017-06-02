package com.example.nayomishah.nihalchess;

/**
 * Created by nayomishah on 5/24/17.
 */
public class King extends ChessPiece {


    public String getInitial() { return "K"; }

    public String getPieceName() { return "king"; }


    public boolean isValidMove(BoardSpot dest)
    {
        int xPos = Math.abs( dest.getX() - getLocation().getX());
        int yPos= Math.abs( dest.getY() - getLocation().getY());

        //moving one space in any direction.
        if (((xPos) <=1 && (yPos) <=1)) return true;

        if (getPlayer().getColor() == PlayerColor.WHITE) {

            if (getLocation().getX() == 4 && getLocation().getY() == 7 && numberOfMoves() == 0) {

                if ((dest.getX() == 6 && dest.getY() == 7)) {

                    ChessPiece rook = getBoard()[dest.getX() + 1][dest.getY()].getPiece();

                    if (!(rook instanceof Rook && rook.numberOfMoves() == 0)) return false;

                    return clearPathTo(dest);
                }
                else if ((dest.getX() == 2 && dest.getY() == 7)) {

                    ChessPiece rook = getBoard()[dest.getX() - 2][dest.getY()].getPiece();

                    if (!(rook instanceof Rook && rook.numberOfMoves() == 0)) return false;

                    return clearPathTo(dest);

                }

            }
            return false;
        }
        else {

            if (getLocation().getX() == 4 && getLocation().getY() == 0 && numberOfMoves() == 0) {

                if ((dest.getX() == 6 && dest.getY() == 0)) {

                    ChessPiece rook = getBoard()[dest.getX() + 1][dest.getY()].getPiece();

                    if (!(rook instanceof Rook && rook.numberOfMoves() == 0)) return false;

                    return clearPathTo(dest);
                }
                else if ((dest.getX() == 2 && dest.getY() == 0)) {

                    ChessPiece rook = getBoard()[dest.getX() - 2][dest.getY()].getPiece();

                    if (!(rook instanceof Rook && rook.numberOfMoves() == 0)) return false;

                    return clearPathTo(dest);
                }


            }
            return false;
        }

    }

    public boolean inCheck(BoardSpot kingLoc) {

        for (ChessPiece chessP: getPlayer().getOpponent().getPieces())
        {
            if (chessP.getLocation() == null) {
                return false;

            }
            if (chessP.isValidMove(kingLoc) && chessP.getLocation() != null)
            {
                return true;
            }

        }

        return false;
    }

    public boolean checkmate(BoardSpot kingLoc) {

        BoardSpot forward = new BoardSpot(kingLoc.getY() + 1, kingLoc.getX());
        BoardSpot backward = new BoardSpot(kingLoc.getY() - 1, kingLoc.getX());
        BoardSpot rightUp = new BoardSpot(kingLoc.getY() + 1, kingLoc.getX() + 1);
        BoardSpot leftUp = new BoardSpot(kingLoc.getY() + 1, kingLoc.getX() - 1);
        BoardSpot rightDown = new BoardSpot(kingLoc.getY() - 1, kingLoc.getX() + 1);
        BoardSpot leftDown = new BoardSpot(kingLoc.getY() - 1, kingLoc.getX() - 1);

        if (forward.getX() >= 0 && forward.getX() <= 7
                && forward.getY() >= 0 && forward.getY() <= 7) {

            if (!inCheck(forward) && getBoard()[forward.getY()][forward.getX()].getPiece() == null) return false;

        }
        if (backward.getX() >= 0 && backward.getY() <= 7
                && backward.getY() >= 0 && backward.getY() <= 7) {

            if (!inCheck(backward) && getBoard()[backward.getY()][backward.getX()].getPiece() == null) return false;
        }
        if (rightUp.getX() >= 0 && rightUp.getY() <= 7
                && rightUp.getY() >= 0 && rightUp.getY() <= 7) {

            if (!inCheck(rightUp) && getBoard()[rightUp.getY()][rightUp.getX()].getPiece() == null) return false;
        }
        if (leftUp.getX() >= 0 && leftUp.getY() <= 7
                && leftUp.getY() >= 0 && leftUp.getY() <= 7) {

            if (!inCheck(leftUp) && getBoard()[leftUp.getY()][leftUp.getX()].getPiece() == null) return false;
        }
        if (rightDown.getX() >= 0 && rightDown.getY() <= 7
                && rightDown.getY() >= 0 && rightDown.getY() <= 7) {

            if (!inCheck(rightDown) && getBoard()[rightDown.getY()][rightDown.getX()].getPiece() == null) return false;
        }
        if (leftDown.getX() >= 0 && leftDown.getY() <= 7
                && leftDown.getY() >= 0 && leftDown.getY() <= 7) {

            if (!inCheck(leftDown) && getBoard()[leftDown.getY()][leftDown.getX()].getPiece() == null) return false;
        }

        return true;

    }
}

