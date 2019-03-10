package com.example.ravi.desktopcontroller;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class commonfiles extends Fragment {
    private ListView commonlisttask;
    private EditText command;
    private Button commandButton;
    private String commandText;
    public String link="192.168.43.212:9000";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myview= inflater.inflate(R.layout.commonfiles,null);
        commonlisttask=myview.findViewById(R.id.commonfileslist);
        command=myview.findViewById(R.id.commandtype);
        commandButton=myview.findViewById(R.id.commandEnter);


        commandButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                commandText=command.getText().toString();
                new SendPostRequest().execute();

            }
        });



        return myview;
    }
    public class SendPostRequest extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
        }

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL("http://"+link+"/control"); // here is your URL path

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("code","command");
                postDataParams.put("data",commandText);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 );
                conn.setConnectTimeout(15000 );
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                Log.e("params", postDataParams.toString());

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while ((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                } else {
                    return new String("false : " + responseCode);
                }
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result) {
            String y = "";
            String d = "";
            try {
                JSONObject json1 = new JSONObject(result);

                y = json1.getString("error");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if(y=="0"){
                Toast.makeText(getActivity(), "Invalid Text Input", Toast.LENGTH_SHORT).show();
            }

        }
    }

    ///////////////////////////////////////////////
    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while (itr.hasNext()) {

            String key = itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }
}
