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
    private Square source;
    private Square destination;
    private MoveType type;
    private int s, d;

    public Move(ChessPiece chessPiece, ChessPiece capture, Square source, Square destination) {
        this.chessPiece = chessPiece;
        this.capture = capture;
        this.source = source;
        this.destination = destination;
    }

    public MoveType getType() { return type; }

    public void setType(MoveType type) { this.type = type; }

    public Square getDestination() { return destination; }

    public Square getSource() { return source;}

    public void setCapture(ChessPiece piece) { this.capture = piece; }

    public ChessPiece getCapture() { return capture; }

    public ChessPiece getPiece() { return chessPiece; }

    public void setSourcePosition(int s) { this.s = s;}

    public int getSourcePosition() { return s; }

    public void setDestPosition(int d) { this.d = d; }

    public int getDestPosition() { return d; }

}


