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

        Queen queen = new Queen();
        Bishop bishop = new Bishop();
        Rook rook = new Rook();
        Knight knight = new Knight();
        Pawn pawn = new Pawn();

        if(inCheck(kingLoc) && !queen.noMovesToSaveKing(kingLoc) &&
                !bishop.noMovesToSaveKing(kingLoc) &&
                !rook.noMovesToSaveKing(kingLoc) &&
                !knight.noMovesToSaveKing(kingLoc) &&
                !pawn.noMovesToSaveKing(kingLoc))
        {
            return true;
        }
        return false;
    }
}

