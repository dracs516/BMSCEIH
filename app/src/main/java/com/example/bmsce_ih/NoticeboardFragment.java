package com.example.bmsce_ih;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NoticeboardFragment extends Fragment {
    private RecyclerView recyclerView;
    SharedPreferences newPreference;
    TextView usr;
    Intent newIntent;
Button logout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_noticeboard, container, false);
        init(view);
        return view;
    }



    private void init(View view) {
        logout = view.findViewById(R.id.logout_btn);
        usr = view.findViewById(R.id.user);

        newPreference = this.getActivity().getSharedPreferences("session", MODE_PRIVATE);
        usr.setText(newPreference.getString("uss",null));
        newIntent = new Intent(getActivity(), MainActivity.class);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor edit = newPreference.edit();
                edit.clear();
                edit.commit();
                startActivity(newIntent);
            }
        });
    }
}
