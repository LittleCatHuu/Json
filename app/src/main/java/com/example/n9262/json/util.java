package com.example.n9262.json;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.EntityTemplate;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by n9262 on 2017/3/5.
 */

public class util {
    public static String getHttpJsonByhttpclient(String fromurl) {

        try {
            Log.v("zms","使用httget");
            HttpGet httpGet = new HttpGet(fromurl);
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpResponse httpResponse = httpClient.execute(httpGet);
            Log.v("zms","响应码"+httpResponse.getStatusLine().getStatusCode());
            if (httpResponse.getStatusLine().getStatusCode()==200){
                String returnStr = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
                Log.v("zms","返回值"+returnStr);
                return  returnStr;
            }else {
                Log.v("zms","访问网络返回数据失败，错误码:"+httpResponse.getStatusLine().getStatusCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getHttpJsonByurlconnection(String fromurl) {
        try
        {
            Log.v("zms","使用httpurlconnection");
            ByteArrayOutputStream os=new ByteArrayOutputStream();
            byte[] data =new byte[1024];
            int len=0;
            URL url=new URL(fromurl);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            InputStream in=conn.getInputStream();
            while ((len=in.read(data))!=-1)
            {
                os.write(data,0,len);
            }
            in.close();
            return new String(os.toByteArray());
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
