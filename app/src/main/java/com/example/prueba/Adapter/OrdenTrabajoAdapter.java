package com.example.prueba.Adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prueba.Models.OrdenTrabajo;
import com.example.prueba.R;

import java.util.ArrayList;

public class OrdenTrabajoAdapter extends RecyclerView.Adapter<OrdenTrabajoAdapter.ViewHolder> {

    private ArrayList<OrdenTrabajo> ordenTrabajos= new ArrayList<>();
    private Context context;

    public OrdenTrabajoAdapter(Context context, ArrayList<OrdenTrabajo> ordenTrabajos) {
        this.ordenTrabajos=ordenTrabajos;
        this.context=context;

    }

    @NonNull
    @Override
    public OrdenTrabajoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_layout,viewGroup, false);
        return new OrdenTrabajoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdenTrabajoAdapter.ViewHolder viewHolder, int i) {
        viewHolder.numero.setText(ordenTrabajos.get(i).getNUMERO());
        viewHolder.nombre.setText(ordenTrabajos.get(i).getRAZON_SOCIAL());
        viewHolder.placa.setText(ordenTrabajos.get(i).getIDVEHICULO());
        viewHolder.fechaini.setText(ordenTrabajos.get(i).getFECHAINICIO());
        viewHolder.fechafin.setText(ordenTrabajos.get(i).getFECHAFINAL());
    }

    @Override
    public int getItemCount() {
        if (ordenTrabajos != null)
        return ordenTrabajos.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView numero, placa, nombre, fechaini, fechafin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            numero = (TextView)itemView.findViewById(R.id.txtOrdTrabajo);
            placa = (TextView)itemView.findViewById(R.id.txtPlaca);
            nombre = (TextView)itemView.findViewById(R.id.txtPropietario);
            fechaini = (TextView)itemView.findViewById(R.id.txtFecha);
            fechafin = (TextView)itemView.findViewById(R.id.txtFechaEntrega);
        }
    }
}
