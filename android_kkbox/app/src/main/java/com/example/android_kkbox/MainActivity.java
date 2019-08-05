package com.example.android_kkbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // go to FirstFragment
        changeFragment(new ButtonFragment());
    }
    @Override
    public void onResume() {
        super.onResume();
        // hide navigate bar
        final int flags = View.SYSTEM_UI_LAYOUT_FLAGS
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(flags);
        }
    }

    public void changeFragment(Fragment f) {
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.main_container, f).commit();
    }
}
