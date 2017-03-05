package com.example.n9262.json;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn5)
    Button btn5;
    @BindView(R.id.btn6)
    Button btn6;
    public TextView textView;
    public String baseUrl = "http://www.baidu.com";
    public String str = "";
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //  返回的字符串 直接是个数组
            // [{"devid":"1234567800","latitude":"29.4963","longitude":"116.189","postime":"2014-06-10 12:13:00"},
            // {"devid":"1234567832","latitude":"29.4943","longitude":"1161.129","postime":"2014-06-11 12:13:00"}]
            if (msg.what == 1) {
                Log.v("zms", "use httpclient,return json");
                try {
                    JSONArray jsonArray = new JSONArray(String.valueOf(msg.obj));
                    textView.setText(" ");
                    str = "httpclient";
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.opt(i);
                        str = str + "第" + i + "个,devid:" + jsonObject.getString("devid") + "维度" + jsonObject.getString("latitude") + "\n";
                        textView.setText(str);
                        Log.v("zms", str);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (msg.what == 2) {
                Log.v("zms", "使用httpurlconnection返回的json");
                try {
                    JSONArray jsonArray = new JSONArray(String.valueOf(msg.obj));
                    textView.setText(" ");
                    str = "urlconnet:";
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.opt(i);
                        str = str + "第" + i + "个,devid:" + jsonObject.getString("devid") + "维度:" + jsonObject.getString("latitude") + "\n";
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
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6})
    public void onClick(View view) {
        switch (view.getId()) {
            //直接一个对象
            case R.id.btn1:
                textView.setText(" ");
                str="";
                // {"username":"zms",age:23,"addr","from china"};
                String json="{\"username\":\"zms\",\"age\":43,\"addr\":\"江西省高安市村前镇\"}";
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(json);
                    str="名字:"+jsonObject.getString("username")+"年薪:"+jsonObject.getString("age")+jsonObject.getString("addr")+"\n";
                    textView.setText(str);
                    Log.v("zms", str);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            //对象里有数组
            case R.id.btn2:
                textView.setText("");
                str="";

                try {
                    //{"username":"zms",age:11,"jicheng":[{"zhengshu":"PMP","date":"2011年"},{"zhengshu":"信息系统项目管理师","date","2012年"}],"addr":"江西"}
                    // 这种也属于 对象里有数组   {"json":[{"username":"zms","date":"2011年"},{"username":"ivy","date","2012年"}]}
                    json = "{\"username\":\"张木生\",age:11,\"jicheng\":[{\"zhengshu\":\"PMP\",\"date\":\"2011年\"},{\"zhengshu\":\"信息系统项目管理师\",\"date\":\"2012年\"}],\"addr\":\"江西\"}";

                    jsonObject = new JSONObject(json);
                    str="名字:"+jsonObject.getString("username");
                    str=str+"工龄:"+jsonObject.getString("age")+"证书:";
                    JSONArray jsonArray = jsonObject.getJSONArray("jicheng");
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObjectSon= (JSONObject)jsonArray.opt(i);
                        str=str+jsonObjectSon.getString("zhengshu")+"年份："+jsonObjectSon.getString("date");
                    }
                    str=str+"籍贯"+jsonObject.getString("addr");
                    textView.setText(str);
                    Log.v("zms", str);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                break;
            //直接一个数组
            case R.id.btn3:
                Log.v("zms","响应4");
                textView.setText("");
                str="";
                //  返回的字符串 直接是个数组
                // [{"devid":"1234567800","latitude":"29.4963","longitude":"116.189"},{"devid":"1234567832","latitude":"29.4943","longitude":"1161.129"}]
                json="[{\"devid\":\"1234567800\",\"latitude\":\"29.4963\",\"longitude\":\"116.189\"},{\"devid\":\"1234567832\",\"latitude\":\"29.4943\",\"longitude\":\"1161.129\"}]";
                try {
                    JSONArray jsonArray = new JSONArray(json);
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject2 = (JSONObject)jsonArray.opt(i);
                        str=str+"第"+i+"个,devid:"+jsonObject2.getString("devid")+"维度:"+jsonObject2.getString("latitude")+"经度:"+jsonObject2.getString("longitude")+"\n";

                    }
                    textView.setText(str);
                    Log.v("zms", str);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            //数组里有数组
            case R.id.btn4:
                textView.setText("");
                str="";
                //  返回的字符串 直接是个数组
               /*   [
                  {"devid":"1234567800","gps":[{"time":"2014-11-12","latitude":"29.4963","longitude":"116.189"},{"time":"2014-11-12","latitude":"29.4963","longitude":"116.189" }],"devname":"赣01"},
                   {"devid":"1234567800","gps":[{"time":"2014-11-12","latitude":"29.4963","longitude":"116.189"},{"time":"2014-11-12","latitude":"29.4963","longitude":"116.189" }],"devname":"赣92"},
                   {"devid":"1234567800","gps":[{"time":"2014-11-12","latitude":"29.4963","longitude":"116.189"},{"time":"2014-11-12","latitude":"29.4963","longitude":"116.189" }],"devname":"赣43"},
                   ], */
                json="["+
                        "{\"devid\":\"1234567800\",\"gps\":[{\"time\":\"2014-11-12\",\"latitude\":\"29.4963\",\"longitude\":\"116.189\"},{\"time\":\"2014-11-12\",\"latitude\":\"29.4963\",\"longitude\":\"116.189\" }],\"devname\":\"赣01\"},"+
                        " {\"devid\":\"1234567800\",\"gps\":[{\"time\":\"2014-11-12\",\"latitude\":\"29.4963\",\"longitude\":\"116.189\"},{\"time\":\"2014-11-12\",\"latitude\":\"29.4963\",\"longitude\":\"116.189\" }],\"devname\":\"赣92\"},"+
                        " {\"devid\":\"1234567800\",\"gps\":[{\"time\":\"2014-11-12\",\"latitude\":\"29.4963\",\"longitude\":\"116.189\"},{\"time\":\"2014-11-12\",\"latitude\":\"29.4963\",\"longitude\":\"116.189\" }],\"devname\":\"赣43\"}"+
                        "]";
                try {
                    JSONArray jsonArray = new JSONArray(json);
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        String tpstr="";
                        JSONObject jsonObject5 = (JSONObject)jsonArray.opt(i);
                        str=str+"第"+i+"条船，设备号devid:"+jsonObject5.getString("devid");
                        JSONArray jsonArray5 = jsonObject5.getJSONArray("gps");
                        for (int j=0;j<jsonArray5.length();j++)
                        {
                            JSONObject jsonOb5Son=(JSONObject)jsonArray5.opt(j);
                            tpstr="采集时间:"+jsonOb5Son.getString("time")+"维度"+jsonOb5Son.getString("latitude")+"经度:"+jsonOb5Son.getString("longitude");

                        }
                        str=str+tpstr;


                    }
                    textView.setText(str);
                    Log.v("zms", str);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            //httpclient访问网络返回json
            case R.id.btn5:
                textView.setText("");
               /*从安卓3.0以后，就不允许在主线程中直接访问网络，必须在子线程中访问
               访问后要修改主线程的UI，需要使用handler通信*/
                accessThread mythread=new accessThread();
                mythread.start();
                break;
            //httpurlconnection访问网络返回json
            case R.id.btn6:
                textView.setText("");
               /*从安卓3.0以后，就不允许在主线程中直接访问网络，必须在子线程中访问
               访问后要修改主线程的UI，需要使用handler通信*/
                accessThread2 mythread2=new accessThread2();
                mythread2.start();
                break;
        }
    }

    private class accessThread extends Thread{
        @Override
        public void run() {
            Log.v("zms","线程accessThread开始");
            Message msg1=handler.obtainMessage();
            msg1.what=1;
            msg1.obj=util.getHttpJsonByhttpclient(baseUrl);
            handler.sendMessage(msg1);
            super.run();
        }
    }

    private class accessThread2 extends Thread{
        @Override
        public void run() {
            Message msg2=handler.obtainMessage();
            msg2.what=2;
            msg2.obj=util.getHttpJsonByurlconnection(baseUrl);
            handler.sendMessage(msg2);
            super.run();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
}
