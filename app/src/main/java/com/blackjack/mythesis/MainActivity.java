package com.blackjack.mythesis;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = new Database(this);

        final EditText editText = findViewById(R.id.ed_aksara);
        final TextView textView = findViewById(R.id.tv_latin);
        Button button = findViewById(R.id.submit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = editText.getText().toString();

                StringBuilder hasil = new StringBuilder();
                int index = 0;
                StringBuilder tumpukan = new StringBuilder();
                StringBuilder tumpukan2 = new StringBuilder();
                ArrayList<String> latin = new ArrayList<>();
                ArrayList<String> temps = new ArrayList<>();
                String search = "";
                String temp= "";


                for (int i = 0; i < text.length(); i++) {
                    try {
                        temp = text.charAt(i)+""+text.charAt(i+1)+""+text.charAt(i+2)+"";
                        search = database.search(temp);
                        System.out.println("3");
                        System.out.println(search);
                        if (!TextUtils.isEmpty(search)){
                            hasil.append(search);
                            i+=2;
                            continue;
                        }
                    }catch (Exception e){

                    }
                    try {
                        temp = text.charAt(i)+""+text.charAt(i+1)+"";
                        search = database.search(temp);
                        System.out.println(temp);
                        System.out.println("2");
                        System.out.println(search);
                        if (!TextUtils.isEmpty(search)){
                            hasil.append(search);
                            i+=1;
                            continue;
                        }
                    }catch (Exception e){

                    }
                    try {
                        temp = text.charAt(i)+"";
                        search = database.search(temp);
                        System.out.println(temp);
                        System.out.println("1");
                        System.out.println(search);
                        if (!TextUtils.isEmpty(search)){
                            hasil.append(search);
//                            i+=1;
                            continue;
                        }
                    }catch (Exception e){

                    }

                }



                textView.setText(hasil);
            }
        });
    }
};
