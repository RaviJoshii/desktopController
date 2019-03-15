package com.example.ravi.desktopcontroller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
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

import javax.net.ssl.HttpsURLConnection;

public class addlink extends Fragment {
    private Button addlink;
    private ListView recentlink;
    private Button sendlink;
    private EditText typeLink,clientname;
    private TextView connectionlink;
    LinearLayout l1;
    private String link,ClientName,Name,datas;
    private Boolean connectionStatus=false;
    private int Count;

    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> arrayList;
    SharedPreferences sharedPreferences;
    private  int flag=0;
    private  String links[];
    private String allrecentlink;
    boolean flag2=true;
    public String s;
    public PopupWindow popupWindow;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myview=inflater.inflate(R.layout.addlink,null);
        final View popup=inflater.inflate(R.layout.popuplink,null);
        sendlink=popup.findViewById(R.id.sendLink);
        typeLink=popup.findViewById(R.id.typelink);
        recentlink=myview.findViewById(R.id.recentlinks);
        clientname=popup.findViewById(R.id.clientname);
        connectionlink=myview.findViewById(R.id.connectedLinkText);

        arrayList=new ArrayList<String>();
        arrayAdapter=new ArrayAdapter<String>(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);

        l1=myview.findViewById(R.id.ll);


        //////////////////////////////////

        allrecentlink=getActivity().getSharedPreferences("mypref",Context.MODE_PRIVATE)
                .getString("recentlinks","");
        if(allrecentlink.length()>=1) {
            links = allrecentlink.split(";");
            for (int i = 0; i < links.length; i++) {
                datas = links[i].toString();
                arrayAdapter.add(datas);
                recentlink.setAdapter(arrayAdapter);
            }
        }


        //////////////////////////////////
        addlink=myview.findViewById(R.id.addLink);
        addlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               popupWindow=new PopupWindow(popup, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.showAtLocation(l1, Gravity.CENTER,0,0);
                popupWindow.setFocusable(true);
                popupWindow.update();
                sendlink.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        link=typeLink.getText().toString();
                        ClientName=clientname.getText().toString();
                        Name=ClientName+" -"+link;
                        if((ClientName.length()<=0)||(link.length()<=0)){
                            Toast.makeText(getContext(),"Name or link is not specified",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            flag = 1;
                            new SendPostRequest().execute();


                        }






                    }
                });



            }
        });

///////////////////////////////////////////////////
        recentlink.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                s= recentlink.getItemAtPosition(i).toString();


                Log.e("clicked",s);

                String links[]=s.split("-");
                link=links[1].toString();

                flag=2;
                new SendPostRequest().execute();


            }
        });




        return myview;
    }






    public class SendPostRequest extends AsyncTask<String, Void, String> {



        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {
            try {
                JSONObject postDataParams = new JSONObject();
                Log.e("params", postDataParams.toString());
                URL url = new URL("http://"+link+":9000/checkconnection");
                //url="http://"+link+"/control";
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                Log.e("cp1","connectionin");
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
////////////////////////////////////////////////////////////
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(
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
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }
        }


        @Override
        protected void onPostExecute(String result) {
            String y="";
            try {
                JSONObject json = new JSONObject(result);
                y=json.getString("error");

                Log.e("value of y",y);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(y.equals("1")) {
                connectionStatus=true;
            }
            else {
                connectionStatus=false;
            }
            if(flag==1){

                    if(connectionStatus) {

                        allrecentlink=allrecentlink+Name+";";
                        connectionlink.setText(Name);
                        typeLink.setText("");
                        clientname.setText("");

                        getActivity().getSharedPreferences("mypref",Context.MODE_PRIVATE).edit()
                                .putString("recentlinks", allrecentlink)
                                .putString("current",Name).commit();
                        arrayAdapter.add(Name);
                        recentlink.setAdapter(arrayAdapter);
                        popupWindow.dismiss();

                        Toast.makeText(getContext(), Name + " is connected", Toast.LENGTH_SHORT).show();
                        connectionStatus=false;
                    }
                    else{

                        Toast.makeText(getContext(), Name + " connection failed", Toast.LENGTH_SHORT).show();
                        connectionStatus=false;
                    }
                flag=0;


                }
                else if(flag==2){
                    if(connectionStatus) {
                        getActivity().getSharedPreferences("mypref",Context.MODE_PRIVATE).edit()
                                .putString("current",s).commit();
                        datas=getActivity().getSharedPreferences("mypref",Context.MODE_PRIVATE)
                                .getString("current","");
                        Log.e("datas",datas);
                        connectionlink.setText(datas);
                        Toast.makeText(getContext(), datas + " is connected", Toast.LENGTH_SHORT).show();
                        connectionStatus=false;
                    }
                    else{

                        Toast.makeText(getContext(), datas + " connection failed", Toast.LENGTH_SHORT).show();
                        connectionStatus=false;
                    }
                flag=0;

                }








        }
    }


    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
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
