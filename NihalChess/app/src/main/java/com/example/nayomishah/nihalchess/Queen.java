package com.example.nayomishah.nihalchess;

/**
 * Created by nayomishah on 5/24/17.
 */
import android.graphics.*;
public class Queen
{
    private Picture pict;
    public Queen(Picture pict)
    {
        this.pict = pict;
    }
    public Picture setColor(Picture p)
    {
        pict = p;
        return pict;
    }
}
