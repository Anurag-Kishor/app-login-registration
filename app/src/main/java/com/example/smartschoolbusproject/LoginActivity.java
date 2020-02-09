package com.example.smartschoolbusproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;

public class LoginActivity extends AppCompatActivity {


    private Button btnLogin, btnRegister;
    private EditText edtTxtUserL, edtTextPassL;
    private TextView fgtPassL, txtErrorMessage;
    RequestQueue requestQueue;
    String url = "http://192.168.1.6/php/usermanage/logincheck.php";
    String emailL,passL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Smart School Bus");

        getData();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    btn_login();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_signup_form();
            }
        });

    }

    private void getData(){

        edtTxtUserL =  findViewById(R.id.edtTxtUserL);
        edtTextPassL =  findViewById(R.id.edtTxtPassL);
        btnLogin =  findViewById(R.id.btnLogin);
        btnRegister =  findViewById(R.id.btnRegister);
        fgtPassL =  findViewById(R.id.fgtPassL);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        txtErrorMessage = findViewById(R.id.txtErrorMessage);


    }

    private void btn_login(){

        byte[] md5input = edtTextPassL.getText().toString().getBytes();
        BigInteger md5Data = null;

        try {
            md5Data = new BigInteger(1,md5.encryptMD5(md5input));
        } catch (Exception e) {
            e.printStackTrace();
        }

        passL = md5Data.toString(16);
        emailL = edtTxtUserL.getText().toString();

//        Intent intent = new Intent(getApplicationContext(), Home.class);
//        startActivity(intent);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                          JSONArray users = response.getJSONArray("User");

                            for(int i = 0; i < users.length() ; i++){
                                JSONObject user = users.getJSONObject(i);

                                String email = user.getString("UserEmail");
                                String password = user.getString("Password");

                               if(emailL.equals(email) && passL.equals(password)){
                                    Intent in = new Intent(getApplicationContext(),Home.class);
                                   startActivity(in);

                                }
                               else if(emailL.equals(email) && !passL.equals(password)){

                                  txtErrorMessage.setText("Incorrect password");
                               }
                               else{
                                    txtErrorMessage.setText("User not found. Register if new.");
                               }


                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
            requestQueue.add(jsonObjectRequest);

            txtErrorMessage.setText(passL);
    }

    private void btn_signup_form() {

        startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
    }

    @Override
    public void onBackPressed() {

        finishAffinity();
        finish();
    }
}
