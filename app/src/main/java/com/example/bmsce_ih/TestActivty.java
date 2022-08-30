package com.example.bmsce_ih;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;

public class TestActivty extends AppCompatActivity
{
    private int starter = 66; //ASCII code for `B`
    LinearLayoutCompat cards;
    Button buttonAdd;
    Button buttonDoSth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_test);

        cards = findViewById(R.id.cards);
        buttonAdd = findViewById(R.id.butAdd);
        buttonDoSth = findViewById(R.id.butDoSth);

        buttonAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                CardView newCard = new CardView(TestActivty.this);
                getLayoutInflater().inflate(R.layout.card_base, newCard);

                TextView t = newCard.findViewById(R.id.textviewClassesBlock1);

                String current = Character.toString((char) starter++);

                t.setText("Block " + current);
                newCard.setTag(current); //

                cards.addView(newCard);
            }
        });

        buttonDoSth.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                findBlockAndDoSomething("B");
            }
        });
    }

    private void findBlockAndDoSomething(String name)
    {
        Log.d("MyTAG", "CLICK");

        for (int i = 0; i < cards.getChildCount(); i++)
        {
            CardView selected = (CardView) cards.getChildAt(i);

            if (selected.getTag() != null && selected.getTag().toString().equals(name))
            {
                // do something. E.g change block name
                TextView textViewClassesBlock1 = selected.findViewById(R.id.textviewClassesBlock1);
                textViewClassesBlock1.setText("Block XXX");
                return;
            }
        }
    }
}