package com.example.nayomishah.nihalchess;

/**
 * Created by nayomishah on 6/1/17.
 */
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
public class BoardSpotAdapter extends BaseAdapter {

    private Context context;
    private BoardSpot[][] board;

    public BoardSpotAdapter(Context c, BoardSpot[][] board) {
        this.context = c;
        this.board = board;
    }


    @Override
    public int getCount() {
        return 64;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView v;
        if (convertView == null) {

            v = new ImageView(context);
            int size = parent.getWidth()/8;

            v.setLayoutParams(new GridView.LayoutParams(size,size));

            //background brown or light brown depending of the position
            int col = position/8 %2;
            if (col == 0)
            {
                if (position%2 == 0)
                    v.setBackgroundColor(Color.parseColor("#DFAE74"));
                else
                    v.setBackgroundColor(Color.parseColor("#6B4226"));

            }
            else
            {
                if (position%2 == 0)
                    v.setBackgroundColor(Color.parseColor("#6B4226"));
                else
                    v.setBackgroundColor(Color.parseColor("#DFAE74"));
            }

            //load images
            ChessPiece p = board[position/8][position%8].getPiece();

            if( p != null)
                v.setImageResource(context.getResources().getIdentifier(p.toString(), "drawable", context.getPackageName()));
        } else {
            v = (ImageView) convertView;
        }


        return v;
    }
}


