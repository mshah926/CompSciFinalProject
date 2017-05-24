package com.example.nayomishah.nihalchess;

/**
 * Created by nayomishah on 5/24/17.
 */
import android.graphics.*;
public class Bishop extends ChessPiece
{
    private Picture pict;
    public Bishop(Picture pict)
    {
        this.pict = pict;
    }
    public Picture setColor(Picture p)
    {
        pict = p;
        return pict;
    }
}
