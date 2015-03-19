package com.example.sati.ejemplolistenerdevf;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private EditText editText;
    private TextView textView;
    private Button button1;
    private Button button2;
    private Button button3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);

        editText = (EditText) findViewById(R.id.editText);
        textView = (TextView) findViewById(R.id.textView);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button1:
                Toast.makeText(this, "Presione el boton 1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                Toast.makeText(this, "Presione el bot0n 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3:
                textView.setText(editText.getText());
                //Corro el Hilo
                new HiloAdroid().execute();
                break;
        }
    }


    public class HiloAdroid extends AsyncTask<Void, Integer, Void> {


        private int anterior = -1;

        @Override
        protected Void doInBackground(Void... params) {

            //Se ejecuta el Hilo
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            //Terminal el Hilo
            switch (anterior) {
                case 0:
                    button1.setBackgroundColor(R.color.default_color);
                    break;
                case 1:
                    button2.setBackgroundColor(R.color.default_color);
                    break;
                default:
                    //no hace nada
                    break;
            }


            switch (values[0]) {
                case 0:
                    button1.setBackgroundColor(getResources().getColor(R.color.red));
                    break;
                case 1:
                    button2.setBackgroundColor(getResources().getColor(R.color.blue));
                    break;
                default:
//                    no hace nada
                    break;
            }

            anterior = values[0];

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            //Comparar

        }
    }


}
