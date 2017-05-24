package com.example.nayomishah.nihalchess;

/**
 * Created by nayomishah on 5/24/17.
 */
import android.graphics.*;
public class Queen extends ChessPiece
{
    private int pict;
    public Queen(int pict)
    {
        this.pict = pict;
    }
    public int setColor(int p)
    {
        pict = p;
        return pict;
    }
}
