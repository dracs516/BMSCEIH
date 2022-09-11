package com.example.bmsce_ih;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Signup extends AppCompatActivity {

    EditText firstname, lastname, username, email, phone, room, password;
    Button submit, login;
    AutoCompleteTextView hostel;
    TextInputLayout hostels;
    //    ArrayList<String> arrayList_hostels;
    ArrayAdapter<String> arrayAdapter_hostels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);


        firstname = findViewById(R.id.first_name);
        lastname = findViewById(R.id.last_name);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone_no);
        room = findViewById(R.id.room_no);
        password = findViewById(R.id.password);
        submit = findViewById(R.id.signup_btn);
        login = findViewById(R.id.login_screen);

//        val hostelList = getResources().getStringArray(R.array.hostelList);
//        val arrayAdapter = ArrayAdapter(requireContext())
        hostels = (TextInputLayout) findViewById(R.id.hostels);
        hostel = (AutoCompleteTextView) findViewById(R.id.hostels_list);

//        arrayList_hostels = new ArrayList<>();
        List<String> arrayList_hostels = Arrays.asList(getResources().getStringArray(R.array.hostelList));

        arrayAdapter_hostels = new ArrayAdapter<>(getApplicationContext(), R.layout.hostel_dropdown, arrayList_hostels);
        hostel.setAdapter(arrayAdapter_hostels);

        hostel.setThreshold(1);

//        hostels_list.setOnClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });


        submit.setOnClickListener((view) -> {
            postData();
            Intent intent = new Intent(Signup.this, Login.class);
            startActivity(intent);
        });

        login.setOnClickListener((view) -> {
            Intent intent = new Intent(Signup.this, Login.class);
            startActivity(intent);
        });

    }

    public void postData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("first_name", firstname.getText().toString());
            object.put("last_name", lastname.getText().toString());
            object.put("username", username.getText().toString());
            object.put("email", email.getText().toString());
            object.put("phone_num", phone.getText().toString());
            object.put("room_no", room.getText().toString());
            object.put("hostel",hostel.getText().toString());
            object.put("password", password.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Enter the correct url for your api service site
        String url = "https://bmsih.herokuapp.com/user/register";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("D", "Done");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("D", error.getMessage());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}