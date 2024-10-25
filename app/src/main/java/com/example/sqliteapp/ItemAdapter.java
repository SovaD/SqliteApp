package com.example.sqliteapp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Person> {
    public ItemAdapter(@NonNull Context context, List<Person> people) {
        super(context, R.layout.activity_item, people);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            //add convertView=
            convertView= LayoutInflater.from(getContext())
                    .inflate(R.layout.activity_item, null);

        final Person person = getItem(position);

        ((TextView) convertView.findViewById(R.id.tName))
                .setText(person.getName());

        ImageView imageView = convertView.findViewById(R.id.imageView);
        // imageView.setImageURI(Uri.parse(person.image));

        try {
            Glide.with(imageView.getContext())
                    .load(Uri.parse(person.getImage()))
                    .placeholder(R.drawable.unknown)
                    .error(R.drawable.unknown)
                    .circleCrop()
                    .into(imageView);
        } catch (Exception e) {
        }
        return convertView;
    }
}
