package com.example.smartschoolbusproject;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private EditText edtTxtUser, edtTxtEmail, edtTxtAddress, edtTxtPassword, edtTxtConfPass, edtTxtMobile;
    private Button btnRegister;
    RequestQueue requestQueue;
    String url = "http://192.168.1.6/php/usermanage/registration.php";
    private TextInputLayout userTxtInp, emailTxtInp, addressTxtInp, mobileTxtInp, passTxtInp, confPassTxtInp;
    TextView test;
    boolean check1 = true; //username
    boolean check2 = true; //email
    boolean check3 = true; //address
    boolean check4 = true; //mobile
    boolean check5 = true; //pass
    boolean check6 = true; //conf pass
    boolean check7 = true; //pass == confpass?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setTitle("Register");

        getData();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });



    }

    public void getData(){
        edtTxtUser =  findViewById(R.id.edtTxtUser);
        edtTxtEmail =  findViewById(R.id.edtTxtEmail);
        edtTxtAddress =  findViewById(R.id.edtTxtAddress);
        edtTxtMobile =  findViewById(R.id.edtTxtMobile);
        edtTxtPassword =  findViewById(R.id.edtTxtPassword);
        edtTxtConfPass =  findViewById(R.id.edtTxtConfPassword);
        btnRegister =  findViewById(R.id.btnRegister);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        test = findViewById(R.id.test);

    }

  public void validate(){   //contains methods for validation of text fields

        validateUsername();
        validateEmail();
        validateAddress();
        validateMobile();
        validatePassword();
        validateConfPass();
        verifyPass(); //checks if pass and conf pass are same

      if(check1 == true && check2 == true && check3 == true && check4 == true && check5 == true && check6 == true && check7 == true){
          registerUser();
      }
      else {
          Toast.makeText(getApplicationContext(),"Registration unsuccessful",Toast.LENGTH_LONG).show();;
      }

   }

    private void validateUsername() {

          if(edtTxtEmail.getText().toString().equals("")){
            edtTxtUser.setError("Email cannot be empty!");
              check1 = false;

          }

        String validemail = "[a-zA-Z0-9\\_]{4,15}";

            String email = edtTxtUser.getText().toString();
            Matcher matcher = Pattern.compile(validemail).matcher(email);
            if(matcher.matches()){

                check1 = true;
            }
            else{
                edtTxtUser.setError("Invalid Username! Username needs to have atleast 4 characters and can have numbers and underscores.");
                check1 = false;
            }


    }
    private void validateEmail() {
        if(edtTxtEmail.getText().toString().equals("")){
            edtTxtEmail.setError("Email cannot be empty!");
            check2 = false;

        }

        String validemail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-z0-9][a-zA-z0-9\\-]{0,64}" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9]{0,25}";

        String email = edtTxtEmail.getText().toString();
        Matcher matcher = Pattern.compile(validemail).matcher(email);
        if(matcher.matches()){

            check2 = true;

        }
        else{
            edtTxtEmail.setError("Invalid Email!");
            check2 = false;

        }
    }
    private void validateAddress() {

        if (edtTxtAddress.getText().toString().equals("")) {
            edtTxtAddress.setError("Address cannot be empty!");
            check3 = false;

        }
        else{
            check3 = true;

        }
    }
    private void validateMobile() {

        if(edtTxtMobile.getText().toString().equals("")){
            edtTxtMobile.setError("Mobile number cannot be empty!");
            check4 = false;
        }

            String validemail = "[0-9]{10}";

            String email = edtTxtMobile.getText().toString();
            Matcher matcher = Pattern.compile(validemail).matcher(email);
            if(matcher.matches()){

                check4 = true;

            }
            else{
                edtTxtMobile.setError("Invalid Mobile number. Do not use country code or any symbols. 10 digit number only.");
                check4 = false;

            }
        }
    private void validatePassword() {

        if(edtTxtPassword.getText().toString().equals("")){
            edtTxtPassword.setError("Password cannot be empty!");
            check5 = false;
        }
        String regexPassword = "[a-zA-Z0-9\\~\\`\\!\\@\\#\\$%\\^&\\*\\(\\)\\-\\_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]{8,}";
        String pass = edtTxtPassword.getText().toString();
        Matcher matcher = Pattern.compile(regexPassword).matcher(pass);
        if(matcher.matches()) {
                check5 = true;

        }
        else{
            edtTxtPassword.setError("Password must be at least 8 character long and should have alphanumeric and a special character.");
            check5 = false;

        }
    }
    private void validateConfPass() {
        if(edtTxtConfPass.getText().toString().equals("")){
            edtTxtConfPass.setError("Confirm Password cannot be empty!");
            check6 = false;
        }
        else{
            check6 = true;

        }
    }
    private void verifyPass(){

        String s1 = edtTxtPassword.getText().toString();
        String s2 = edtTxtConfPass.getText().toString();

        if(s1.equals(s2)){

            check7 = true;
        }
        else{
            edtTxtConfPass.setError("Retyped password is not same!");
            check7 = false;

        }

    }


    public void registerUser(){

        test.setText("Working...");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                test.setText(response);
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                test.setText("That didn't work!");
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parameters = new HashMap<String,String>();
                parameters.put("UserEmail", edtTxtEmail.getText().toString());
                parameters.put("Password", edtTxtPassword.getText().toString());
                parameters.put("Username", edtTxtUser.getText().toString());
                parameters.put("UserAddress", edtTxtAddress.getText().toString());
                parameters.put("UserMob", edtTxtMobile.getText().toString());
                return parameters;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/json");
                return params;
            }
        };
        requestQueue.add(stringRequest);

     //   Toast.makeText(getApplicationContext(),"Registration successful. Check email for final confirmation",Toast.LENGTH_LONG).show();;


    }
}
