package com.example.shoescorp.pesanActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoescorp.R;
import com.squareup.picasso.Picasso;

public class pesanSneakers extends AppCompatActivity {

    TextView name, price;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_sneakers);

        name = this.findViewById(R.id.nama_sepatu);
        price = this.findViewById(R.id.harga_sepatu);
        image = this.findViewById(R.id.image_sepatu);

        Bundle bundle = getIntent().getExtras();
        String urlGambar = bundle.getString("image");
        String getNama = bundle.getString("Name");
        String getHarga = bundle.getString("Price");

        name.setText(getNama);
        price.setText(getHarga);
        Picasso.with(this)
                .load(urlGambar)
                .into(image);
    }
}
