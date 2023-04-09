package com.example.native_1.Auth;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.native_1.Home.HomeActivity;
import com.example.native_1.Navigator.Navigator;
import com.example.native_1.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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

    MaterialButton btnLogin, btnRegister;
    TextInputEditText txtEmail, txtPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);


        btnRegister = (MaterialButton) view.findViewById(R.id.btnRegister);
        btnLogin = (MaterialButton) view.findViewById(R.id.btnLogin);
        txtEmail = (TextInputEditText) view.findViewById(R.id.txtEmailLogin);
        txtPassword = (TextInputEditText) view.findViewById(R.id.txtPasswordLogin);



        if (sharedPreferences.contains("isAvailable")) {
            String account = sharedPreferences.getString("isAvailable", null);
            btnRegister.setVisibility(View.GONE);
        } else {
            btnRegister.setVisibility(View.VISIBLE);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                if (!email.equals("") || !password.equals("")) {
                    login(email, password);
                } else {
                    Toast.makeText(getActivity(), "Email atau Password Kosong!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fw = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fw.beginTransaction();
                ft.replace(R.id.frameLayout, new RegisterFragment());
                ft.addToBackStack(null);
                ft.commit();
            }
        });


        // Inflate the layout for this fragment
        return view;
    }


    public void login(String email, String password) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        String getEmail = sharedPreferences.getString("email", null);
        String getPassword = sharedPreferences.getString("password", null);

        if (email.equals(getEmail) && password.equals(getPassword)) {

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("isLogin", "true");
            editor.apply();
            Intent intent = new Intent(getActivity().getApplicationContext(), HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            Toast.makeText(getActivity(), "Berhasil Login", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getActivity(), "Email dan Password Salah!", Toast.LENGTH_SHORT).show();
        }
    }
}