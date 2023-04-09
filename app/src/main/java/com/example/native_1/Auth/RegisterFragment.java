package com.example.native_1.Auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.native_1.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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

    private TextInputEditText txtNama, txtEmail, txtPassword, txtConfirmPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        MaterialButton btnBack, btnRegister;
        btnBack = (MaterialButton) view.findViewById(R.id.btnBack);
        txtNama = (TextInputEditText) view.findViewById(R.id.txtNamaLengkap);
        txtEmail = (TextInputEditText) view.findViewById(R.id.txtEmail);
        txtPassword = (TextInputEditText) view.findViewById(R.id.txtPassword);
        txtConfirmPassword = (TextInputEditText) view.findViewById(R.id.txtConfirmPassword);
        btnRegister = (MaterialButton) view.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String nama = txtNama.getText().toString();
                    String email = txtEmail.getText().toString();
                    String password = txtPassword.getText().toString();
                    String confirmPassword = txtConfirmPassword.getText().toString();
                    if (!nama.equals("") || !email.equals("") || !password.equals("") || !confirmPassword.equals("")) {
                        if (password.length() >= 8) {
                            if (password.equals(confirmPassword)) {
                                registerAction(txtNama.getText().toString(),
                                        txtEmail.getText().toString(),
                                        txtPassword.getText().toString(),
                                        txtConfirmPassword.getText().toString());
                            } else {
                                Toast.makeText(getActivity(), "Konfirmasi Password tidak sama!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Password minimal 8 karakter!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Harap isi semua kolom yang disediakan!", Toast.LENGTH_SHORT).show();

                    }
//
                    System.out.println("PCCCC = " + txtNama.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "An error occured!", Toast.LENGTH_SHORT).show();
                }

            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fw = getActivity().getSupportFragmentManager();
                fw.popBackStack();
            }
        });


        // Inflate the layout for this fragment
        return view;
    }


    public void registerAction(String nama, String email, String password, String confirmPassword) {
        try {
            SharedPreferences sharedPref = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("nama", nama);
            editor.putString("email", email);
            editor.putString("password", password);
            editor.putString("isAvailable", "true");
            editor.putString("isLogin", "false");
//            editor.apply();
            editor.commit();
            Toast.makeText(getActivity(), "Berhasil Register", Toast.LENGTH_SHORT).show();

            FragmentManager fw = getActivity().getSupportFragmentManager();
            fw.popBackStack();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "An Error Occured @registerAction()", Toast.LENGTH_SHORT).show();
        }

    }
}