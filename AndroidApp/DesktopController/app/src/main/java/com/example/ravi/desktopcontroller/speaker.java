package com.example.ravi.desktopcontroller;

import android.app.Activity;
import android.arch.core.executor.TaskExecutor;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.RecognitionService;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

import static android.app.Activity.RESULT_OK;


public class speaker extends Fragment  {
    private TextView txtSpeechInput;
    private ImageButton btnspeak;
    private final int REQ_CODE_SPEECH_INPUT=100;
    public String speechdata="";
    private String connecteddevice;
    private TextView connDevice;
    public String link;
    private Boolean flag=false;
    private Button controlfunctions, keyboardkeys,otherfunction,close;
   LinearLayout linearLayout2;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       flag=false;
        View myfragmentview=inflater.inflate(R.layout.speaker,null);
        final View rules=inflater.inflate(R.layout.rules,null);

       /////////getting the link of connected device

       View linkView=inflater.inflate(R.layout.addlink,null);
       connDevice=linkView.findViewById(R.id.connectedLinkText);
        linearLayout2=myfragmentview.findViewById(R.id.linearlayout2);
        close=rules.findViewById(R.id.close);

       connecteddevice= connDevice.getText().toString();
        if(connecteddevice.length()>0) {
            String links[] = connecteddevice.split("-");
            link=links[1].toString();
            flag=true;
        }
        else {
            Toast.makeText(getContext(),"established the connection",Toast.LENGTH_SHORT).show();
        }
        /////////////////////////////////

        txtSpeechInput=myfragmentview.findViewById(R.id.speaktext);
        btnspeak=myfragmentview.findViewById(R.id.speakbutton);
        controlfunctions=myfragmentview.findViewById(R.id.controlfunctions);
        otherfunction=myfragmentview.findViewById(R.id.otherfunctions);
        keyboardkeys=myfragmentview.findViewById(R.id.tappingkeys);


       btnspeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=true;
                if(flag) {
                    promptSpeechInput();
                }
                else
                {
                    Toast.makeText(getContext(),"established the connection by adding CLIENT PC link", Toast.LENGTH_SHORT).show();

                }
            }
        });



        keyboardkeys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupWindow popupWindow=new PopupWindow(rules, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.showAtLocation(linearLayout2, Gravity.CENTER,0,0);
                popupWindow.setFocusable(true);
                popupWindow.update();
                close.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();


                    }

                });
            }
        });
        otherfunction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupWindow popupWindow=new PopupWindow(rules, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.showAtLocation(linearLayout2, Gravity.CENTER,0,0);
                popupWindow.setFocusable(true);
                popupWindow.update();
                close.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();


                    }

                });
            }
        });
        controlfunctions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupWindow popupWindow=new PopupWindow(rules, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.showAtLocation(linearLayout2, Gravity.CENTER,0,0);
                popupWindow.setFocusable(true);
                popupWindow.update();
                close.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();


                    }

                });
            }
        });











        return myfragmentview;

    }

    private void promptSpeechInput(){
        Intent intent =new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say Something");
        try{
            startActivityForResult(intent,REQ_CODE_SPEECH_INPUT);

        }catch (ActivityNotFoundException a){
            Toast.makeText(getContext(),"speech not supported",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){

            case REQ_CODE_SPEECH_INPUT:{
                if(resultCode==RESULT_OK &&null!=data){

                    ArrayList<String> result= data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    speechdata=result.get(0);
                    txtSpeechInput.setText(speechdata);
                    new SendPostRequest().execute();
                }
                break;
            }
        }
    }


    public class SendPostRequest extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
        }

        protected String doInBackground(String... arg0) {

            try {

                //URL url = new URL("http://"+link+":9000/speak"); // here is your URL path
                URL url = new URL("http://192.168.43.212:9000/speak");


                JSONObject postDataParams = new JSONObject();
                postDataParams.put("data",speechdata);

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