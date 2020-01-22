package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ModelClass> modelClassList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super .onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        modelClassList=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        modelClassList.add(new ModelClass("0040321", "Juan Perez", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040321", "Erick Fuentes", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040321", "Brenda Angles", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040321", "René Lozano", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040321", "Juan Perez", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040321", "Erick Fuentes", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040321", "Brenda Angles", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040321", "René Lozano", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040321", "Juan Perez", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040321", "Erick Fuentes", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040321", "Brenda Angles", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040321", "René Lozano", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040321", "Juan Perez", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040321", "Erick Fuentes", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040321", "Brenda Angles", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040321", "René Lozano", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040321", "Juan Perez", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040321", "Erick Fuentes", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040321", "Brenda Angles", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040321", "René Lozano", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040321", "Juan Perez", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040321", "Erick Fuentes", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040321", "Brenda Angles", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040321", "René Lozano", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));


        AdapterClass adapterClass= new AdapterClass(modelClassList);
        recyclerView.setAdapter(adapterClass);
    }
}
