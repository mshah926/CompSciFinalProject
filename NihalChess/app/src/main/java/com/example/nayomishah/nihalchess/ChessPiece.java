package com.example.nayomishah.nihalchess;

import android.widget.ImageView;
import android.content.Context;
/**
 * Created by nayomishah on 5/24/17.
 */

public abstract class ChessPiece {


    private BoardSpot[][] board;

    private BoardSpot location;

    private Player player;

    private int moves;

    public abstract String getInitial();

    public abstract String getPieceName();

    public abstract boolean isValidMove(BoardSpot dest);

    public boolean canMoveTo(BoardSpot dest) {

        if (location.equals(dest)) return false;

        if (dest.getPiece() != null) {

            if (player.getColor() == dest.getPiece().getPlayer().getColor()) {

                return false;
            }
        }

        //implemented on a piece by piece basis.
        if (!isValidMove(dest))  return false;

        BoardSpot kingLoc = null;

        if (this instanceof King) {

            kingLoc = dest;
        }
        else {

            kingLoc = getPlayer().getKing().getLocation();
        }

        if (getPlayer().getKing().inCheck(kingLoc)) return false;

        return true;
    }

    public boolean clearPathTo(BoardSpot dest) {

        if (location.getX() == dest.getX() && location.getY() != dest.getY()) {

            // plus/minus 1 because we don't check the starting or end square
            int curr = Math.min(location.getY(), dest.getY()) + 1;
            int end = Math.max(location.getY(), dest.getY()) - 1;

            while (curr <= end) {

                if (board[curr++][location.getX()].getPiece() != null) return false;
            }
            return true;
        }

        else if (location.getX() != dest.getX() && location.getY() == dest.getY()) {

            int curr = Math.min(location.getX(), dest.getX()) + 1;
            int end = Math.max(location.getX(), dest.getX()) - 1;

            while (curr <= end) {

                if (board[location.getY()][curr++].getPiece() != null) return false;
            }
            return true;
        }

        else if (Math.abs(location.getX() - dest.getX()) == Math.abs(location.getY() - dest.getY())) {

            int dx = (location.getX() < dest.getX()) ? 1 : -1;
            int dy = (location.getY() < dest.getY()) ? 1 : -1;
            int currX  = location.getX() + dx;
            int currY = location.getY() + dy;

            while ((dx == 1 && currX < dest.getX()) || (dx == -1 && currX > dest.getX())){
                if (board[currY][currX].getPiece() != null){

                    return false;
                }
                currX += dx;
                currY += dy;
            }
            return true;
        }

        return false;
    }

    public BoardSpot getLocation() { return location; }

    public void setLocation(BoardSpot location) { this.location = location; }

    public Player getPlayer() { return player; }

    public void setPlayer(Player player) { this.player = player; }

    public BoardSpot[][] getBoard() { return board; }

    public void incrementMoves() { moves++; }

    public void decrementMoves() { moves--; }

    public int numberOfMoves() { return moves; }

    public void setNumberOfMoves(int moves) { this.moves = moves; }

    public void setBoard(BoardSpot[][] board) { this.board = board; }

    public String colorString() {

        return getPlayer().getColor() == PlayerColor.WHITE ? "white" : "black";
    }

    public String toString() {
        return colorString()+getPieceName();
    }

}
