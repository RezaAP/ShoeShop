package com.example.shoescorp.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.example.shoescorp.Class.Shoes;
import com.example.shoescorp.Interface.ItemClickListener;
import com.example.shoescorp.R;
import com.example.shoescorp.view_holder.ShoesViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    View myFragment;
    RecyclerView listCategory;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<Shoes, ShoesViewHolder> adapter;
    DatabaseReference database;
    DatabaseReference categories;


    public static HomeFragment newInstances() {
        // Required empty public constructor
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance().getReference("sepatu");
//        categories = database.getReference("sepatu");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.fragment_home,container,false);
        listCategory = myFragment.findViewById(R.id.list_brandsepatu);
        listCategory.setHasFixedSize(true);
        listCategory.setLayoutManager(new GridLayoutManager(this.getActivity(), 3));

//        backBtn();
        loadCategories();

        return myFragment;

    }

    private void loadCategories() {
        FirebaseRecyclerOptions<Shoes> options =
                new FirebaseRecyclerOptions.Builder<Shoes>()
                        .setQuery(database, Shoes.class)
                        .build();

        adapter = new FirebaseRecyclerAdapter<Shoes, ShoesViewHolder>(options)
        {
            @NonNull
            @Override
            public ShoesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new ShoesViewHolder(inflater.inflate(R.layout.category_layout, parent, false));
            }

            @Override
            protected void onBindViewHolder(@NonNull ShoesViewHolder viewHolder, int position, @NonNull final Shoes model) {
                viewHolder.category_name.setText(model.getName());
                Glide.with(getActivity())
                        .load(model.getImage())
                        .into(viewHolder.category_image);

//                viewHolder.setItemClickListener(new ItemClickListener() {
//                    @Override
//                    public void onClick(View view, int position, boolean isLongClick) {
//                        Intent startGame = new Intent(view.getContext() , RecipesIsiActivity.class);
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
