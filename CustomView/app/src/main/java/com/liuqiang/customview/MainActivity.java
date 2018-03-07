package com.liuqiang.customview;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.liuqiang.customviewlibrary.CustomButton;

import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    CustomButton customButton_code;
    AudioManager audioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customButton_code = (CustomButton) findViewById(R.id.customButton1);
        customButton_code.setShapeType(CustomButton.RECTANGLE)
                .setBgNormalColor(R.color.colorPrimary)
                .setBgPressedColor(R.color.colorPrimaryDark)
                .setBgDisableColor(R.color.white)
                .setCornersRadius(20)
                .setStrokeColor(R.color.black)
                .setStrokeWidth(10)
                .setTextNormalColor(R.color.white)
                .setTextPressedColor(R.color.black)
                .setTextDisableColor(R.color.gray)
                .setRippleColor(R.color.colorAccent)
                .use();


        audioManager = (AudioManager) getSystemService(Service.AUDIO_SERVICE);

        findViewById(R.id.up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioManager.adjustStreamVolume(9,
                        AudioManager.ADJUST_RAISE, AudioManager.FLAG_PLAY_SOUND
                                | AudioManager.FLAG_SHOW_UI);
            }
        });findViewById(R.id.down).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioManager.adjustStreamVolume(9,
                        AudioManager.ADJUST_LOWER, AudioManager.FLAG_PLAY_SOUND
                                | AudioManager.FLAG_SHOW_UI);
            }
        });

        customButton_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent checkIntent = new Intent();
                checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
                startActivityForResult(checkIntent, 1);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
               if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                    // success, create the TTS instance
                   read(MainActivity.this,"多少点多少分");
               } else {
                    // missing data, install it
                    Intent installIntent = new Intent();
                    installIntent.setAction(
                    TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                    startActivity(installIntent);
               }
        }
    }



    public static TextToSpeech mTts;

    public static void read(Context ctx, final String content) {
        if (mTts != null) {
            stop();
        }

        mTts = new TextToSpeech(ctx, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    if(mTts != null){
                        int result1 = mTts.setLanguage(Locale.US);
                        int result2 = mTts.setLanguage(Locale.CHINESE);
                        if (result1 == TextToSpeech.LANG_MISSING_DATA
                                || result1 == TextToSpeech.LANG_NOT_SUPPORTED
                                || result2 == TextToSpeech.LANG_MISSING_DATA
                                || result2 == TextToSpeech.LANG_NOT_SUPPORTED) {
                            //system.out("==tts=======数据丢失或不支持=============");
                        }else{
                            //system.out("=========tts初始化成功=============");
                            // 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
                            mTts.setPitch(1.0f);
                            // 设置语速
                            mTts.setSpeechRate(1.0f);

                            Log.e("","语音播报内容--》： " + content);
                            HashMap myHashAlarm = new HashMap();
                            myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_STREAM,
                            String.valueOf(AudioManager.STREAM_ALARM));
                            mTts.speak(content, TextToSpeech.QUEUE_FLUSH, myHashAlarm);
                            mTts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                                @Override
                                public void onDone(String utteranceId) {
                                    stop();
                                }

                                @Override
                                public void onError(String utteranceId) {
                                    stop();
                                }

                                @Override
                                public void onStart(String utteranceId) {
                                }
                            });

                        }
                    }
                }else{
                    //system.out("==========tts初始化失败============");
                }
            }
        });
    }

    private static void stop() {
        if(mTts != null){
            try {
                mTts.stop();
                mTts.shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
