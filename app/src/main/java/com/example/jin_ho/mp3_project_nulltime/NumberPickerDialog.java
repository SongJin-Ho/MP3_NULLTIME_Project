package com.example.jin_ho.mp3_project_nulltime;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.NumberPicker;

/**
 * Created by Jin-Ho on 2016-06-15.
 */
public class NumberPickerDialog extends Dialog implements View.OnClickListener{

    private NumberPicker numberPicker;
    private Button button,Okbtn,CancelBtn;
    private Context context;
    private NumberPickerDialog numberPickerDialog;
    public NumberPickerDialog(Context context,Button button) {
        super(context);
        this.context = context;
        this.button = button;
    }

    public void setNuberPickerDialog(NumberPickerDialog numberPickerDialog)
    {
        this.numberPickerDialog = numberPickerDialog;
        this.numberPickerDialog.setCancelable(false);
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams windowLayout = new WindowManager.LayoutParams();
        windowLayout.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        windowLayout.dimAmount = 0.8f;
        getWindow().setAttributes(windowLayout);

        setContentView(R.layout.numberpicker_dialog);
        numberPicker = (NumberPicker) findViewById(R.id.numberpicker);
        numberPicker.setMaxValue(16);
        numberPicker.setMinValue(0);
        numberPicker.setValue(8);


        Okbtn = (Button) findViewById(R.id.numberpicker_okBtn);
        CancelBtn = (Button)findViewById(R.id.numberpicker_CancelBtn);

        Okbtn.setOnClickListener(this);
        CancelBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.numberpicker_okBtn:
                String msg = numberPicker.getValue() + "";
                button.setText(msg);
                numberPickerDialog.dismiss();
                break;
            case R.id.numberpicker_CancelBtn:
                numberPickerDialog.dismiss();
                break;
        }
    }
}
