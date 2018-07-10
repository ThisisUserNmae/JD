package com.bwei.jd.mvp.shoppingcar.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwei.jd.R;

public class MyView extends LinearLayout implements View.OnClickListener{

    private View inflate;
    private TextView tv_jian;
    private TextView tv_add;
    private TextView tv_number;

    private int number = 1;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        tv_number.setText(number+"");

    }

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        inflate = inflate(context, R.layout.shopping_expandablelistview_number, this);

        initViews();

    }

    private void initViews() {

        tv_jian = inflate.findViewById(R.id.tv_jian);

        tv_add = inflate.findViewById(R.id.tv_add);

        tv_number = inflate.findViewById(R.id.tv_number);

        tv_jian.setOnClickListener(this);

        tv_add.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.tv_add:

                number++;

                tv_number.setText(number + "");

                if (onNumberClickListener!=null){

                    onNumberClickListener.onNumberClick(number);

                }

                break;

            case R.id.tv_jian:

                if (number > 1){

                    --number;
                    tv_number.setText(number +"");

                    if (onNumberClickListener!=null){

                        onNumberClickListener.onNumberClick(number);

                    }

                }



                break;


        }

    }

    OnNumberClickListener onNumberClickListener;

    public void setOnNumberClickListener(OnNumberClickListener onNumberClickListener) {
        this.onNumberClickListener = onNumberClickListener;
    }

    public interface OnNumberClickListener{

        void onNumberClick(int num);

    }
}
