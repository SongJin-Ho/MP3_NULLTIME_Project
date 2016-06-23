package com.example.jin_ho.mp3_project_nulltime;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private final int column = 27;
    private final int row = 5;
    private AddSchedualDialog addSchedualDialog;

    private PassInputDialog passInputDialog;
    private URLinputDialog urLinputDialog;

    private final int[][] table_position = new int[][]{
            {R.id.table_position1, R.id.table_position2, R.id.table_position3, R.id.table_position4, R.id.table_position5, R.id.table_position6, R.id.table_position7, R.id.table_position8, R.id.table_position9, R.id.table_position10, R.id.table_position11, R.id.table_position12, R.id.table_position13, R.id.table_position14, R.id.table_position15, R.id.table_position16, R.id.table_position17, R.id.table_position18, R.id.table_position19, R.id.table_position20, R.id.table_position21, R.id.table_position22, R.id.table_position23, R.id.table_position24, R.id.table_position25, R.id.table_position26, R.id.table_position27},
            {R.id.table_position28, R.id.table_position29, R.id.table_position30, R.id.table_position31, R.id.table_position32, R.id.table_position33, R.id.table_position34, R.id.table_position35, R.id.table_position36, R.id.table_position37, R.id.table_position38, R.id.table_position39, R.id.table_position40, R.id.table_position41, R.id.table_position42, R.id.table_position43, R.id.table_position44, R.id.table_position45, R.id.table_position46, R.id.table_position47, R.id.table_position48, R.id.table_position49, R.id.table_position50, R.id.table_position51, R.id.table_position52, R.id.table_position53, R.id.table_position54},
            {R.id.table_position55, R.id.table_position56, R.id.table_position57, R.id.table_position58, R.id.table_position59, R.id.table_position60, R.id.table_position61, R.id.table_position62, R.id.table_position63, R.id.table_position64, R.id.table_position65, R.id.table_position66, R.id.table_position67, R.id.table_position68, R.id.table_position69, R.id.table_position70, R.id.table_position71, R.id.table_position72, R.id.table_position73, R.id.table_position74, R.id.table_position75, R.id.table_position76, R.id.table_position77, R.id.table_position78, R.id.table_position79, R.id.table_position80, R.id.table_position81},
            {R.id.table_position82, R.id.table_position83, R.id.table_position84, R.id.table_position85, R.id.table_position86, R.id.table_position87, R.id.table_position88, R.id.table_position89, R.id.table_position90, R.id.table_position91, R.id.table_position92, R.id.table_position93, R.id.table_position94, R.id.table_position95, R.id.table_position96, R.id.table_position97, R.id.table_position98, R.id.table_position99, R.id.table_position100, R.id.table_position101, R.id.table_position102, R.id.table_position103, R.id.table_position104, R.id.table_position105, R.id.table_position106, R.id.table_position107, R.id.table_position108},
            {R.id.table_position109, R.id.table_position110, R.id.table_position111, R.id.table_position112, R.id.table_position113, R.id.table_position114, R.id.table_position115, R.id.table_position116, R.id.table_position117, R.id.table_position118, R.id.table_position119, R.id.table_position120, R.id.table_position121, R.id.table_position122, R.id.table_position123, R.id.table_position124, R.id.table_position125, R.id.table_position126, R.id.table_position127, R.id.table_position128, R.id.table_position129, R.id.table_position130, R.id.table_position131, R.id.table_position132, R.id.table_position133, R.id.table_position134, R.id.table_position135}


    };
    private final int[] table_time_position = new int[]{
            R.id.table_time1, R.id.table_time2, R.id.table_time3, R.id.table_time4, R.id.table_time5,
            R.id.table_time6, R.id.table_time7, R.id.table_time8, R.id.table_time9, R.id.table_time10,
            R.id.table_time11, R.id.table_time12, R.id.table_time13, R.id.table_time14, R.id.table_time15,
            R.id.table_time16, R.id.table_time17, R.id.table_time18, R.id.table_time19, R.id.table_time20,
            R.id.table_time21, R.id.table_time22, R.id.table_time23, R.id.table_time24, R.id.table_time25,
            R.id.table_time26, R.id.table_time27 };
    private TextView[][] table_po = new TextView[row][column];

    private TextView[] table_time = new TextView[column];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSchedualDialog = new AddSchedualDialog(MainActivity.this, addSchedualListener);
                addSchedualDialog.show();
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        for (int position = 0; position < row; position++)
            for (int po = 0; po < column; po++)
                table_po[position][po] = (TextView) findViewById(table_position[position][po]);

        for(int po = 0; po < column ; po++)
            table_time[po] = (TextView)findViewById(table_time_position[po]);
    }

    private View.OnClickListener addSchedualListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.URLinputAddBtn:
                    //Toast.makeText(MainActivity.this, "URL 입력", Toast.LENGTH_SHORT).show();
                    addSchedualDialog.dismiss();
                    urLinputDialog = new URLinputDialog(MainActivity.this, table_po,table_time);
                    urLinputDialog.setURLinputDialog(urLinputDialog,MainActivity.this);
                    urLinputDialog.show();
                    break;
                case R.id.passInputAddBtn:
                    //Toast.makeText(MainActivity.this, "수동 입력", Toast.LENGTH_SHORT).show();
                    addSchedualDialog.dismiss();
                    passInputDialog = new PassInputDialog(MainActivity.this, table_po);
                    passInputDialog.setPassInputDialog(passInputDialog);
                    passInputDialog.show();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_myschedual:
                break;
            case R.id.action_publicschedual:
                break;
            default:
                break;
        }
        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
}
