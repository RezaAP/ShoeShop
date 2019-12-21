package com.example.shoescorp.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoescorp.Class.Pesan;
import com.example.shoescorp.Class.Shoes;
import com.example.shoescorp.Interface.ItemClickListener;
import com.example.shoescorp.R;
import com.example.shoescorp.pesanActivity.pesanSneakers;
import com.example.shoescorp.view_holder.PesananViewHolder;
import com.example.shoescorp.view_holder.ShoesViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class TranscFragment extends Fragment {
    View myFragment;
    RecyclerView listCategory;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Shoes, ShoesViewHolder> adapter;
    FirebaseDatabase database;
    DatabaseReference categories;

    public static TranscFragment newInstances() {
        TranscFragment transcFragment = new TranscFragment();
        return transcFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        categories = database.getReference("pesan");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.fragment_home,container,false);
        listCategory = myFragment.findViewById(R.id.list_brandsepatu);
        listCategory.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(container.getContext(),3);
        listCategory.setLayoutManager(layoutManager);
        loadCategories();
        return myFragment;
    }

    private void loadCategories() {
        adapter = new FirebaseRecyclerAdapter<Pesan, PesananViewHolder>(
                Pesan.class,
                R.layout.list_pesan,
                PesananViewHolder.class,
                categories
        ){
            @Override
            protected void pesananViewHolder(PesananViewHolder viewHolder, final Pesan model, int i) {
                viewHolder.tv_nama_sepatu.setText(model.getNamePesan());
                viewHolder.tvSize.setText(model.getNamePesan());
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //Toast.makeText(getActivity(), String.format("%s|%s",adapter.getRef(position).getKey(),model.getName()), Toast.LENGTH_SHORT).show();
                        Intent startGame = new Intent(getActivity(), pesanSneakers.class);
                        startGame.putExtra("Name", model.getNamePesan());
                        startGame.putExtra("Size", model.getSize());
                        startActivity(startGame);
                    }
                });
            }
        };
        adapter.notifyDataSetChanged();
        listCategory.setAdapter(adapter);

}}
