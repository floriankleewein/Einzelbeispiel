package com.floriankleewein.einstiegsbeispiel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Button sendButton = findViewById(R.id.sendButton);
        final EditText inputFromUser = findViewById(R.id.matrikelNrInput);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TCPClient client = new TCPClient(inputFromUser.getText().toString());
                client.start();
                TextView outputTextView = findViewById(R.id.serverResponseTextView);

                try {
                    client.join(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String outputToShow = client.getResponseServer();
                outputTextView.setText(outputToShow);
            }
        });

        Button calcButton = findViewById(R.id.calculateButton);
        final TextView calcTextView = findViewById(R.id.calcTextView);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               char[] input =  inputFromUser.getText().toString().toCharArray();
               int x;
               int sum = 0;
               for(int i = 0; i < input.length; i++){
                   x = Integer.parseInt(input[i] + "");
                   sum+=x;
               }
                String test = Integer.toBinaryString(sum);
               calcTextView.setText("Quersumme der Matrikelnummer in Binärdarstellung: " + Integer.toBinaryString(sum));
            }
        });
    }
}
