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
 * Use the {@link UbahEmailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UbahEmailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UbahEmailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UbahEmailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UbahEmailFragment newInstance(String param1, String param2) {
        UbahEmailFragment fragment = new UbahEmailFragment();
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

    MaterialButton btnBackEditEmail, btnLanjutkan;
    TextInputEditText txtEmailEdit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ubah_email, container, false);
        btnBackEditEmail = (MaterialButton) view.findViewById(R.id.btnBackEditEmail);
        txtEmailEdit = (TextInputEditText) view.findViewById(R.id.txtEditEmail);
        btnLanjutkan = (MaterialButton) view.findViewById(R.id.btnEditemail);

        btnLanjutkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmailEdit.getText().toString();
                if (email.equals("")) {
                    Toast.makeText(getActivity(), "Kolom email harap diisi!", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        ubahEmail(email);
                        Toast.makeText(getActivity(), "Berhasil Ubah Email!", Toast.LENGTH_SHORT).show();
                        FragmentNavigator.pop(getActivity());
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "Gagal Ubah Email!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnBackEditEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentNavigator.pop(getActivity());
            }
        });
        return view;
    }

    public void ubahEmail(String email) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.commit();
    }
}