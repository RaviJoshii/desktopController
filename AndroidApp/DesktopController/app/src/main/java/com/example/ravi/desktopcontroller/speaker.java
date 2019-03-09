package com.example.ravi.desktopcontroller;

import android.app.Activity;
import android.arch.core.executor.TaskExecutor;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionService;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;


public class speaker extends Fragment  {
    private TextView txtSpeechInput;
    private Button btnspeak;
    private final int REQ_CODE_SPEECH_INPUT=100;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View myfragmentview=inflater.inflate(R.layout.speaker,null);

        txtSpeechInput=myfragmentview.findViewById(R.id.speaktext);
        btnspeak=myfragmentview.findViewById(R.id.speakbutton);
       btnspeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               promptSpeechInput();
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
                    txtSpeechInput.setText(result.get(0));
                }
                break;
            }
        }
    }


}