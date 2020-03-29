package com.jadd.texttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech textToSpeech;
    EditText text;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);
        button = findViewById(R.id.button);

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i == TextToSpeech.SUCCESS){
                    int result = textToSpeech.setLanguage(Locale.UK);

                    if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Toast.makeText(MainActivity.this, "Language not supported!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        button.setEnabled(true);
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Initialization Problem", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak();
            }
        });

    }

    private void speak() {
        String textname = text.getText().toString();
        textToSpeech.setPitch(1f);
        textToSpeech.setSpeechRate(0.75f);
        textToSpeech.speak(textname,TextToSpeech.QUEUE_FLUSH,null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(textToSpeech!=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}
