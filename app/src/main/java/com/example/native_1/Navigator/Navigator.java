package com.example.native_1.Navigator;

import android.content.Context;
import android.content.Intent;

public class Navigator {
    public static void pushReplacement(Context context, Class<?> targetActivityClass) {
        Intent intent = new Intent(context, targetActivityClass);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
