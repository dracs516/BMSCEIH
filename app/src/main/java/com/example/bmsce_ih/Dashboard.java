package com.example.bmsce_ih;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dashboard extends AppCompatActivity {

    final Fragment fragment1 = new NoticeboardFragment();


    /////Fragments 2 and 3 are interchanged due to my error in switch case below:::::::::
    final Fragment fragment3 = new ComplainFragment();
    final Fragment fragment2 = new AccountsFragment();
    final Fragment fragment4 = new RulesFragment();
    final Fragment fragment5 = new ContactsFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


//        loadFragments(new NoticeboardFragment());
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav_bar);
        bottomNav.setOnItemSelectedListener(navListener);

        fm.beginTransaction().add(R.id.fragment_container, fragment5, "5").hide(fragment5).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragment4, "4").hide(fragment4).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragment1, "1").commit();



//        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new NoticeboardFragment()).commit();
    }

//    private boolean loadFragments(Fragment fragment) {
//
//        if(fragment!=null)
//        {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack(null).commit();
//        }
//        return true;
//    }

    private BottomNavigationView.OnItemSelectedListener navListener =
            new BottomNavigationView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    Fragment selectedFragment = null;







                    switch (item.getItemId()) {
                        case R.id.nav_notice_board:
//                            selectedFragment= new NoticeboardFragment();
                            fm.beginTransaction().hide(active).show(fragment1).commit();
                            active = fragment1;
//                            break;
                            return true;
                        case R.id.nav_accounts:
//                            selectedFragment= new AccountsFragment();
                            fm.beginTransaction().hide(active).show(fragment2).commit();
                            active = fragment2;
//                            break;
                            return true;
                        case R.id.nav_complaints:
//                            selectedFragment= new ComplainFragment();
                            fm.beginTransaction().hide(active).show(fragment3).commit();
                            active = fragment3;
//                            break;
                            return true;
                        case R.id.nav_rules:
//                            selectedFragment= new RulesFragment();
                            fm.beginTransaction().hide(active).show(fragment4).commit();
                            active = fragment4;
//                            break;
                            return true;
                        case R.id.nav_contact:
//                            selectedFragment= new ContactsFragment();
                            fm.beginTransaction().hide(active).show(fragment5).commit();
                            active = fragment5;
//                            break;
                            return true;
                    }

                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, active).commit();
                    return true;
                }

            };
}