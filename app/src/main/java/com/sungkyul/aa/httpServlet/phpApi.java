package com.sungkyul.aa.httpServlet;

import android.util.Log;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class phpApi {
    // https://androidexample.com/How_To_Make_HTTP_POST_Request_To_Server_-_Android_Example/index.php?view=article_discription&aid=64&aaid=89 --> EXAMPLE CODE
    static URL url;
    static JSONObject jsonObject;
    static HttpURLConnection conn;
    public static void setAddr(String urlData, String data){
        try {
            // URL setting
            url = new URL(urlData);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static void setData(JSONObject jsonArg){
        jsonObject = jsonArg;
    }

    public static void sendPost(){
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();

            DataOutputStream os = new DataOutputStream(conn.getOutputStream());
            os.writeBytes(URLEncoder.encode(jsonObject.toString(), "UTF-8"));
            os.flush();
            os.close();

            Log.i("STATUS", String.valueOf(conn.getResponseCode()));
            Log.i("MSG", conn.getResponseMessage());

            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
