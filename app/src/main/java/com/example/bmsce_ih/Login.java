package com.example.bmsce_ih;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.json.simple.JSONObject;
//import org.json.simple.JSONValue;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Login extends AppCompatActivity {

    //Variables
    Button callSignUp, login_btn;
    ImageView image;
    TextView logoText, sloganText;
    EditText username, password;

    JSONObject obj;

    //Credentials


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        //Hooks
        callSignUp = findViewById(R.id.signup_screen);
        image = findViewById(R.id.logo_image);
        logoText = findViewById(R.id.logo_name);
        sloganText = findViewById(R.id.slogan_name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login_btn = findViewById(R.id.login_btn);

//        Bundle bundle = new Bundle();
//        bundle.putString("usr", username.getText().toString());

        login_btn.setOnClickListener((view) -> {
            postData();
//            if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin"))
//            {

//            Intent intent = new Intent(Login.this, Dashboard.class);
//            startActivity(intent);
//            }
        });

        callSignUp.setOnClickListener((view) -> {
            Intent intent = new Intent(Login.this, Signup.class);


            Pair[] pairs = new Pair[7];

            pairs[0] = new Pair<View, String>(image, "logo_image");
            pairs[1] = new Pair<View, String>(logoText, "logo_text");
            pairs[2] = new Pair<View, String>(sloganText, "logo_desc");
            pairs[3] = new Pair<View, String>(username, "username_trans");
            pairs[4] = new Pair<View, String>(password, "password_trans");
            pairs[5] = new Pair<View, String>(login_btn, "button_trans");
            pairs[6] = new Pair<View, String>(callSignUp, "login_signup_trans");

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this, pairs);
            startActivity(intent, options.toBundle());


        });

    }

//    public String getUser() {
//         return username.getText().toString();
//    }


    public void postData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("username", username.getText().toString());
            object.put("password", password.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Enter the correct url for your api service site
        String url = "https://bmsbackend-production-fc41.up.railway.app/user/verify";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

//                        try {
////                            String doc = response.getString("document");
////                            String reqObj=doc.substring(1,doc.length()-1);
//
//
//                            JSONObject jsonObject = new JSONObject(response.toString());
//                            JSONArray jsonArray = jsonObject.getJSONArray("document");
//                            for(int i=0;i<jsonArray.length()-1;i++){
//                                obj= jsonArray.getJSONObject(i);
//
////                                String objStr = obj.toString();
////                                JSONObject room =
//                            username.setText(obj.getString("room_no"));
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
                        try {
//                            if (response.getString("Userdata") != null) {
//                                username.setText(response.toString());
//                            }
                            if (response.getInt("status") == 200) {
////                                username.setText(response);s
//                                username.setText(response.toString());
                                Intent intent = new Intent(Login.this, Dashboard.class);
                                String usr = username.getText().toString();
                                intent.putExtra("usr", username.getText().toString());
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(Login.this, "Sign up first", Toast.LENGTH_SHORT).show();
                                Intent intent2 = new Intent(Login.this, Signup.class);
                                startActivity(intent2);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }


//        public void getData() {
//            String url = "http://192.168.0.114:3000/user/getuser";
//
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            try {
//                                //converting response to json object
//                                JSONObject obj = new JSONObject(response);
//
//                                //if no error in response
//                                if (!obj.getBoolean("error")) {
//                                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
//
//                                    //getting the user from the response
//                                    JSONObject userJson = obj.getJSONObject("user");
//
//                                    String usr = userJson.getString("username");
//                                    String pass = userJson.getString("username");
//
//                                    if (username.getText().toString().equals(usr) && password.getText().toString().equals(pass)) {
//                                        Intent intent = new Intent(Login.this, Signup.class);
//                                        startActivity(intent);
//                                    }
//
//                                } else {
//                                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }) {
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String, String> params = new HashMap<>();
//                    params.put("username", username.getText().toString());
//                    params.put("password", password.getText().toString());
//                    return params;
//                }
//            };
////            VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
//        }
//
//    public void getData(){
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        try {
//            String url = "http://192.168.0.114:3000/user/verify";
//            JSONObject object = new JSONObject();
//            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {
////                    username.setText("Resposne : " + response);
//
//                    try {
//                        username.setText(response.getString("data"));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
////                    Toast.makeText(getApplicationContext(), "I am OK !" + response.toString(), Toast.LENGTH_LONG).show();
////                    Intent intent= new Intent(Login.this,Signup.class);
////                    startActivity(intent) ;
//                }
//            },
//            new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
//                }
//            });
//            requestQueue.add(jsonObjectRequest);
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public void postData() {
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        JSONObject object = new JSONObject();
//        try {
//            //input your API parameters
//            object.put("name",username.getText().toString());
//            object.put("email",password.getText().toString());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        // Enter the correct url for your api service site
//        String url = "http://192.168.0.114:3000/user/register";
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, object,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d("D","Done");
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("D",error.getMessage());
//            }
//        });
//        requestQueue.add(jsonObjectRequest);
//    }

}
