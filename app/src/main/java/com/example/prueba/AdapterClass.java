package com.example.prueba;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViewHolder> {

    private List<ModelClass> modelClassList=new ArrayList<>();

    public AdapterClass(List<ModelClass> modelClassList) {
        this.modelClassList = modelClassList;
    }

    @NonNull
    @Override
    public AdapterClass.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterClass.ViewHolder holder, int position) {
        String ordTrabajo=modelClassList.get(position).getOrdTrabajo();
        String propietario=modelClassList.get(position).getPropietario();
        String placa=modelClassList.get(position).getPlaca();
        String fecha=modelClassList.get(position).getFecha();
        String fechaEntrega=modelClassList.get(position).getFechaEntrega();
        holder.setData(ordTrabajo,propietario,placa,fecha,fechaEntrega);
    }

    @Override
    public int getItemCount() {
        return modelClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView eordTrabajo, epropietario, eplaca, efecha, efechaEntrega;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eordTrabajo=itemView.findViewById(R.id.txtOrdTrabajo);
            epropietario=itemView.findViewById(R.id.txtPropietario);
            eplaca=itemView.findViewById(R.id.txtPlaca);
            efecha=itemView.findViewById(R.id.txtFecha);
            efechaEntrega=itemView.findViewById(R.id.txtFechaEntrega);

        }

        public void setData(String sordTrabajo, String spropietario, String splaca, String sfecha, String sfechaEntrega){
             eordTrabajo.setText(sordTrabajo);
             epropietario.setText(spropietario);
             eplaca.setText(splaca);
             efecha.setText(sfecha);
             efechaEntrega.setText(sfechaEntrega);

        }
    }
}
