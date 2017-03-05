package com.example.n9262.json;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private TextView textView;
    private String baseUrl = "http://www.baidu.com";
    private String str = "";
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //  返回的字符串 直接是个数组
            // [{"devid":"1234567800","latitude":"29.4963","longitude":"116.189","postime":"2014-06-10 12:13:00"},
            // {"devid":"1234567832","latitude":"29.4943","longitude":"1161.129","postime":"2014-06-11 12:13:00"}]
            if(msg.what==1){
                Log.v("zms","use httpclient,return json");
                try {
                    JSONArray jsonArray = new JSONArray(String.valueOf(msg.obj));
                    textView.setText(" ");
                    str = "httpclient";
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = (JSONObject) jsonArray.opt(i);
                        str=str+"第"+i+"个,devid:"+jsonObject.getString("devid")+"维度"+jsonObject.getString("atitude")+"\n";
                        textView.setText(str);
                        Log.v("zms",str);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else if (msg.what==2){
                Log.v("zms","使用httpurlconnection返回的json");
                try {
                    JSONArray jsonArray = new JSONArray(String.valueOf(msg.obj));
                    textView.setText(" ");
                    str="urlconnet:";
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = (JSONObject) jsonArray.opt(i);
                        str=str+"第"+i+"个,devid:"+jsonObject.getString("devid")+"维度:"+jsonObject.getString("latitude")+"\n";
                        textView.setText(str);
                        Log.v("zms", str);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textview);
    }
}
