package com.example.volcanoes_explorer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.volcanoes_explorer.Adapter.VolcanoesAdapter;
import com.example.volcanoes_explorer.Models.Volcano;
import com.example.volcanoes_explorer.Repositories.VolcanoesRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView rvVolcanoes;
    private VolcanoesAdapter volcanoesAdapter;
    private VolcanoesRepository repository;
    private ProgressBar progressBar;
    CharSequence search="";
    private SearchView searchView;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        repository = VolcanoesRepository.getInstance();
        volcanoesAdapter = new VolcanoesAdapter(volcano -> {
            Intent intent = new Intent(HomeActivity.this, DetailVolcano.class);
            intent.putExtra("nama", volcano.getNama());
            intent.putExtra("geolokasi", volcano.getGeoLokasi());
            intent.putExtra("tinggi_meter", volcano.getTinggiMeter());
            intent.putExtra("estimasi_letusan_terakhir", volcano.getEstimasiLetusanTerakhir());
            intent.putExtra("pict_url", volcano.getPictUrl());
            startActivity(intent);
        });

        searchView = findViewById(R.id.searchView);searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                volcanoesAdapter.getFilter().filter(newText);
                search=newText;
                return true;
            }
        });
        rvVolcanoes = findViewById(R.id.rv_volcanoes);
        progressBar = findViewById(R.id.progressBar);
        rvVolcanoes.setLayoutManager(new GridLayoutManager(this, 2));
        rvVolcanoes.setAdapter(volcanoesAdapter);

        compositeDisposable.add(repository.getVolcanoes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> {
                    progressBar.setVisibility(View.VISIBLE);
                    rvVolcanoes.setVisibility(View.GONE);
                })
                .doFinally(() -> {
                    progressBar.setVisibility(View.GONE);
                    rvVolcanoes.setVisibility(View.VISIBLE);
                })
                .subscribe(volcanoes -> {
                    int i = 0;
                    for (Volcano volcano : volcanoes) {
                        volcano.setPictUrl("https://picsum.photos/900/1200?random=" + i);
                        i++;
                    }
                    volcanoesAdapter.setVolcanoes(volcanoes);
                },
                throwable -> Toast.makeText(HomeActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show())
        );
    }
}