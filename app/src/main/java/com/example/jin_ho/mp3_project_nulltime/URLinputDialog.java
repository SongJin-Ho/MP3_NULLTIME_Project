package com.example.jin_ho.mp3_project_nulltime;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jin-Ho on 2016-06-15.
 */
public class URLinputDialog extends Dialog implements View.OnClickListener {

    private int[] EditTextID = new int[]{R.id.url_input_editText1, R.id.url_input_editText2, R.id.url_input_editText3, R.id.url_input_editText4, R.id.url_input_editText5};
    private int[] ButtonID = new int[]{R.id.url_input_button1, R.id.url_input_button2, R.id.url_input_button3, R.id.url_input_button4, R.id.url_input_button5};
    private int[] LinearID = new int[]{R.id.url_input_Linear1, R.id.url_input_Linear2, R.id.url_input_Linear3, R.id.url_input_Linear4};

    private int number = 0;

    private Button[] buttons = new Button[5];
    private EditText[] editTexts = new EditText[5];

    private Button url_input_OKBtn, url_input_CancelBtn;

    private Context context;
    private URLinputDialog urLinputDialog;

    private TextView[][] table_po;
    private TextView[] table_time;

    public URLinputDialog(Context context,TextView[][] table_po,TextView[] table_time) {

        super(context);
        this.context  = context;
        this.table_po = table_po;
        this.table_time = table_time;
    }

    public void setURLinputDialog(URLinputDialog urLinputDialog,Context context) {
        this.urLinputDialog = urLinputDialog;
        this.urLinputDialog.setCancelable(false);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams windowLayout = new WindowManager.LayoutParams();
        windowLayout.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        windowLayout.dimAmount = 0.8f;
        getWindow().setAttributes(windowLayout);

        setContentView(R.layout.url_input_layout);

        for (int position = 0; position < 5; position++) {
            buttons[position] = (Button) findViewById(ButtonID[position]);
            buttons[position].setOnClickListener(this);
            editTexts[position] = (EditText) findViewById(EditTextID[position]);
        }

        url_input_OKBtn = (Button)findViewById(R.id.url_input_OKBtn);
        url_input_CancelBtn = (Button)findViewById(R.id.url_input_CancelBtn);

        url_input_CancelBtn.setOnClickListener(this);
        url_input_OKBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.url_input_button1:
            case R.id.url_input_button2:
            case R.id.url_input_button3:
            case R.id.url_input_button4:
                ((LinearLayout) findViewById(LinearID[number++])).setVisibility(View.VISIBLE);
                break;
            case R.id.url_input_button5:
                Toast.makeText(context, "최대 5개까지 가능합니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.url_input_OKBtn:


                for(int position = 0; position < number+1 ; position++){
                    String url = editTexts[position].getText().toString();
                    new getURLDataAsynkTask(table_po,table_time,context).execute(url);
                }
                urLinputDialog.dismiss();
                break;
            case R.id.url_input_CancelBtn:
                urLinputDialog.dismiss();
                break;
        }
    }
}
