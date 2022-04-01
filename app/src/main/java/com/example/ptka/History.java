package com.example.ptka;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class History {
    public String suhu;
    public String kelembapan;
    public String amon;
    public String tanggal;
    public String time;




    public void Perusahaan(String suhu, String kelembapan, String amonia, String tanggal, String time) {
        this.suhu = suhu;
        this.kelembapan = kelembapan;
        this.amon = amonia;
        this.tanggal = tanggal;
        this.time = time;


    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("suhu", suhu);
        result.put("kelembapan", kelembapan);
        result.put("ammonia", amon);
        result.put("tanggal", tanggal);
        result.put("waktu", time);


        return result;
    }
}
