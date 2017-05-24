package com.example.nayomishah.nihalchess;

/**
 * Created by nayomishah on 5/24/17.
 */
import android.graphics.*;
public class King extends ChessPiece
{
    private int pict;
    public King(int pict)
    {
        this.pict = pict;
    }
    public int setColor(int p)
    {
        pict = p;
        return pict;
    }


}
