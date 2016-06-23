package com.example.jin_ho.mp3_nulltime_project;

import android.os.AsyncTask;
import android.util.Log;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jin-Ho on 2016-04-17.
 */
public class getDataThread extends AsyncTask<String,String, ArrayList<String>> {


    @Override
    protected ArrayList<String> doInBackground(String... params) {
        Source source;
        String get_data = "";
        ArrayList<String> array = new ArrayList<>();
        try{
            URL url = new URL(params[0]);
            source = new Source(url);  // 쓰레드를 사용 안하면 여기에서 예외 발생함 그 이유는 아래에서 설명
            Element element = null;

            List<Element> list = source.getAllElements(HTMLElementName.TD); // TD 태그의 엘리먼트 가져옴
            for(int i = 0; i < list.size(); i++){
                element = list.get(i);
                String rowspan=element.getAttributeValue("rowspan");
                String text=element.getContent().getTextExtractor().toString();
                System.out.println(element);
                System.out.println(rowspan);
                System.out.println(text);
                System.out.println("");
            }
            return array;
        }catch(Exception e){
            return null;
        }


    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(ArrayList<String> strings) {
        for(int i = 0; i < strings.size(); i++){
            Log.i("array data : ", strings.get(i));
        }
        super.onPostExecute(strings);
    }
}
