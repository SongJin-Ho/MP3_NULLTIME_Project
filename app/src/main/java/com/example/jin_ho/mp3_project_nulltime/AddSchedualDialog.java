package com.example.jin_ho.mp3_project_nulltime;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.net.URL;

/**
 * Created by Jin-Ho on 2016-06-14.
 */
public class AddSchedualDialog extends Dialog {

    private Button URLInputBtn;
    private Button PassInputBtn;
    private Context context;
    private View.OnClickListener clickListener;

    public AddSchedualDialog(Context context,View.OnClickListener clickListener) {
        super(context);
        this.context  = context;
        this.clickListener = clickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams windowLayout = new WindowManager.LayoutParams();
        windowLayout.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        windowLayout.dimAmount = 0.8f;
        getWindow().setAttributes(windowLayout);

        setContentView(R.layout.add_schedual);
        URLInputBtn = (Button) findViewById(R.id.URLinputAddBtn);
        PassInputBtn = (Button) findViewById(R.id.passInputAddBtn);

        URLInputBtn.setOnClickListener(clickListener);
        PassInputBtn.setOnClickListener(clickListener);
    }


}
