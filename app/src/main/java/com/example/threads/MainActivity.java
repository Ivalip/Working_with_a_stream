 package com.example.threads;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import com.example.threads.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    static String TEXTTOVIEW =  "Этот город самый лучший город на Земле,\n" +
                                "Он как будто нарисован мелом на стене,\n" +
                                "Нарисованы бульвары реки и мосты,\n" +
                                "Разноцветные веснушки белые банты.\n"+
                                "Этот город просыпаясь смотрит в облака\n"+
                                "Где то там совсем недавно пряталась луна\n"+
                                "А теперь взрывают птицы крыльями восход\n"+
                                "И куда то уплывает белый пароход\n"+
                                "Этот город не похожий ни на что вокруг\n"+
                                "Улыбается прохожий и за 5 минут\n"+
                                "Помогая человеку верить в чудеса\n"+
                                "Распускаются фонтаны прямо в небеса\n"+
                                "Я не знаю где еще на этом свете есть такая же весна\n"+
                                "Я пожалуй отпущу попутный ветер и останусь навсегда\n"+
                                "Голубые тротуары синие цветы\n"+
                                "Ярко-желтые трамваи розовые сны\n"+
                                "Он как будто нарисован мелом на стене\n"+
                                "Этот город самый лучший город на Земле\n"+
                                "Я не знаю где еще на этом свете есть такая же весна\n"+
                                "Я пожалуй отпущу попутный ветер и останусь навсегда\n"+
                                "С тобою\n"+
                                "Я не знаю где еще на этом свете есть такая же весна\n"+
                                "Я пожалуй отпущу попутный ветер и останусь навсегда\n"+
                                "С тобою\n"+
                                "Я не знаю где еще на этом свете есть такая же весна\n"+
                                "Я пожалуй отпущу попутный ветер и останусь навсегда\n";

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                char [] chars = (char[]) msg.obj;
                String str = String.valueOf(chars);
                binding.TV.setText(str);
            }
        };
        binding.But.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyThread myThread = new MyThread(TEXTTOVIEW);
                myThread.start();
            }
        });

    }

    class MyThread extends Thread {
        private char[] textToView;
        private String text;
        public MyThread (String text) {
            this.text = text;
            this.textToView = new char[text.length()];
        }

        @Override
        public void run() {
            super.run();
            char [] textchars = text.toCharArray();
            String punct500 = "!?.:";
            String punct200 = ",:-";
            for (int i = 0; i < textchars.length; i++) {
                char ch = textchars[i];
                textToView[i] = ch;

                Message msg = new Message();
                msg.obj = textToView;
                handler.sendMessage(msg);
                if (punct500.contains(String.valueOf(textchars[i]))) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else if (punct200.contains(String.valueOf(textchars[i]))) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {

                    }
                } else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                }
            }
        }
    }
}