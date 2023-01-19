package com.example.volcanoes_explorer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailVolcano extends AppCompatActivity {
    private TextView tv_namaGunung, tv_geoLokasi, tv_tinggiMeter, tv_estimasiLetusanTerakhir;
    private ImageView iv_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_volcano);

        tv_namaGunung = findViewById(R.id.tv_namaGunung);
        tv_geoLokasi = findViewById(R.id.tv_geoLokasi);
        tv_tinggiMeter = findViewById(R.id.tv_tinggiMeter);
        tv_estimasiLetusanTerakhir = findViewById(R.id.tv_estimasiLetusanTerakhir);
        iv_image = findViewById(R.id.iv_image);

        String nama_gunung = getIntent().getStringExtra("nama");
        String gelokasi = getIntent().getStringExtra("geolokasi");
        String tinggi_meter = getIntent().getStringExtra("tinggi_meter");
        String estimasi_letusan_terakhir = getIntent().getStringExtra("estimasi_letusan_terakhir");
        String image = getIntent().getStringExtra("pict_url");

        tv_namaGunung.setText(nama_gunung);
        tv_geoLokasi.setText(gelokasi);
        tv_tinggiMeter.setText(tinggi_meter);
        tv_estimasiLetusanTerakhir.setText(estimasi_letusan_terakhir);
        Glide.with(DetailVolcano.this).load(image).into(iv_image);
    }
}