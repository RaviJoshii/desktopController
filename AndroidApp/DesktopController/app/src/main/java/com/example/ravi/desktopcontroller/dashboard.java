package com.example.ravi.desktopcontroller;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;


public class dashboard extends Fragment {
    public ImageButton ul, uc, ur, ml, mr, ll, lc, lr, upkey, downkey,rightkey,leftkey, leftCLick, rightclick;
    public Button types,enter;
    public EditText typestring;
    public String code = "";
    public String data = "";
    public Handler mhandler = new Handler();
    public String link ;
    private String connecteddevice;
    private TextView connDevice;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        final View myfragmentview = inflater.inflate(R.layout.dashboard, null);
        ul = myfragmentview.findViewById(R.id.ul);
        uc = myfragmentview.findViewById(R.id.uc);
        ur = myfragmentview.findViewById(R.id.ur);
        ml = myfragmentview.findViewById(R.id.ml);
        mr = myfragmentview.findViewById(R.id.mr);
        ll = myfragmentview.findViewById(R.id.ll);
        lc = myfragmentview.findViewById(R.id.lc);
        lr = myfragmentview.findViewById(R.id.lr);
        enter = myfragmentview.findViewById(R.id.enter);
        upkey = myfragmentview.findViewById(R.id.upkey);
        downkey = myfragmentview.findViewById(R.id.downkey);
        types = myfragmentview.findViewById(R.id.type);
        typestring = myfragmentview.findViewById(R.id.typestring);
        leftCLick = myfragmentview.findViewById(R.id.leftclick);
        rightclick = myfragmentview.findViewById(R.id.rightclick);
        leftkey = myfragmentview.findViewById(R.id.leftkey);
        rightkey = myfragmentview.findViewById(R.id.rightkey);



        /////////getting the link of connected device

        View linkView=inflater.inflate(R.layout.addlink,null);

        connDevice=linkView.findViewById(R.id.connectedLinkText);
        connecteddevice= connDevice.getText().toString();
        if(connecteddevice.length()>0) {
            String links[] = connecteddevice.split("-");
            link=links[1].toString();
        }
        else {
            Toast.makeText(getContext(),"established the connection",Toast.LENGTH_SHORT).show();
        }


        /////////////////////////////////


        ul.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mhandler.postDelayed(mAction, 10);
                        break;
                    case MotionEvent.ACTION_UP:
                        myfragmentview.performClick();
                        mhandler.removeCallbacks(mAction);
                        break;
                }
                return true;
            }

            Runnable mAction = new Runnable() {
                @Override
                public void run() {
                    code = "mouse";
                    data = "ul";
                    new SendPostRequest().execute();
                    mhandler.postDelayed(this, 100);
                }
            };
        });


        uc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mhandler.postDelayed(mAction, 10);
                        break;
                    case MotionEvent.ACTION_UP:
                        myfragmentview.performClick();
                        mhandler.removeCallbacks(mAction);
                        break;
                }
                return true;
            }

            Runnable mAction = new Runnable() {
                @Override
                public void run() {
                    code = "mouse";
                    data = "uc";
                    new SendPostRequest().execute();
                    mhandler.postDelayed(this, 100);
                }
            };
        });


        ur.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mhandler.postDelayed(mAction, 10);
                        break;
                    case MotionEvent.ACTION_UP:
                        myfragmentview.performClick();
                        mhandler.removeCallbacks(mAction);
                        break;
                }
                return true;
            }

            Runnable mAction = new Runnable() {
                @Override
                public void run() {
                    code = "mouse";
                    data = "ur";
                    new SendPostRequest().execute();
                    mhandler.postDelayed(this, 100);
                }
            };
        });


        ml.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mhandler.postDelayed(mAction, 10);
                        break;
                    case MotionEvent.ACTION_UP:
                        myfragmentview.performClick();
                        mhandler.removeCallbacks(mAction);
                        break;
                }
                return true;
            }

            Runnable mAction = new Runnable() {
                @Override
                public void run() {
                    code = "mouse";
                    data = "ml";
                    new SendPostRequest().execute();
                    mhandler.postDelayed(this, 100);
                }
            };
        });


        mr.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mhandler.postDelayed(mAction, 10);
                        break;
                    case MotionEvent.ACTION_UP:
                        myfragmentview.performClick();
                        mhandler.removeCallbacks(mAction);
                        break;
                }
                return true;

            }

            Runnable mAction = new Runnable() {
                @Override
                public void run() {
                    code = "mouse";
                    data = "mr";
                    new SendPostRequest().execute();
                    mhandler.postDelayed(this, 100);
                }
            };

        });


        ll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mhandler.postDelayed(mAction, 10);
                        break;
                    case MotionEvent.ACTION_UP:
                        myfragmentview.performClick();
                        mhandler.removeCallbacks(mAction);
                        break;
                }
                return true;
            }

            Runnable mAction = new Runnable() {
                @Override
                public void run() {
                    code = "mouse";
                    data = "ll";
                    new SendPostRequest().execute();
                    mhandler.postDelayed(this, 100);
                }
            };
        });


        lc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mhandler.postDelayed(mAction, 10);
                        break;
                    case MotionEvent.ACTION_UP:
                        myfragmentview.performClick();
                        mhandler.removeCallbacks(mAction);
                        break;
                }
                return true;
            }

            Runnable mAction = new Runnable() {
                @Override
                public void run() {
                    code = "mouse";
                    data = "lc";
                    new SendPostRequest().execute();
                    mhandler.postDelayed(this, 100);
                }
            };
        });


        lr.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mhandler.postDelayed(mAction, 10);
                        break;
                    case MotionEvent.ACTION_UP:
                        myfragmentview.performClick();
                        mhandler.removeCallbacks(mAction);
                        break;
                }
                return true;
            }

            Runnable mAction = new Runnable() {
                @Override
                public void run() {
                    code = "mouse";
                    data = "lr";
                    new SendPostRequest().execute();
                    mhandler.postDelayed(this, 100);
                }
            };
        });


        upkey.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mhandler.postDelayed(mAction, 10);
                        break;
                    case MotionEvent.ACTION_UP:
                        myfragmentview.performClick();
                        mhandler.removeCallbacks(mAction);
                        break;
                }
                return true;
            }

            Runnable mAction = new Runnable() {
                @Override
                public void run() {
                    code = "keyboard";
                    data = "upkey";
                    new SendPostRequest().execute();
                    mhandler.postDelayed(this, 100);
                }
            };
        });
        downkey.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mhandler.postDelayed(mAction, 10);
                        break;
                    case MotionEvent.ACTION_UP:
                        myfragmentview.performClick();
                        mhandler.removeCallbacks(mAction);
                        break;
                }
                return true;
            }

            Runnable mAction = new Runnable() {
                @Override
                public void run() {
                    code = "keyboard";
                    data = "downkey";
                    new SendPostRequest().execute();
                    mhandler.postDelayed(this, 100);
                }
            };
        });
        rightkey.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mhandler.postDelayed(mAction, 10);
                        break;
                    case MotionEvent.ACTION_UP:
                        myfragmentview.performClick();
                        mhandler.removeCallbacks(mAction);
                        break;
                }
                return true;
            }

            Runnable mAction = new Runnable() {
                @Override
                public void run() {
                    code = "keyboard";
                    data = "rightkey";
                    new SendPostRequest().execute();
                    mhandler.postDelayed(this, 100);
                }
            };
        });
        leftkey.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mhandler.postDelayed(mAction, 10);
                        break;
                    case MotionEvent.ACTION_UP:
                        myfragmentview.performClick();
                        mhandler.removeCallbacks(mAction);
                        break;
                }
                return true;
            }

            Runnable mAction = new Runnable() {
                @Override
                public void run() {
                    code = "keyboard";
                    data = "leftkey";
                    new SendPostRequest().execute();
                    mhandler.postDelayed(this, 100);
                }
            };
        });

        types.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                code = "type";
                data = typestring.getText().toString();
                new SendPostRequest().execute();
            }
        });
        leftCLick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                code = "mouse";
                data = "leftclick";
                new SendPostRequest().execute();
            }
        });
        rightclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                code = "mouse";
                data = "rightclick";
                new SendPostRequest().execute();
            }
        });
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                code = "keyboard";
                data = "enter";
                new SendPostRequest().execute();
            }
        });

        return myfragmentview;
    }


    /////////////////////////////////////////
    public class SendPostRequest extends AsyncTask<String, Void, String> {



        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {
            try {
                JSONObject postDataParams = new JSONObject();
                postDataParams.put("code", code);
                postDataParams.put("data", data);
                Log.e("params", postDataParams.toString());
                //URL url = new URL("http://"+link+":9000/control");
                URL url = new URL("http://192.168.43.212:9000/control");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    Log.e("url",url.toString());
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
            if(y=="0"){
                Toast.makeText(getActivity(),"Mouse Out of WIndow ",Toast.LENGTH_SHORT).show();

            } else if (y == "2") {

                Toast.makeText(getActivity(),"Keyboard Error ",Toast.LENGTH_SHORT).show();
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