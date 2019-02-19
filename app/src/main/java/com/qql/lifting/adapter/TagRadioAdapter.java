package com.qql.lifting.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.qql.lifting.R;
import com.qql.lifting.mvp.module.entity.Dic;

import java.util.List;

// TODO: 2019/2/18 以这个为一单位，动态增加类目选项 
public class TagRadioAdapter extends RecyclerView.Adapter<TagRadioAdapter.Holder> {
    private Context context;
    private List<Dic> dics;
    private int index;

    public TagRadioAdapter(Context context, List<Dic> dics) {
        this.context = context;
        this.dics = dics;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_item_radio, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        final int position = i;
        holder.radio.setText(dics.get(i).getDisplayName());
        holder.radio.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                index = position;
                specialUpdate();
            }
        });
        if(index==position){
            holder.radio.setChecked(true);
        } else {
            holder.radio.setChecked(false);
        }
    }

    private void specialUpdate() {
        Handler handler = new Handler(Looper.getMainLooper());
        final Runnable r = this::notifyDataSetChanged;
        handler.post(r);
    }

    @Override
    public int getItemCount() {
        return dics.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        RadioButton radio;
        public Holder(@NonNull View itemView) {
            super(itemView);
            radio = (RadioButton) itemView;
        }
    }
}
