package com.example.eddielee.copywyxwelm.util;

import android.util.Log;

import com.example.eddielee.copywyxwelm.PoJo.Menu;
import com.example.eddielee.copywyxwelm.PoJo.StepsBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Connection implements web {

    private static final String html = "http://apis.juhe.cn/cook/query?";
    private static final String key = "key=57e9c578a72bcd1d3c1acffbd5d3650d&menu=";

    @Override
    public List<Menu> connectionTo(String name) throws Exception{
        URL url = new URL("http://apis.juhe.cn/cook/query?rn=5&key=57e9c578a72bcd1d3c1acffbd5d3650d&menu="+name

        );
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream is = connection.getInputStream();
        BufferedReader bf = new BufferedReader(new InputStreamReader(is));
        String line = null;
        StringBuffer sb = new StringBuffer();

        while ((line = bf.readLine())!=null){
            sb.append(line);
        }

        Log.i("fff",sb.toString());
        List<Menu> result = result(sb.toString());

        return result;
    }

    private List<Menu> result(String s) {
        List<Menu> list = new ArrayList<>();
        try {
            JSONObject firstObject = new JSONObject(s);
            int resultcode = firstObject.getInt("resultcode");
            if (resultcode != 200) {
                JSONArray result = firstObject.getJSONObject("result").getJSONArray("data");
                int length = result.length();
                for (int i = 0; i < length; i++) {
                    JSONObject o = result.getJSONObject(i);
                    int id = o.getInt("id");
                    String title = o.getString("title");
                    String ingredients = o.getString("ingredients");
                    String burden = o.getString("burden");
                    String albums = o.getJSONArray("albums").getString(0);

                    List<StepsBean> stepList = new ArrayList<>();
                    JSONArray steps = o.getJSONArray("steps");
                    int stepSize = steps.length();
                    for (int x = 0; x < stepSize; x++) {
                        String img = steps.getJSONObject(i).getString("img");
                        String step = steps.getJSONObject(i).getString("img");
                        StepsBean bean = new StepsBean(img, step);
                        stepList.add(bean);
                    }

                    Menu menu = new Menu(id, title, ingredients, burden, albums, stepList);
                    list.add(menu);
                }
            }
        } catch(JSONException e){
            e.printStackTrace();
        }
        return list;
    }
}
