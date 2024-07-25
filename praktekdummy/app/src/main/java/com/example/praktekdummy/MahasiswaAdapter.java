package com.example.praktekdummy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewHolder> {
    private List<Mahasiswa> mahasiswaList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Mahasiswa mahasiswa);
    }

    public MahasiswaAdapter(List<Mahasiswa> mahasiswaList, OnItemClickListener listener) {
        this.mahasiswaList = mahasiswaList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mahasiswa, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Mahasiswa mahasiswa = mahasiswaList.get(position);
        holder.bind(mahasiswa, listener);
    }

    @Override
    public int getItemCount() {
        return mahasiswaList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView namaTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            namaTextView = itemView.findViewById(R.id.namaTextView);
        }

        public void bind(final Mahasiswa mahasiswa, final OnItemClickListener listener) {
            namaTextView.setText(mahasiswa.getNama());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(mahasiswa);
                }
            });
        }
    }
}
