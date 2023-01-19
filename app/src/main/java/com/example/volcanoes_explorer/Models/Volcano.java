package com.example.volcanoes_explorer.Models;

public class Volcano {
    private String nama, geolokasi, tinggi_meter, estimasi_letusan_terakhir, pict_url;

    public String getNama() {
        return nama;
    }

    public String getGeoLokasi() {
        return "Geolokasi: "+ geolokasi;
    }

    public String getTinggiMeter() { return tinggi_meter; }

    public String getEstimasiLetusanTerakhir() {
        return "Estimasi Letusan Terakhir: " + estimasi_letusan_terakhir;
    }

    public String getPictUrl() {
        return pict_url;
    }

    public void setPictUrl(String pict_url) {
        this.pict_url = pict_url;
    }
}