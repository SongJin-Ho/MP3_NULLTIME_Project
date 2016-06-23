package com.example.jin_ho.mp3_project_nulltime;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by Jin-Ho on 2016-06-15.
 */
public class Show_view_dialog extends Dialog {

    private String subject, location, professor;

    public Show_view_dialog(Context context,String subejct,String location,String professor) {
        super(context);
        this.subject = subejct;
        this.location = location;
        this.professor = professor;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams windowLayout = new WindowManager.LayoutParams();
        windowLayout.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        windowLayout.dimAmount = 0.8f;
        getWindow().setAttributes(windowLayout);

        setContentView(R.layout.show_view_schedual);
        ((TextView)findViewById(R.id.show_view_subject)).setText(subject);
        ((TextView)findViewById(R.id.show_view_location)).setText(location);
        ((TextView)findViewById(R.id.show_view_professor)).setText(professor);

    }
}
