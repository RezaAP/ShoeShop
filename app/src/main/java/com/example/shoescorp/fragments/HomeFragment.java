package com.example.shoescorp.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

//import com.bumptech.glide.Glide;
import com.example.shoescorp.Class.Shoes;
import com.example.shoescorp.Interface.ItemClickListener;
import com.example.shoescorp.R;
import com.example.shoescorp.view_holder.ShoesViewHolder;
//import com.firebase.client.annotations.Nullable;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    View myFragment;
    RecyclerView listCategory;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Shoes, ShoesViewHolder> adapter;
    FirebaseDatabase database;
    DatabaseReference categories;


    public static HomeFragment newInstances() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        categories = database.getReference("sepatu");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.fragment_home,container,false);
        listCategory = myFragment.findViewById(R.id.list_brandsepatu);
        listCategory.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(container.getContext());
        listCategory.setLayoutManager(layoutManager);

//        backBtn();
        loadCategories();

        return myFragment;

    }

    private void loadCategories() {
        adapter = new FirebaseRecyclerAdapter<Shoes, ShoesViewHolder>(
                Shoes.class,
                R.layout.category_layout,
                ShoesViewHolder.class,
                categories
        ) {
            @Override
            protected void populateViewHolder(ShoesViewHolder viewHolder, final Shoes model, int i) {
                viewHolder.category_name.setText(model.getName());
                Picasso.with((getActivity()))
                        .load(model.getImage())
                        .into(viewHolder.category_image);

//                viewHolder.setItemClicklistener(new ItemClicklistener() {
//                    @Override
//                    public void onClick(View view, int position, boolean isLongClick) {
//                        //Toast.makeText(getActivity(), String.format("%s|%s",adapter.getRef(position).getKey(),model.getName()), Toast.LENGTH_SHORT).show();
//                        Intent startGame = new Intent(getActivity(),Start.class);
//                        Common.categoryId = adapter.getRef(position).getKey();
//                        Common.categoryName = model.getName();
//                        startActivity(startGame);
//                    }
//                });
            }
        };
        adapter.notifyDataSetChanged();
        listCategory.setAdapter(adapter);
    }

//    private void backBtn() {
//        ImageButton btnBack = (ImageButton) myFragment.findViewById(R.id.back_menu);
//
//        btnBack.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                getActivity().finish();
//            }
//        });
//    }

}
