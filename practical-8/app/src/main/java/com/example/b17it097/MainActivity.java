package com.example.b17it097;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    Fragment f1,f2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Fragment Pratica8");
        f1 = new firstfragment();
        f2 = new secondfragment();

        loadFragment(f1,R.id.fragment1);
        loadFragment(f2,R.id.fragment2);
        // loadFragment(f3,R.id.fragment3);

    }
    private void loadFragment(Fragment fragment,int id) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(id, fragment);
        fragmentTransaction.commit(); // save the changes
    }

}

