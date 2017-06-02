package com.example.nayomishah.nihalchess;

/**
 * Created by nayomishah on 6/1/17.
 */

public class Move {

    public static enum MoveType {
        NORMAL, CASTLE, ENPASSANT
    }

    private ChessPiece chessPiece;
    private ChessPiece capture;
    private BoardSpot start;
    private BoardSpot destination;
    private MoveType type;
    private int s, d;

    public Move(ChessPiece chessPiece, ChessPiece capture, BoardSpot start, BoardSpot destination) {
        this.chessPiece = chessPiece;
        this.capture = capture;
        this.start = start;
        this.destination = destination;
    }

    public MoveType getType() { return type; }

    public void setType(MoveType type) { this.type = type; }

    public BoardSpot getDestination() { return destination; }

    public BoardSpot getSource() { return start;}

    public void setCapture(ChessPiece piece) { this.capture = piece; }

    public ChessPiece getCapture() { return capture; }

    public ChessPiece getPiece() { return chessPiece; }

    public void setSourcePosition(int s) { this.s = s;}

    public int getSourcePosition() { return s; }

    public void setDestPosition(int d) { this.d = d; }

    public int getDestPosition() { return d; }

}


