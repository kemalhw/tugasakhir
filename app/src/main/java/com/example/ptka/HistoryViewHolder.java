package com.example.ptka;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class HistoryViewHolder extends RecyclerView.ViewHolder {

    public TextView tvtgl;
    public TextView tvtime;
    public TextView tvamon;
    public TextView tvKelembapan;
    public TextView tvSuhu;

    public HistoryViewHolder(View itemView) {
        super(itemView);
        tvSuhu = itemView.findViewById(R.id.tv_suhu);
        tvKelembapan = itemView.findViewById(R.id.tv_hum);
        tvamon = itemView.findViewById(R.id.tv_amon);
        tvtgl = itemView.findViewById(R.id.tv_tgl);
        tvtime = itemView.findViewById(R.id.tv_time);


    }

    public void bindToHistory(History history, View.OnClickListener onClickListener){

        tvSuhu.setText(history.suhu);
        tvKelembapan.setText(history.kelembapan);
        tvamon.setText(history.amon);
        tvtgl.setText(history.tanggal);
        tvtime.setText(history.time);
    }
}
