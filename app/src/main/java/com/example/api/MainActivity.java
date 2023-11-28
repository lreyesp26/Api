package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Login(View view){
        EditText txtusuario = (EditText) findViewById(R.id.txtUsuario);
        EditText txtclave = (EditText) findViewById(R.id.txtClave);

        String url = "https://revistas.uteq.edu.ec/ws/login.php?usr="+ txtusuario.getText().toString() +
                "&pass=" + txtclave.getText().toString();

        //Toast.makeText(getApplicationContext(), "Hola " + txtusuario.getText().toString() +
        //" Clave" + txtclave.getText().toString(), Toast.LENGTH_LONG).show();

        //Snackbar.make(view, url, BaseTransientBottomBar.ANIMATION_MODE_FADE).show();


        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(url, datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtResp = (TextView) findViewById(R.id.txtRespuesta);
        txtResp.setText(result);
    }


    public void LoginWithVoller(View view){
        RequestQueue queue = Volley.newRequestQueue(this);
        EditText txtusuario2 = (EditText) findViewById(R.id.txtUsuario);
        EditText txtclave2 = (EditText) findViewById(R.id.txtClave);
        String url = "https://revistas.uteq.edu.ec/ws/login.php?usr="+ txtusuario2.getText().toString() +
                "&pass=" + txtclave2.getText().toString();
        TextView txtResp = (TextView) findViewById(R.id.txtRespuesta);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        txtResp.setText("Response is: "+ response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txtResp.setText("Error " + error.getMessage());
                    }
                });
        queue.add(stringRequest);
    }
}