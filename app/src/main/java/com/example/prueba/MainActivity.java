package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ModelClass> modelClassList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super .onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //boton flotante... agregar Orden de Trabajo.

        FloatingActionButton fab = findViewById(R.id.fab_btn);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FormularioOTActivity.class);
                startActivity(i);
            }
        });


        modelClassList=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        modelClassList.add(new ModelClass("0040321", "Juan Perez", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040322", "Erick Fuentes", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040323", "Brenda Angles", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040324", "René Lozano", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040325", "Juan Perez", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040326", "Erick Fuentes", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040327", "Brenda Angles", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040328", "René Lozano", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040329", "Juan Perez", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040331", "Erick Fuentes", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040332", "Brenda Angles", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040334", "René Lozano", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040335", "Juan Perez", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040336", "Erick Fuentes", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040337", "Brenda Angles", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040338", "René Lozano", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040339", "Juan Perez", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040340", "Erick Fuentes", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040341", "Brenda Angles", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040342", "René Lozano", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040343", "Juan Perez", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040344", "Erick Fuentes", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040345", "Brenda Angles", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));
        modelClassList.add(new ModelClass("0040346", "René Lozano", "V3U568", "12/01/2020 10:11", "15/01/2020 11:15"));


        AdapterClass adapterClass= new AdapterClass(modelClassList);
        recyclerView.setAdapter(adapterClass);
    }
}
