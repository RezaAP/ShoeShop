package com.example.shoescorp.view_holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoescorp.Interface.ItemClickListener;
import com.example.shoescorp.R;

public class PesananViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView tv_nama_sepatu;
    public TextView tvSize;
    private ItemClickListener itemClickListener;

    public PesananViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_nama_sepatu = (TextView)itemView.findViewById(R.id.tv_nama_sepatu);
        tvSize = (TextView)itemView.findViewById(R.id.tvSize);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }
}
