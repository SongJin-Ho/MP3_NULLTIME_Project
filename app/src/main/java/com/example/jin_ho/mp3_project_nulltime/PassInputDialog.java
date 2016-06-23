package com.example.jin_ho.mp3_project_nulltime;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Jin-Ho on 2016-06-15.
 */
public class PassInputDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private PassInputDialog passInputDialog;
    private TextView[][] table_po;

    private Button timeStrat, timeEnd, add_OKBtn, add_CancelBtn;
    private EditText add_subject_name, add_location_name, add_professor_name;

    private ToggleButton[] toggleBtn = new ToggleButton[6];
    private NumberPickerDialog numberPicker;

    private  final int[] colors  = new int[]{0xFF28E0C7,0xFF368ACA,0xFF804BCE,0xFFC75ADF,0xFFE25979,
            0xFFD34ECA,0xFFD34ECA,0xFFF9F650,0xFFEC8C44,0xFFE04222,0xFF00FF00};

    public PassInputDialog(Context context, final TextView[][] table_po) {
        super(context);
        this.context = context;
        this.table_po = table_po;
    }

    public void setPassInputDialog(PassInputDialog passInputDialog) {
        this.passInputDialog = passInputDialog;
        this.passInputDialog.setCancelable(false);
    }

    private int[] toggleDays = new int[]{
            R.id.add_monday,
            R.id.add_tuesday,
            R.id.add_wednesday,
            R.id.add_thursday,
            R.id.add_friday,
    };
    int toggleLength = toggleDays.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams windowLayout = new WindowManager.LayoutParams();
        windowLayout.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        windowLayout.dimAmount = 0.8f;
        getWindow().setAttributes(windowLayout);

        setContentView(R.layout.pass_input_dialog);
        add_OKBtn = (Button) findViewById(R.id.add_OKBtn);
        add_CancelBtn = (Button) findViewById(R.id.add_CancelBtn);

        add_CancelBtn.setOnClickListener(this);
        add_OKBtn.setOnClickListener(this);

        timeStrat = (Button) findViewById(R.id.timeStart);
        timeEnd = (Button) findViewById(R.id.timeEnd);

        timeStrat.setOnClickListener(this);
        timeEnd.setOnClickListener(this);


        for (int position = 0; position < toggleLength; position++)
            toggleBtn[position] = (ToggleButton) findViewById(toggleDays[position]);

        add_subject_name = (EditText)findViewById(R.id.add_subject_name);
        add_location_name = (EditText)findViewById(R.id.add_location_name);
        add_professor_name = (EditText)findViewById(R.id.add_professor_name);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_OKBtn:
                int timeStartPosition, timeEndPosition;
                timeStartPosition = Integer.parseInt(timeStrat.getText().toString());
                timeEndPosition = Integer.parseInt(timeEnd.getText().toString())+2;

                for (int position = 0; position < toggleLength; position++) {
                    if (toggleBtn[position].isChecked()) {
                        int randomColorNumber = (int) (Math.random() * 11);
                        for (int po = timeStartPosition; po <= timeEndPosition; po++) {
                            table_po[position][po].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    new Show_view_dialog(context,add_subject_name.getText().toString(),add_location_name.getText().toString(),add_professor_name.getText().toString()).show();
                                }
                            });
                            table_po[position][po].setBackgroundColor(colors[randomColorNumber]);
                        }
                    }
                }
                passInputDialog.dismiss();
                break;
            case R.id.add_CancelBtn:
                passInputDialog.dismiss();
                break;
            case R.id.timeStart:
                numberPicker = new NumberPickerDialog(context, timeStrat);
                numberPicker.setNuberPickerDialog(numberPicker);
                numberPicker.show();
                break;
            case R.id.timeEnd:
                numberPicker = new NumberPickerDialog(context, timeEnd);
                numberPicker.setNuberPickerDialog(numberPicker);
                numberPicker.show();
                break;

        }
    }

}
