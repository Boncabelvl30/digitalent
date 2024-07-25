package com.example.praktek;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class PilihanDialog extends DialogFragment {
    private Mahasiswa mahasiswa;

    public PilihanDialog(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_pilihan);

        Button btnLihatData = dialog.findViewById(R.id.btnLihatData);
        btnLihatData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailMahasiswaActivity.class);
                intent.putExtra("mahasiswa", mahasiswa);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        Button btnUpdateData = dialog.findViewById(R.id.btnUpdateData);
        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UpdateMahasiswaActivity.class);
                intent.putExtra("mahasiswa", mahasiswa);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        Button btnHapusData = dialog.findViewById(R.id.btnHapusData);
        btnHapusData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implementasi penghapusan data
                dialog.dismiss();
            }
        });

        return dialog;
    }
}
