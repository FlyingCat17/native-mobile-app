package com.example.native_1.Home.Profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.native_1.Navigator.FragmentNavigator;
import com.example.native_1.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UbahNamaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UbahNamaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UbahNamaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UbahNamaFragment newInstance(String param1, String param2) {
        UbahNamaFragment fragment = new UbahNamaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    MaterialButton btnBackEditNama, btnLanjutkan;
    TextInputEditText txtEditNama;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        btnBackEditNama = (MaterialButton) view.findViewById(R.id.btnBackEditNama);
        btnLanjutkan = (MaterialButton) view.findViewById(R.id.btnEditNama);
        txtEditNama = (TextInputEditText) view.findViewById(R.id.txtEditNama1);

        btnLanjutkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = txtEditNama.getText().toString();
                if (nama.equals("")) {
                    Toast.makeText(getActivity(), "Kolom nama harap diisi!", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        ubahNama(nama);
                        Toast.makeText(getActivity(), "Berhasil Ubah Nama", Toast.LENGTH_SHORT).show();
                        FragmentNavigator.pop(getActivity());
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "Gagal Ubah Nama", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        btnBackEditNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentNavigator.pop(getActivity());
            }
        });

        return view;
    }

    public void ubahNama(String nama) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nama", nama);
        editor.commit();
    }
}