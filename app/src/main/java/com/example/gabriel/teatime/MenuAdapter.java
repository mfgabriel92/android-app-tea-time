package com.example.gabriel.teatime;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gabriel.teatime.model.Tea;

import java.util.ArrayList;

public class MenuAdapter extends ArrayAdapter<Tea> {
    private Context mContext;
    private int mResource;
    private ArrayList mData;

    MenuAdapter(@NonNull Context context, int resource, @NonNull ArrayList data) {
        super(context, resource, data);
        this.mContext = context;
        this.mResource = resource;
        this.mData = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        Tea currentTea = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(mResource, parent, false);

            holder = new ViewHolder();
            holder.mImageTitle = convertView.findViewById(R.id.tv_tea_name);
            holder.mImage = convertView.findViewById(R.id.img_tea_image);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mImageTitle.setText(currentTea.getName());
        holder.mImage.setImageResource(currentTea.getImageResource());

        return convertView;
    }

    static class ViewHolder {
        TextView mImageTitle;
        ImageView mImage;
    }
}
