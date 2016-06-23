package com.example.jin_ho.mp3_project_nulltime;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;


import org.w3c.dom.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * Created by Jin-Ho on 2016-06-15.
 */
public class getURLDataAsynkTask extends AsyncTask<String, String, List<Element>> {
    private final int[] colors = new int[]{0xFF28E0C7, 0xFF368ACA, 0xFF804BCE, 0xFFC75ADF, 0xFFE25979,
            0xFFD34ECA, 0xFFD34ECA, 0xFFF9F650, 0xFFEC8C44, 0xFFE04222, 0xFF00FF00};
    private URLScheduaData[][] urlSchedualDatas;
    private TextView[][] table_po;
    private TextView[] table_time;
    private boolean sixNumberCheck = true;
    private Context context;
    public getURLDataAsynkTask(TextView[][] table_po, TextView[] table_time,Context context) {
        this.table_po = table_po;
        this.table_time = table_time;
        this.context = context;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        urlSchedualDatas = new URLScheduaData[5][27];
    }

    @Override
    protected List<Element> doInBackground(String... params) {

        Source source;
        ArrayList<String> array = new ArrayList<>();
        try {
            URL url = new URL(params[0]);
            source = new Source(url);  // 쓰레드를 사용 안하면 여기에서 예외 발생함 그 이유는 아래에서 설명
            Element element = null;

            List<Element> list = source.getAllElements(HTMLElementName.TR); // TD 태그의 엘리먼트 가져옴
            return list;
        } catch (Exception e) {
            return null;
        }

    }

    private boolean InsertTable(int column, String time, int rowspan, String classData, boolean sizeSix) {
        int row = positionTable_Time(time);
        final URLScheduaData urlSchedualData = urlSchedualData(classData);

        if (sizeSix) {
            int randomColorNumber = (int) (Math.random() * 11);

            for (int position = row; position < (row + rowspan); position++) {
                table_po[column][position].setBackgroundColor(colors[randomColorNumber]);
                table_po[column][position].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new Show_view_dialog(context,urlSchedualData.getClassName(),urlSchedualData.getClassPoint(),urlSchedualData.getClassProfessor()).show();
                    }
                });
                urlSchedualDatas[column][position] = urlSchedualData;
            }

        } else {

        }
        return true;
    }

    private URLScheduaData urlSchedualData(String classData) {
        StringTokenizer stringTokenizer = new StringTokenizer(classData, " ");
        URLScheduaData urlSchedualData = new URLScheduaData();

        if (stringTokenizer.countTokens() == 3) {
            urlSchedualData.setClassName(stringTokenizer.nextToken());
            urlSchedualData.setClassProfessor(stringTokenizer.nextToken());
            urlSchedualData.setClassPoint(stringTokenizer.nextToken());
        } else {
            urlSchedualData.setClassName(stringTokenizer.nextToken() + stringTokenizer.nextToken());
            urlSchedualData.setClassProfessor(stringTokenizer.nextToken());
            urlSchedualData.setClassPoint(stringTokenizer.nextToken());
        }
        return urlSchedualData;
    }

    private boolean checkTableBoolean(int row, int column) {
        if (urlSchedualDatas[column][row] != null)
            return true;
        else
            return false;

    }


    private int positionTable_Time(String time) {
        for (int position = 0; position < 27; position++)
            if (time.equals(table_time[position].getText().toString()))
                return position;

        return 0;

    }

    @Override
    protected void onPostExecute(List<Element> list) {
        super.onPostExecute(list);
        Element element = null;
        for (int i = 1; i < list.size(); i++) {
            element = list.get(i);
            List<Element> thList = element.getAllElements(HTMLElementName.TH);
            String thListLocation = thList.get(0).getTextExtractor().toString(); //time
            List<Element> tdList = element.getAllElements(HTMLElementName.TD);
            if (tdList.size() == 6) {
                for (int position = 0; position < tdList.size(); position++) {
                    String tdListRowSpan = tdList.get(position).getAttributeValue("rowspan");
                    if (tdListRowSpan == null) {
                        continue;
                    }
                    int tdListRowSpanIntger = Integer.parseInt(tdListRowSpan); //rowspan 수
                    String tdListText = tdList.get(position).getContent().getTextExtractor().toString(); //class 내용
                    InsertTable(position, thListLocation, tdListRowSpanIntger, tdListText, true);
                }
            } else {
                int position = 0, column = 0;
                int tdListLength = tdList.size();
                int row = positionTable_Time(thListLocation);
                while (position < tdListLength) {
                    if (checkTableBoolean(row, column)) {
                        column++;
                        if (column == 5)
                            break;
                        continue;
                    } else {
                        String tdListRowSpan = tdList.get(position).getAttributeValue("rowspan");
                        if (tdListRowSpan == null) {
                            column++;
                            if (column == 5)
                                break;
                            position++;
                            continue;
                        }
                        int tdListRowSpanIntger = Integer.parseInt(tdListRowSpan);
                        String tdListText = tdList.get(position).getContent().getTextExtractor().toString();
                        InsertTable(column, thListLocation, tdListRowSpanIntger, tdListText, true);
                        position++;
                        column++;
                        if (column == 5)
                            break;
                    }

                }


            }
        }
    }


}
