package com.king.circleprogressview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.king.view.circleprogressview.CircleProgressView;

public class MainActivity extends AppCompatActivity {

    private CircleProgressView cpv;

    private CheckBox cbTick;
    private CheckBox cbTurn;

    private int[] mShaderColors = new int[]{0xFF4FEAAC,0xFFA8DD51,0xFFE8D30F,0xFFA8DD51,0xFF4FEAAC};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cpv = findViewById(R.id.cpv);
        cbTick = findViewById(R.id.cbTick);
        cbTurn = findViewById(R.id.cbTurn);
        cbTick.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cpv.setShowTick(isChecked);
            }
        });

        cbTurn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cpv.setTurn(isChecked);
            }
        });


    }


    private void clickBtn1(){
        cpv.setProgressColor(mShaderColors);
        cpv.showAnimation(100,3000);

    }

    private void clickBtn2(){
        cpv.setProgressColor(mShaderColors);
        cpv.showAnimation(100,0,3000);

    }

    private void clickBtn3(){
        cpv.setProgressColor(0xFF4FEAAC);
        cpv.showAnimation(100,3000);

    }

    private void clickBtn4(){
        cpv.setProgressColor(0xFF4FEAAC);
        cpv.showAnimation(100,3000);

    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn1:
                clickBtn1();
                break;
            case R.id.btn2:
                clickBtn2();
                break;
            case R.id.btn3:
                clickBtn3();
                break;
            case R.id.btn4:
                clickBtn4();
                break;
        }
    }
}
