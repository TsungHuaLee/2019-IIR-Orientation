package com.example.android_kkbox;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class ButtonFragment extends Fragment {
    // UI components
    private Button like_btn, unlike_btn;
    RetrofitRequest req;

    public ButtonFragment() {
        // Required empty public constructor
    }

    public static ButtonFragment newInstance() {
        ButtonFragment fragment = new ButtonFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.button_fragment, container, false);

        // Initialize UI button
        like_btn = view.findViewById(R.id.like_btn);
        unlike_btn = view.findViewById(R.id.unlike_btn);

        like_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
//                req = RetrofitRequest.getInstance();
//                req.Request_LikeInfo("like");
                Log.d("click like button", "Click like button");
                Bundle bundle = new Bundle();
                bundle.putString("preference", "like");

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_container, ContentFragment.newInstance(bundle)).commit();
            }
        });

        unlike_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                req = RetrofitRequest.getInstance();
//                req.Request_LikeInfo("unlike");

                Log.d("click unlike button", "Click unlike button");
                Bundle bundle = new Bundle();
                bundle.putString("preference", "unlike");

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_container, ContentFragment.newInstance(bundle)).commit();

            }
        });

        return view;
    }

}
