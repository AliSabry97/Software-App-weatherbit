package com.example.ali.finalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView temp, description, clouds, sunset;
    EditText one;
    String parse;

    Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        one=(EditText) findViewById(R.id.edit1);
        temp = (TextView) findViewById(R.id.textView1);
        description = (TextView) findViewById(R.id.textView2);
        clouds = (TextView) findViewById(R.id.textView3);
        sunset = (TextView) findViewById(R.id.textView4);
        button=(Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getweather(v);
            }
        });


}
    public void getweather(View view) {
        parse=String.valueOf( one.getText());
        String url = "https://api.weatherbit.io/v2.0/current?city="+parse+"&key=59ceedd9397a4f6ab23aabd6a6883597";
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                   JSONArray array=response.getJSONArray("data");
                    JSONObject object=array.getJSONObject(0);
                    String tempo = String.valueOf(object.getDouble("temp"));
                    temp.setText("The Current Temperature is : "+tempo);
                    String cloudss=String.valueOf(object.getInt("clouds"));
                    clouds.setText("The current clouds coverage: "+cloudss);
                    String sunsetts=String.valueOf(object.getDouble("app_temp"));
                    sunset.setText("The Apparent  or feels like temperature: "+sunsetts);

                    String  desss=String.valueOf(object.getString("weather"));
                    description.setText(desss);
                    Toast.makeText(getApplicationContext(),parse,Toast.LENGTH_LONG).show();




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jor);
    }
}