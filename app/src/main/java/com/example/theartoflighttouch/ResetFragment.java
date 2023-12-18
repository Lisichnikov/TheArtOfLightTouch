package com.example.theartoflighttouch;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

public class ResetFragment extends Fragment {
    View view;
    public interface OnResetSelectedListener{
        void onClickButtonReset();
    }
    private OnResetSelectedListener resetSelectedListener;

    @Override
    public void onAttach(Context context) {
        Log.d("TAG", "onAttach: ");
        super.onAttach(context);
        try {
            resetSelectedListener = (OnResetSelectedListener) context;
        } catch (ClassCastException e) {}
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        ImageButton reset_button = getView().findViewById(R.id.reset_button);
//        reset_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                resetSelectedListener.onClickButtonReset();
//            }
//        });
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reset, container, false);
        ImageButton reset_button = view.findViewById(R.id.reset_button);
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetSelectedListener.onClickButtonReset();
            }
        });

        return view;
    }
}