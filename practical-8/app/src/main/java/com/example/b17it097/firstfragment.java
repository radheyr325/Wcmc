package com.example.b17it097;;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class firstfragment extends android.app.Fragment {
    ListView simpleList;
    String countryList[] = {"janak","Mohit","Rahul","Manan","Jay","Yash","Darshit","Radhey","Rutvik"};
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_firstfragment, container, false);


        simpleList = (ListView)view.findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(view.getContext(), R.layout.activity_listview, R.id.textView, countryList);
        simpleList.setAdapter(arrayAdapter);




        return view;
    }
}


