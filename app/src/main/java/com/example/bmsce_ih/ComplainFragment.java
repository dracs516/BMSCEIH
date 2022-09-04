package com.example.bmsce_ih;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class ComplainFragment extends Fragment {
    Dialog createDialog;
    EditText title, description;
    Button createTicket;
    EditText text;
    JSONObject obj;
    LinearLayout cards;
    TextView comaplaint;


    private Context mContext;
//    String message;

//    public ComplainFragment() {
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complaints, container, false);
        init(view);
        return view;
    }

    private void init(View view) {

        createDialog = new Dialog(getContext());
        createDialog.setContentView(R.layout.fragment_create_complaints);
//        createDialog.dismiss();
        TextView btnClose;
        CardView createCard, viewCard, hideCard;
        LinearLayout complaint_list;

        createCard = view.findViewById(R.id.createForm);
        viewCard = view.findViewById(R.id.view_complaints);
        hideCard = view.findViewById(R.id.hide_complaints);
        complaint_list = view.findViewById(R.id.complaint_list);

        title = createDialog.findViewById(R.id.complaint_title);
        description = createDialog.findViewById(R.id.complaint_desc);
        createTicket = createDialog.findViewById(R.id.create_ticket);
        btnClose = (TextView) createDialog.findViewById(R.id.btnClose);


        createCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog.show();
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog.dismiss();
            }
        });

        viewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complaint_list.setVisibility(View.VISIBLE);
                viewCard.setVisibility(View.GONE);
                hideCard.setVisibility(View.VISIBLE);
                viewComplaint(view);
            }
        });

        hideCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                complaint_list.setVisibility(View.GONE);
                hideCard.setVisibility(View.GONE);
                viewCard.setVisibility(View.VISIBLE);
            }
        });

        createTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                postDataComplaint();
                viewComplaint(view);
                complaint_list.setVisibility(View.VISIBLE);
            }
        });
    }

    public void postDataComplaint() {
        Bundle bundle = getArguments();
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            if (bundle != null) {
                object.put("username", bundle.getString("usr1"));
                object.put("title", title.getText().toString());
                object.put("description", description.getText().toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Enter the correct url for your api service site
        String url = "http://192.168.8.161:3000/complaint/register/complaint";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        createDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("D", error.getMessage());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }


    public void viewComplaint(View view) {
        cards = view.findViewById(R.id.cards);


        Bundle bundle = getArguments();
//        Log.d("as",bundle.getString("usr1"));
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            if (bundle != null) {
                object.put("username", bundle.getString("usr1"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Enter the correct url for your api service site
        String url = "http://192.168.8.161:3000/complaint/getcomplaint";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        cards.removeAllViews();
//                        comaplaint= cards.findViewById(R.id.complaint);
//                        comaplaint.setText("Complaints");


                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
//                            Log.d("usr",bundle.getString("usr1"));
                            Log.d("sd", response.toString());
                            JSONArray jsonArray = jsonObject.getJSONArray("Userdata");


                            for (int i = jsonArray.length()-1; i >=0 ; i--) {

                                obj = jsonArray.getJSONObject(i);

                                CardView newCard = new CardView(mContext);
                                getLayoutInflater().inflate(R.layout.card_base, newCard);

                                TextView t = newCard.findViewById(R.id.textviewClassesBlock1);
                                TextView d = newCard.findViewById(R.id.desc);
//                        String current = Character.toString((char) starter++);
                                t.setText(obj.getString("title"));
                                d.setText(obj.getString("description"));
                                newCard.setTag(obj.getString("title"));
                                cards.addView(newCard);

//                                username.setText(obj.getString("room_no"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


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
