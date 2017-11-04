package com.ezmusic.tsongling5.ezmusic.QListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ezmusic.tsongling5.ezmusic.R;

import java.util.List;

/**
 * Created by TsongLing5 on 2017/11/2.
 */

public class QListAdapter extends ArrayAdapter<QItem> {

    private int resourceID;

    public QListAdapter(Context context, int textViewResourceID, List<QItem> objects){

        super(context,textViewResourceID,objects);
        resourceID=textViewResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        QItem mQItem=getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view= LayoutInflater.from(getContext()).inflate(resourceID,null);
            viewHolder=new ViewHolder();
            viewHolder.Name=(TextView)view.findViewById(R.id.qName);
            viewHolder.Artist=(TextView)view.findViewById(R.id.qArtist);
            viewHolder.Album=(TextView)view.findViewById(R.id.qAlbum);
        }else{
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }


        viewHolder.Name.setText(mQItem.getName());
        viewHolder.Artist.setText(mQItem.getArtist());
        viewHolder.Album.setText(mQItem.getAlbum());

        return view;

    }


    class ViewHolder{
        TextView Name;
        TextView Artist;
        TextView Album;
    }

}
