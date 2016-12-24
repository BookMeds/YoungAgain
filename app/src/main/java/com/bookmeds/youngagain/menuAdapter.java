package com.bookmeds.youngagain;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sanjit on 24/12/16.
 * Project: YoungAgain
 */

public class menuAdapter extends ArrayAdapter<MenuItem> {
    public menuAdapter(Context context, List<MenuItem> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View newView = convertView;
        if (newView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            newView = inflater.inflate(R.layout.item, null);
        }
        MenuItem currentItem = getItem(position);

        ImageView menuImage = (ImageView) newView.findViewById(R.id.item_icon);
        menuImage.setImageResource(currentItem.getImage());

        TextView menuName = (TextView) newView.findViewById(R.id.item_name);
        menuName.setText(currentItem.getName());

        TextView menuDetail = (TextView) newView.findViewById(R.id.item_detail);
        menuDetail.setText(currentItem.getDetail());

        return newView;
    }
}
