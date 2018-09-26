package com.example.halong.myapplication.adapter;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.halong.myapplication.R;

import java.util.List;

public class MyAdapter<T> extends BaseAdapter {
    private List<T> items;
    public MyAdapter(List<T> items){
        this.items=items;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;
        if (convertView == null) {
            holder=new Holder();
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
            holder.textView=convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        }else {
            holder=(Holder)convertView.getTag();
        }

        holder.textView.setText(items.get(position).toString());
        return convertView;
    }


    public static class Holder{
        public TextView textView;
    }
}
