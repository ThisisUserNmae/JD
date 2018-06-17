package com.bwei.jd.mvp.myinfo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;

import com.bwei.jd.R;

public class MyInfoFragment extends Fragment implements View.OnClickListener{

    private ImageView myinfo_img;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.myinfofragment,null);

        initViews();

        return view;
    }

    private void initViews() {


        myinfo_img = view.findViewById(R.id.myinfo_img);

        myinfo_img.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.myinfo_img:

                Intent it = new Intent(getActivity(),LoginActivity.class);

                startActivity(it);

                break;


        }

    }
}
