package com.example.bmsce_ih;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AccountsFragment extends Fragment {

    private int starter = 66; //ASCII code for `B`
    LinearLayout cards;
    FloatingActionButton buttonAdd;

    public AccountsFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_accounts, container, false);
        init(view);
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
//        outState.putAll("card",cards);
        getFragmentManager().putFragment(outState, "card", this);
    }

    private void init(View view) {
        cards = view.findViewById(R.id.cards);
        buttonAdd = view.findViewById(R.id.butAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardView newCard = new CardView(getActivity());
                getLayoutInflater().inflate(R.layout.card_base, newCard);

                TextView t = newCard.findViewById(R.id.textviewClassesBlock1);

                String current = Character.toString((char) starter++);

                t.setText("Block " + current);
                newCard.setTag(current);

                cards.addView(newCard);
            }
        });
    }


}
