package com.example.bmsce_ih;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Dashboard extends AppCompatActivity {

TextView text;
    Fragment active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Intent intent = getIntent();
        String usr = intent.getStringExtra("usr");

        Bundle bundle = new Bundle();
        bundle.putString("usr1",usr);


        final Fragment fragment1 = new NoticeboardFragment();


        /////Fragments 2 and 3 are interchanged due to my error in switch case below:::::::::
        final Fragment fragment3 = new ComplainFragment();
        fragment3.setArguments(bundle);
        final Fragment fragment2 = new AccountsFragment();
        final Fragment fragment4 = new RulesFragment();
        final Fragment fragment5 = new ContactsFragment();
        final FragmentManager fm = getSupportFragmentManager();
        active = fragment1;
//        loadFragments(new NoticeboardFragment());
        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav_bar);
//        bottomNav.setOnItemSelectedListener(navListener);

        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
//                        fm.beginTransaction().replace(R.id.fragment_container,fragment3).commit();
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
        });

        fm.beginTransaction().add(R.id.fragment_container, fragment5, "5").hide(fragment5).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragment4, "4").hide(fragment4).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragment1, "1").commit();
//        finish();

//        Login lgn  = new Login();

//        String u = lgn.getUser();
//        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,new NoticeboardFragment()).commit();
    }


    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();

        if (manager.getBackStackEntryCount() > 1) {
            // If there are back-stack entries, leave the FragmentActivity
            // implementation take care of them.
            manager.popBackStack();

        } else {
            // Otherwise, ask user if he wants to leave :)
            new AlertDialog.Builder(this)
                    .setTitle("Really Exit?")
                    .setMessage("Are you sure you want to exit?")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            // MainActivity.super.onBackPressed();
                            ExitActivity.exitApplication(getApplicationContext());
                            finish();
                            moveTaskToBack(true);
                        }
                    }).create().show();
        }



//    private boolean loadFragments(Fragment fragment) {
//
//        if(fragment!=null)
//        {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).addToBackStack(null).commit();
//        }
//        return true;
//    }

//    private BottomNavigationView.OnItemSelectedListener navListener =
//            new BottomNavigationView.OnItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
////                    Fragment selectedFragment = null;
//
//
//                    switch (item.getItemId()) {
//                        case R.id.nav_notice_board:
////                            selectedFragment= new NoticeboardFragment();
//                            fm.beginTransaction().hide(active).show(fragment1).commit();
//                            active = fragment1;
////                            break;
//                            return true;
//                        case R.id.nav_accounts:
////                            selectedFragment= new AccountsFragment();
//                            fm.beginTransaction().hide(active).show(fragment2).commit();
//                            active = fragment2;
////                            break;
//                            return true;
//                        case R.id.nav_complaints:
////                            selectedFragment= new ComplainFragment();
//                            fm.beginTransaction().hide(active).show(fragment3).commit();
//                            active = fragment3;
////                            break;
//                            return true;
//                        case R.id.nav_rules:
////                            selectedFragment= new RulesFragment();
//                            fm.beginTransaction().hide(active).show(fragment4).commit();
//                            active = fragment4;
////                            break;
//                            return true;
//                        case R.id.nav_contact:
////                            selectedFragment= new ContactsFragment();
//                            fm.beginTransaction().hide(active).show(fragment5).commit();
//                            active = fragment5;
////                            break;
//                            return true;
//                    }
//
//                    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, active).commit();
//                    return true;
//                }
//
//            };
}

//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        new MainActivity();
////        moveTaskToBack(true);
////        super.onRestart();
//    }
}