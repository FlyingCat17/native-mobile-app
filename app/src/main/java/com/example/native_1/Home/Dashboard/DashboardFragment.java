package com.example.native_1.Home.Dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.native_1.Home.Profile.UbahEmailFragment;
import com.example.native_1.Home.Profile.UbahNamaFragment;
import com.example.native_1.MainActivity;
import com.example.native_1.Navigator.FragmentNavigator;
import com.example.native_1.R;
import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
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

    TextView txtNama, txtEmail;
    MaterialButton btnLogout, btnEditNama, btnEditEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        btnLogout = (MaterialButton) view.findViewById(R.id.btnLogout);
        btnEditNama = (MaterialButton) view.findViewById(R.id.btnEditNama);
        btnEditEmail = (MaterialButton) view.findViewById(R.id.btnEditEmail);
        String nama = sharedPreferences.getString("nama", "");
        String email = sharedPreferences.getString("email", "");
//        String a = getResources().getString(Integer.parseInt("nama"), null);
        txtNama = (TextView) view.findViewById(R.id.textViewNama);
        txtEmail = (TextView) view.findViewById(R.id.textViewEmail);

        txtNama.setText(nama);
        txtEmail.setText(email);

        btnEditNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentNavigator.pushReplacement(getActivity(), new UbahNamaFragment(), R.id.fragmentMain);
            }
        });

        btnEditEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentNavigator.pushReplacement(getActivity(), new UbahEmailFragment(), R.id.fragmentMain);
            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("isLogin", "false");
                    editor.commit();
                    Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Gagal logout!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}