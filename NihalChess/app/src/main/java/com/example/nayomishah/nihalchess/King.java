package com.example.nayomishah.nihalchess;

/**
 * Created by nayomishah on 5/24/17.
 */
import android.graphics.*;
public class King
{
    private Picture pict;
    public King(Picture pict)
    {
        this.pict = pict;
    }
    public Picture setColor(Picture p)
    {
        pict = p;
        return pict;
    }


}
