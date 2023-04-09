package com.example.native_1.Navigator;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.Fragment;



public class FragmentNavigator {
    public static void push(FragmentActivity act, Fragment fragment, int containerId) {
        FragmentManager fragmentManager = act.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(containerId, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    public static void pushReplacement(FragmentActivity act, Fragment fragment, int containerId) {
        FragmentManager fragmentManager = act.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(containerId, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    public static void pop(FragmentActivity act){
        FragmentManager fw = act.getSupportFragmentManager();
        fw.popBackStack();
    }
}
