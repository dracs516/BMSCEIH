package com.example.bmsce_ih;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;



public class Signup extends AppCompatActivity {

    EditText firstname, lastname, username,email, phone, room , password;
    Button submit,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView(R.layout.activity_signup);


        firstname=findViewById(R.id.first_name);
        lastname=findViewById(R.id.last_name);
        username= findViewById(R.id.username);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone_no);
        room=findViewById(R.id.room_no);
        password= findViewById(R.id.password);
        submit=findViewById(R.id.signup_btn);
        login=findViewById(R.id.login_screen);


        submit.setOnClickListener((view) -> {
            postData();
            Intent intent = new Intent(Signup.this,Login.class);
            startActivity(intent);
        });

        login.setOnClickListener((view) -> {
            Intent intent = new Intent(Signup.this,Login.class);
            startActivity(intent);
        });

    }

    public void postData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("first_name",firstname.getText().toString());
            object.put("last_name",lastname.getText().toString());
            object.put("username",username.getText().toString());
            object.put("email",email.getText().toString());
            object.put("phone_num",phone.getText().toString());
            object.put("room_no",room.getText().toString());
            object.put("password",password.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Enter the correct url for your api service site
        String url = "http://10.210.32.134:3000/user/register";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("D","Done");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("D",error.getMessage());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}