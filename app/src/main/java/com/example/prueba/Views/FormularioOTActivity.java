package com.example.prueba.Views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prueba.Interface.APIPerumotor;
import com.example.prueba.Interface.RetrofitClient;
import com.example.prueba.Models.CambioFecha;
import com.example.prueba.Models.Factura;
import com.example.prueba.Models.FormaPago;
import com.example.prueba.Models.Moneda;
import com.example.prueba.Models.NumeroOT;
import com.example.prueba.Models.Periodo;
import com.example.prueba.Models.PostOrdenTrabajo;
import com.example.prueba.Models.ResponseClass;
import com.example.prueba.Models.TipoOrden;
import com.example.prueba.Models.TipoTrabajo;
import com.example.prueba.Models.Vehiculo;
import com.example.prueba.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.R.layout.simple_spinner_item;

public class FormularioOTActivity extends AppCompatActivity  {

    private static final String SHARED_PREFS = "sharedPrefs";
    public static  String tipo_trabajoPDF = null;
    Button btn_buscarVehiculo, btn_buscarFactura;

    ImageView btnLIzquierdo, btnLDerecho, btnMedio, btnFrontal, btnIFrontal, btnIPosterior,
            btnPosterior;

    Button btn_crear, btn_cancelar, btn_firmar, btn_subirfirma, btn_pdf;

    EditText txtBuscarPlaca, txtKilometraje, txtObservacionesF;

    TextView txtMarca, txtModelo, txtAño, txtColor, txtPlaca, txtModeloTDP, txtNroMotor,
            txtNroChasis, txtCliente, txtDireccion, txtBuscarFactura, txtFacturaF, txtRuc,
            txtSerieF, txtNumeroF, txtTCambioF, txtFechaF, txtPeriodoF, txtAsesorF, txtSucursalF,
            txt_tipo_orden, txt_moneda, txt_formapago, txt_tipo_trabajo;

    private Spinner spnFormaPago, spnMoneda, spnTipoOrden, spnTipoTrabajo;

    ArrayList<TipoTrabajo> tipoTrabajos = new ArrayList<>();
    ArrayList<TipoOrden> tipoOrdens = new ArrayList<>();
    ArrayList<Moneda> monedas = new ArrayList<>();
    ArrayList<FormaPago> formaPagos = new ArrayList<>();
    String[] MonedaStr;
    String[] TipoOrdStr;
    String[] TipoTraStr;
    String[] FormaPStr;

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private static final int CAPTURE_REQUEST_CODE= 0;


    private APIPerumotor apiPerumotor;
    private static final int SELECT_REQUEST_CODE=1;
    private static final int SELECCIONAR_REQUEST_CODE=2;
    private static final int SELECCIONAR_REQUEST_CODE1=3;
    private static final int SELECCIONAR_REQUEST_CODE2=4;
    private static final int SELECCIONAR_REQUEST_CODE3=5;
    private static final int SELECCIONAR_REQUEST_CODE4=6;
    private static final int SELECCIONAR_REQUEST_CODE5=7;



    private ContentValues values;
    private Uri imageUri;
    private String imagen;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_ot_layout);

        getSupportActionBar().hide();

        //datos vehículos
        txtMarca = (TextView) findViewById(R.id.txtMarcaF);
        txtModelo = (TextView) findViewById(R.id.txtModeloF);
        txtColor = (TextView) findViewById(R.id.txtColorF);
        txtPlaca = (TextView) findViewById(R.id.txtplacaF);
        txtAño = (TextView) findViewById(R.id.txtAñoF);
        txtModeloTDP = (TextView) findViewById(R.id.txtModeloTDPF);
        txtNroMotor = (TextView) findViewById(R.id.txtNMotorF);
        txtNroChasis = (TextView) findViewById(R.id.txtNChasisF);
        txtBuscarPlaca = (EditText) findViewById(R.id.txtVehiculoF);
        txtCliente = (TextView) findViewById(R.id.txtPropietario);
        txtDireccion = (TextView) findViewById(R.id.txtDireccionF);
        txtBuscarFactura = (EditText) findViewById(R.id.txtFacturaAF);
        txtFacturaF = (TextView) findViewById(R.id.txtFacturaF);
        txtRuc = (TextView) findViewById(R.id.txtRucF);
        txtSerieF = (TextView) findViewById(R.id.txtSerieF);
        txtNumeroF = (TextView) findViewById(R.id.txtOrdenTrabajoF);
        txtTCambioF = (TextView) findViewById(R.id.txtTCambioF);
        txtFechaF = (TextView) findViewById(R.id.txtFechaF);
        txtPeriodoF = (TextView) findViewById(R.id.txtPeriodoF);
        txtAsesorF = (TextView) findViewById(R.id.txtAsesorF);
        txtSucursalF = (TextView) findViewById(R.id.txtPEmisionF);
        txt_tipo_orden = (TextView) findViewById(R.id.txt_tipo_orden);
        txt_moneda = (TextView) findViewById(R.id.txt_moneda);
        txt_formapago = (TextView) findViewById(R.id.txt_formapago);
        txt_tipo_trabajo = (TextView) findViewById(R.id.txt_tipo_trabajo);
        txtKilometraje = (EditText) findViewById(R.id.txtKilometrajeF);
        txtObservacionesF = (EditText) findViewById(R.id.txtObservacionesF);


        //recibiendo dato asesor
        String datoasesor = getIntent().getStringExtra("idasesor2");
        txtAsesorF.setText(datoasesor.trim());
        //fin recibiendo datos asesor

        spnFormaPago = (Spinner) findViewById(R.id.spnFPagoF);
        spnFormaPago.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String id_formaPago = formaPagos.get(position).getIDFPAGO();
                txt_formapago.setText(id_formaPago);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnTipoOrden = (Spinner) findViewById(R.id.spn_tipo_orden);
        spnTipoOrden.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String id_tipoorden = tipoOrdens.get(position).getId();
                txt_tipo_orden.setText(id_tipoorden);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnMoneda = (Spinner) findViewById(R.id.spn_moneda);
        spnMoneda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String id_moneda = monedas.get(position).getIDMONEDA();
                txt_moneda.setText(id_moneda);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnTipoTrabajo = (Spinner) findViewById(R.id.spn_tipo_trabajo);
        spnTipoTrabajo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String id_tipotrabajo = tipoTrabajos.get(position).getIDACTIVIDAD();
                tipo_trabajoPDF = tipoTrabajos.get(position).getDESCRIPCION();
                txt_tipo_trabajo.setText(id_tipotrabajo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btn_buscarVehiculo = (Button) findViewById(R.id.btn_buscarVehiculo);
        btn_subirfirma = (Button) findViewById(R.id.btn_subirfirmar);
        btn_subirfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckPermission()) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, SELECCIONAR_REQUEST_CODE5);
                }
            }
        });

        btn_buscarFactura = (Button) findViewById(R.id.btn_buscarFactura);
        btn_crear = (Button) findViewById(R.id.btn_crear);


        //ventana emergente para cancelar orden de trabajo
        btn_cancelar = (Button) findViewById(R.id.btn_cancelar);
        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FormularioOTActivity.this);
                builder.setMessage("¿Seguro qué quieres cancelar la orden de trabajo? Piénsalo ...");
                builder.setTitle("Cancelar");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        btn_firmar = (Button) findViewById(R.id.btn_firmar);
        btn_firmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FormularioOTActivity.this, SignatureActivity.class);
                startActivity(i);
            }
        });

        btn_pdf = (Button) findViewById(R.id.btn_pdf);
        btn_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FormularioOTActivity.this, PdfActivity.class);
                i.putExtra("orden_trabajo", txtNumeroF.getText().toString());
                i.putExtra("facturaa", txtFacturaF.getText().toString());
                i.putExtra("propietario", txtCliente.getText().toString());
                i.putExtra("direccion", txtDireccion.getText().toString());
                i.putExtra("fecha", txtFechaF.getText().toString());
                i.putExtra("ruc", txtRuc.getText().toString());
                i.putExtra("asesor", txtAsesorF.getText().toString());
                i.putExtra("marca", txtMarca.getText().toString());
                i.putExtra("modelo", txtModelo.getText().toString());
                i.putExtra("modelotdp", txtModeloTDP.getText().toString());
                i.putExtra("año", txtAño.getText().toString());
                i.putExtra("color", txtColor.getText().toString());
                i.putExtra("placa", txtPlaca.getText().toString());
                i.putExtra("nromotor", txtNroMotor.getText().toString());
                i.putExtra("nrochasis", txtNroChasis.getText().toString());
                i.putExtra("kilometraje", txtKilometraje.getText().toString());
                i.putExtra("tipotrabajo", tipo_trabajoPDF);
                i.putExtra("observaciones", txtObservacionesF.getText().toString());
                startActivity(i);
            }
        });

        //validar campos vacios
        if(txtKilometraje.getText().toString().isEmpty()){
            txtKilometraje.setError("complete");
        }if (txtObservacionesF.getText().toString().isEmpty()){
            txtObservacionesF.setError("complete");
        }
        //fin validar campos vacios

        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.22.3.29:3000").client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
        apiPerumotor = retrofit.create(APIPerumotor.class);

        //tomar foto
        btnLIzquierdo = (ImageView) findViewById(R.id.imgLIzquierdo);
        btnLIzquierdo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (CheckPermission()) {
                    values = new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE, "MyPicture");
                    values.put(MediaStore.Images.Media.DESCRIPTION, "Photo taken on " + System.currentTimeMillis());
                    imageUri = getContentResolver().insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, CAPTURE_REQUEST_CODE);
                }
            }
        });

        //tomar foto2
        btnLDerecho = (ImageView) findViewById(R.id.imgLDerecho);
        btnLDerecho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckPermission()) {
                    values = new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE, "MyPicture");
                    values.put(MediaStore.Images.Media.DESCRIPTION, "Photo taken on " + System.currentTimeMillis());
                    imageUri = getContentResolver().insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, SELECT_REQUEST_CODE);
                }
            }
        });

        //tomar foto3
        btnMedio = (ImageView) findViewById(R.id.imgMedio);
        btnMedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckPermission()) {
                    values = new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE, "MyPicture");
                    values.put(MediaStore.Images.Media.DESCRIPTION, "Photo taken on " + System.currentTimeMillis());
                    imageUri = getContentResolver().insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, SELECCIONAR_REQUEST_CODE);
                }
            }
        });
        //tomar foto4
        btnFrontal = (ImageView) findViewById(R.id.imgFrontal);
        btnFrontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckPermission()) {
                    values = new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE, "MyPicture");
                    values.put(MediaStore.Images.Media.DESCRIPTION, "Photo taken on " + System.currentTimeMillis());
                    imageUri = getContentResolver().insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, SELECCIONAR_REQUEST_CODE1);
                }
            }
        });

        //tomar foto5
        btnPosterior = (ImageView) findViewById(R.id.imgPosterior);
        btnPosterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckPermission()) {
                    values = new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE, "MyPicture");
                    values.put(MediaStore.Images.Media.DESCRIPTION, "Photo taken on " + System.currentTimeMillis());
                    imageUri = getContentResolver().insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, SELECCIONAR_REQUEST_CODE2);
                }
            }
        });

        //tomar foto6
        btnIFrontal = (ImageView) findViewById(R.id.imgIFrontal);
        btnIFrontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckPermission()) {
                    values = new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE, "MyPicture");
                    values.put(MediaStore.Images.Media.DESCRIPTION, "Photo taken on " + System.currentTimeMillis());
                    imageUri = getContentResolver().insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, SELECCIONAR_REQUEST_CODE3);
                }
            }
        });


        //tomar foto7
        btnIPosterior = (ImageView) findViewById(R.id.imgIPosterior);
        btnIPosterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CheckPermission()) {
                    //Intent seleccionar = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //startActivityForResult(seleccionar, SELECCIONAR_REQUEST_CODE4);

                    values = new ContentValues();
                    values.put(MediaStore.Images.Media.TITLE, "MyPicture");
                    values.put(MediaStore.Images.Media.DESCRIPTION, "Photo taken on " + System.currentTimeMillis());
                    imageUri = getContentResolver().insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, SELECCIONAR_REQUEST_CODE4);
                }
            }
        });




        getVehiculo();
        getTipoTrabajo();
        getFactura();
        getNumeroOT();
        getCambioFecha();
        getPeriodo();
        createOrdenTrabajo();
        llegadaDatos();
        getFormaPago();
        getTipoOrden();
        getMoneda();

    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
    public void getFormaPago() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.22.3.29:3000").client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
        APIPerumotor apiPerumotor = retrofit.create(APIPerumotor.class);
        Call<ArrayList<FormaPago>> call = apiPerumotor.getFormaPago();

        call.enqueue(new Callback<ArrayList<FormaPago>>() {
            @Override
            public void onResponse(Call<ArrayList<FormaPago>> call, Response<ArrayList<FormaPago>> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());
                        int numFormaPagos = response.body().size();

                        FormaPStr = new String[numFormaPagos];

                        for (int i = 0; i < response.body().size(); i++) {
                            formaPagos.add(response.body().get(i));
                            FormaPStr[i] = response.body().get(i).getDESCRIPCION();
                        }

                            ArrayAdapter<FormaPago> spinnerArrayAdapter = new ArrayAdapter<>(FormularioOTActivity.this, R.layout.spinner_layout_perumotor, formaPagos);
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnFormaPago.setAdapter(spinnerArrayAdapter);


                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<FormaPago>> call, Throwable t) {
                Toast.makeText(FormularioOTActivity.this, "No hay respuesta", Toast.LENGTH_LONG).show();
                Log.i("err", t.getMessage());
            }
        });
    }

    public void getTipoOrden() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.22.3.29:3000").client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
        APIPerumotor apiPerumotor = retrofit.create(APIPerumotor.class);
        Call<ArrayList<TipoOrden>> call = apiPerumotor.getTipoOrden();

        call.enqueue(new Callback<ArrayList<TipoOrden>>() {
            @Override
            public void onResponse(Call<ArrayList<TipoOrden>> call, Response<ArrayList<TipoOrden>> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());
                        int numTipoOrdens = response.body().size();

                        TipoOrdStr = new String[numTipoOrdens];

                        for (int i = 0; i < response.body().size(); i++) {
                            tipoOrdens.add(response.body().get(i));
                            TipoOrdStr[i] = response.body().get(i).getTipoOrden();
                        }

                        ArrayAdapter<TipoOrden> spinnerArrayAdapter = new ArrayAdapter<>(FormularioOTActivity.this, R.layout.spinner_layout_perumotor, tipoOrdens);
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnTipoOrden.setAdapter(spinnerArrayAdapter);


                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TipoOrden>> call, Throwable t) {
                Toast.makeText(FormularioOTActivity.this, "No hay respuesta", Toast.LENGTH_LONG).show();
                Log.i("err", t.getMessage());
            }
        });
    }

    public void getMoneda() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.22.3.29:3000").client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
        APIPerumotor apiPerumotor = retrofit.create(APIPerumotor.class);
        Call<ArrayList<Moneda>> call = apiPerumotor.getMoneda();

        call.enqueue(new Callback<ArrayList<Moneda>>() {
            @Override
            public void onResponse(Call<ArrayList<Moneda>> call, Response<ArrayList<Moneda>> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());
                        int numMonedas = response.body().size();

                        MonedaStr = new String[numMonedas];

                        for (int i = 0; i < response.body().size(); i++) {
                            monedas.add(response.body().get(i));
                            MonedaStr[i] = response.body().get(i).getDESCRIPCION();
                        }

                        ArrayAdapter<Moneda> spinnerArrayAdapter = new ArrayAdapter<>(FormularioOTActivity.this, R.layout.spinner_layout_perumotor, monedas);
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnMoneda.setAdapter(spinnerArrayAdapter);


                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Moneda>> call, Throwable t) {
                Toast.makeText(FormularioOTActivity.this, "No hay respuesta", Toast.LENGTH_LONG).show();
                Log.i("err", t.getMessage());
            }
        });

    }

    public void getTipoTrabajo() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.22.3.29:3000").client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
        APIPerumotor apiPerumotor = retrofit.create(APIPerumotor.class);
        Call<ArrayList<TipoTrabajo>> call = apiPerumotor.getTipoTrabajo();

        call.enqueue(new Callback<ArrayList<TipoTrabajo>>() {
            @Override
            public void onResponse(Call<ArrayList<TipoTrabajo>> call, Response<ArrayList<TipoTrabajo>> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());
                        int numTipoTrabajo = response.body().size();

                        TipoTraStr = new String[numTipoTrabajo];

                        for (int i = 0; i < response.body().size(); i++) {
                            tipoTrabajos.add(response.body().get(i));
                            TipoTraStr[i] = response.body().get(i).getDESCRIPCION();
                        }

                        ArrayAdapter<TipoTrabajo> spinnerArrayAdapter = new ArrayAdapter<>(FormularioOTActivity.this, R.layout.spinner_layout_perumotor, tipoTrabajos);
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spnTipoTrabajo.setAdapter(spinnerArrayAdapter);


                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TipoTrabajo>> call, Throwable t) {
                Toast.makeText(FormularioOTActivity.this, "No hay respuesta", Toast.LENGTH_LONG).show();
                Log.i("err", t.getMessage());
            }
        });
    }

    public void llegadaDatos() {
        String id_tipo_orden = getIntent().getStringExtra("id_tipo_orden");
        String id_moneda = getIntent().getStringExtra("id_moneda");
        SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE).edit();
        editor.putString("id_tipo_orden", id_tipo_orden);
        editor.putString("id_moneda", id_moneda);
        editor.apply();


    }



    final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case CAPTURE_REQUEST_CODE: {
                if (resultCode == RESULT_OK) {

                    try {
                        Bitmap bitmap =  MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                        ImageUpload(bitmap);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
            break;
            case SELECT_REQUEST_CODE: {
                if (resultCode == RESULT_OK) {

                    try {
                        Bitmap bitmap =  MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                        ImageUpload2(bitmap);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
            break;
            case SELECCIONAR_REQUEST_CODE: {
                if (resultCode == RESULT_OK) {

                    try {
                        Bitmap bitmap =  MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                        ImageUpload3(bitmap);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
            break;
            case SELECCIONAR_REQUEST_CODE1: {
                if (resultCode == RESULT_OK) {

                    try {
                        Bitmap bitmap =  MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                        ImageUpload4(bitmap);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
            break;
            case SELECCIONAR_REQUEST_CODE2: {
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap =  MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                        ImageUpload5(bitmap);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
            break;
            case SELECCIONAR_REQUEST_CODE3: {
                if (resultCode == RESULT_OK) {

                    try {
                        Bitmap bitmap =  MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                        ImageUpload6(bitmap);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
            break;
            case SELECCIONAR_REQUEST_CODE4: {
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap =  MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                        ImageUpload7(bitmap);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
            break;
            case SELECCIONAR_REQUEST_CODE5: {
                if (resultCode == RESULT_OK) {


                    try {
                        Uri ImageUri = data.getData();
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), ImageUri);
                        ImageFirma(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            break;
        }
    }

    private void ImageUpload(Bitmap bitmap) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);

        String image = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

        Call<ResponseClass> call = apiPerumotor.UploadImage(txtNumeroF.getText().toString().trim(),image);
        call.enqueue(new Callback<ResponseClass>() {
            @Override
            public void onResponse(Call<ResponseClass> call, Response<ResponseClass> response) {
                Toast.makeText(FormularioOTActivity.this, "AGREGADO", Toast.LENGTH_SHORT).show();
                Log.d("Exito1:", response.message());
                btnLIzquierdo.setImageResource(R.drawable.imagen_subida);

            }

            @Override
            public void onFailure(Call<ResponseClass> call, Throwable t) {
                Toast.makeText(FormularioOTActivity.this, "Error", Toast.LENGTH_SHORT).show();
                String err = (t.getMessage()==null)?"failed":t.getMessage();
                Log.e("err2:",err);


            }
        });
    }

    private void ImageUpload2(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);

        String image = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

        Call<ResponseClass> call = apiPerumotor.UploadImage2(txtNumeroF.getText().toString().trim(),image);
        call.enqueue(new Callback<ResponseClass>() {
            @Override
            public void onResponse(Call<ResponseClass> call, Response<ResponseClass> response) {
                ResponseClass responseClass = response.body();
                Toast.makeText(FormularioOTActivity.this, "AGREGADO"+responseClass.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Exito2:", response.message());
                btnLDerecho.setImageResource(R.drawable.imagen_subida);


            }

            @Override
            public void onFailure(Call<ResponseClass> call, Throwable t) {
                Toast.makeText(FormularioOTActivity.this, "Imagen Cargada", Toast.LENGTH_SHORT).show();
                String err = (t.getMessage()==null)?"failed":t.getMessage();
                Log.e("err2:",err);


            }
        });
    }

    private void ImageUpload3(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);

        String image = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

        Call<ResponseClass> call = apiPerumotor.UploadImage3(txtNumeroF.getText().toString().trim(),image);
        call.enqueue(new Callback<ResponseClass>() {
            @Override
            public void onResponse(Call<ResponseClass> call, Response<ResponseClass> response) {
                Toast.makeText(FormularioOTActivity.this, "AGREGADO", Toast.LENGTH_SHORT).show();
                Log.d("Exito3:", response.message());
                btnMedio.setImageResource(R.drawable.imagen_subida);


            }

            @Override
            public void onFailure(Call<ResponseClass> call, Throwable t) {
                Toast.makeText(FormularioOTActivity.this, "Error al cargar imagen.", Toast.LENGTH_SHORT).show();
                String err = (t.getMessage()==null)?"failed":t.getMessage();
                Log.e("err2:",err);

            }
        });
    }

    private void ImageUpload4(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);

        String image = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

        Call<ResponseClass> call = apiPerumotor.UploadImage4(txtNumeroF.getText().toString().trim(),image);
        call.enqueue(new Callback<ResponseClass>() {
            @Override
            public void onResponse(Call<ResponseClass> call, Response<ResponseClass> response) {
                Toast.makeText(FormularioOTActivity.this, "AGREGADO", Toast.LENGTH_SHORT).show();
                Log.d("Exito4:", response.message());
                btnFrontal.setImageResource(R.drawable.imagen_subida);


            }

            @Override
            public void onFailure(Call<ResponseClass> call, Throwable t) {
                Toast.makeText(FormularioOTActivity.this, "Error al cargar imagen.", Toast.LENGTH_SHORT).show();
                String err = (t.getMessage()==null)?"failed":t.getMessage();
                Log.e("err2:",err);
            }
        });
    }

    private void ImageUpload5(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);

        String image = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

        Call<ResponseClass> call = apiPerumotor.UploadImage5(txtNumeroF.getText().toString().trim(),image);
        call.enqueue(new Callback<ResponseClass>() {
            @Override
            public void onResponse(Call<ResponseClass> call, Response<ResponseClass> response) {
                Toast.makeText(FormularioOTActivity.this, "AGREGADO", Toast.LENGTH_SHORT).show();
                Log.d("Exito5:", response.message());
                btnPosterior.setImageResource(R.drawable.imagen_subida);


            }

            @Override
            public void onFailure(Call<ResponseClass> call, Throwable t) {
                Toast.makeText(FormularioOTActivity.this, "Error al cargar imagen.", Toast.LENGTH_SHORT).show();
                String err = (t.getMessage()==null)?"failed":t.getMessage();
                Log.e("err2:",err);
            }
        });
    }

    private void ImageUpload6(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);

        String image = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

        Call<ResponseClass> call = apiPerumotor.UploadImage6(txtNumeroF.getText().toString().trim(),image);
        call.enqueue(new Callback<ResponseClass>() {
            @Override
            public void onResponse(Call<ResponseClass> call, Response<ResponseClass> response) {
                Toast.makeText(FormularioOTActivity.this, "AGREGADO", Toast.LENGTH_SHORT).show();
                Log.d("Exito6:", response.message());
                btnIFrontal.setImageResource(R.drawable.imagen_subida);


            }

            @Override
            public void onFailure(Call<ResponseClass> call, Throwable t) {
                Toast.makeText(FormularioOTActivity.this, "Error al cargar imagen.", Toast.LENGTH_SHORT).show();
                String err = (t.getMessage()==null)?"failed":t.getMessage();
                Log.e("err2:",err);
            }
        });
    }

    private void ImageUpload7(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream);

        String image = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

        Call<ResponseClass> call = apiPerumotor.UploadImage7(txtNumeroF.getText().toString().trim(),image);
        call.enqueue(new Callback<ResponseClass>() {
            @Override
            public void onResponse(Call<ResponseClass> call, Response<ResponseClass> response) {
                Toast.makeText(FormularioOTActivity.this, "AGREGADO", Toast.LENGTH_SHORT).show();
                Log.d("Exito7:", response.message());
                btnIPosterior.setImageResource(R.drawable.imagen_subida);


            }

            @Override
            public void onFailure(Call<ResponseClass> call, Throwable t) {
                Toast.makeText(FormularioOTActivity.this, "Error al cargar imagen.", Toast.LENGTH_SHORT).show();
                String err = (t.getMessage()==null)?"failed":t.getMessage();
                Log.e("err2:",err);
            }
        });
    }

    private void ImageFirma(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        String image = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);

        Call<ResponseClass> call = apiPerumotor.UploadFirma(txtNumeroF.getText().toString().trim(),image);
        call.enqueue(new Callback<ResponseClass>() {
            @Override
            public void onResponse(Call<ResponseClass> call, Response<ResponseClass> response) {
                Toast.makeText(FormularioOTActivity.this, "AGREGADO", Toast.LENGTH_SHORT).show();
                Log.d("Exito:", response.message());
            }

            @Override
            public void onFailure(Call<ResponseClass> call, Throwable t) {
                Toast.makeText(FormularioOTActivity.this, "Firma agregada.", Toast.LENGTH_SHORT).show();
                String err = (t.getMessage()==null)?"failed":t.getMessage();
                Log.e("err2:",err);
            }
        });

    }

    public boolean CheckPermission() {
        if (ContextCompat.checkSelfPermission(FormularioOTActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(FormularioOTActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(FormularioOTActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(FormularioOTActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(FormularioOTActivity.this,
                    Manifest.permission.CAMERA) || ActivityCompat.shouldShowRequestPermissionRationale(FormularioOTActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                new AlertDialog.Builder(FormularioOTActivity.this)
                        .setTitle("Permission")
                        .setMessage("Please accept the permissions")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(FormularioOTActivity.this,
                                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        MY_PERMISSIONS_REQUEST_LOCATION);


                                startActivity(new Intent(FormularioOTActivity
                                        .this, MainActivity.class));
                                FormularioOTActivity.this.overridePendingTransition(0, 0);
                            }
                        })
                        .create()
                        .show();


            } else {
                ActivityCompat.requestPermissions(FormularioOTActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }

            return false;
        } else {

            return true;
        }
    }

    private void createOrdenTrabajo() {
        btn_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validar campos vacios
                if(txtKilometraje.getText().toString().isEmpty()){
                    txtKilometraje.setError("complete");
                    txtKilometraje.requestFocus();
                    return;
                }if (txtObservacionesF.getText().toString().isEmpty()){
                    txtObservacionesF.setError("complete");
                    txtObservacionesF.requestFocus();
                    return;
                }
                //fin validar campos vacios
                Toast.makeText(FormularioOTActivity.this, "Creando Orden de Trabajo ...", Toast.LENGTH_LONG).show();
                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.22.3.29:3000").client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
                APIPerumotor apiPerumotor = retrofit.create(APIPerumotor.class);
                Call<PostOrdenTrabajo> call = apiPerumotor.createOrdenTrabajo(txtPeriodoF.getText().toString().trim(),txtSucursalF.getText().toString().trim(),
                        txtFechaF.getText().toString().trim(), txt_moneda.getText().toString(), txtAsesorF.getText().toString().trim(),
                        txtSerieF.getText().toString().trim(), txtTCambioF.getText().toString(), txtFacturaF.getText().toString(),
                        txtPlaca.getText().toString().trim(), txtKilometraje.getText().toString(), txt_formapago.getText().toString(),
                        txt_tipo_trabajo.getText().toString(), txt_tipo_orden.getText().toString());
                call.enqueue(new Callback<PostOrdenTrabajo>() {
                    @Override
                    public void onResponse(Call<PostOrdenTrabajo> call, Response<PostOrdenTrabajo> response) {
                        Toast.makeText(FormularioOTActivity.this, "Creado", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFailure(Call<PostOrdenTrabajo> call, Throwable t) {
                        Toast.makeText(FormularioOTActivity.this, "Error al crear Orden de Trabajo. Intente de nuevo por favor.", Toast.LENGTH_LONG).show();

                    }
                });
            }
        });


    }


    private void getNumeroOT() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.22.3.29:3000").addConverterFactory(GsonConverterFactory.create()).build();
        APIPerumotor apiPerumotor = retrofit.create(APIPerumotor.class);
        Call<List<NumeroOT>> call = apiPerumotor.getNumeroOT();

        call.enqueue(new Callback<List<NumeroOT>>() {
            @Override
            public void onResponse(Call<List<NumeroOT>> call, Response<List<NumeroOT>> response) {

                List<NumeroOT> numeroOTS = response.body();


                for (NumeroOT numeroOT : numeroOTS) {

                    String content1 = numeroOT.getSERIE();
                    String content2 = numeroOT.getNUMERO();

                    txtSerieF.append(content1);
                    txtNumeroF.append(content2);
                }

            }

            @Override
            public void onFailure(Call<List<NumeroOT>> call, Throwable t) {
                Toast.makeText(FormularioOTActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Log.d("error", t.getMessage());
            }
        });
    }

    private void getCambioFecha() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.22.3.29:3000").addConverterFactory(GsonConverterFactory.create()).build();
        APIPerumotor apiPerumotor = retrofit.create(APIPerumotor.class);
        Call<List<CambioFecha>> call = apiPerumotor.getCambioFecha();

        call.enqueue(new Callback<List<CambioFecha>>() {
            @Override
            public void onResponse(Call<List<CambioFecha>> call, Response<List<CambioFecha>> response) {

                List<CambioFecha> cambioFechas = response.body();


                for (CambioFecha cambioFecha : cambioFechas) {

                    String content1 = cambioFecha.getT_VENTA();
                    String content2 = cambioFecha.getFECHA2();

                    txtTCambioF.append(content1);
                    txtFechaF.append(content2);
                }

            }

            @Override
            public void onFailure(Call<List<CambioFecha>> call, Throwable t) {
                Toast.makeText(FormularioOTActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Log.d("error", t.getMessage());
            }
        });
    }

    private void getPeriodo() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.22.3.29:3000").addConverterFactory(GsonConverterFactory.create()).build();
        APIPerumotor apiPerumotor = retrofit.create(APIPerumotor.class);
        Call<List<Periodo>> call = apiPerumotor.getPeriodo();

        call.enqueue(new Callback<List<Periodo>>() {
            @Override
            public void onResponse(Call<List<Periodo>> call, Response<List<Periodo>> response) {

                List<Periodo> periodos = response.body();


                for (Periodo periodo : periodos) {

                    String content1 = periodo.getFECHA();

                    txtPeriodoF.append(content1);
                }

            }

            @Override
            public void onFailure(Call<List<Periodo>> call, Throwable t) {
                Toast.makeText(FormularioOTActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Log.d("error", t.getMessage());
            }
        });
    }

    private void getVehiculo() {


            btn_buscarVehiculo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txtMarca.setText("");
                    txtModelo.setText("");
                    txtAño.setText("");
                    txtColor.setText("");
                    txtPlaca.setText("");
                    txtModeloTDP.setText("");
                    txtNroMotor.setText("");
                    txtNroChasis.setText("");
                    txtCliente.setText("");


                    Toast.makeText(FormularioOTActivity.this, "Buscando vehículo ...", Toast.LENGTH_SHORT).show();
                    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.22.3.29:3000").client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
                    APIPerumotor apiPerumotor = retrofit.create(APIPerumotor.class);
                    Call<List<Vehiculo>> call = apiPerumotor.getVehiculo(txtBuscarPlaca.getText().toString().trim());


                    call.enqueue(new Callback<List<Vehiculo>>() {
                        @Override
                        public void onResponse(Call<List<Vehiculo>> call, Response<List<Vehiculo>> response) {


                            if (response.body().size() != 0) {
                                    List<Vehiculo> vehiculos = response.body();


                                    for (Vehiculo vehiculo : vehiculos) {

                                        String content1 = vehiculo.getDSMARCA();
                                        String content2 = vehiculo.getDSMODELO();
                                        String año = vehiculo.getANIO();
                                        String content4 = vehiculo.getDSCOLOR();
                                        String content5 = vehiculo.getPLACA();
                                        String content6 = vehiculo.getCODIGOTDP();
                                        String content7 = vehiculo.getNROMOTOR();
                                        String content8 = vehiculo.getNROCHASIS();
                                        String content9 = vehiculo.getRAZON_SOCIAL();


                                        txtMarca.append(content1);
                                        txtModelo.append(content2);
                                        txtAño.append(año);
                                        txtColor.append(content4);
                                        txtPlaca.append(content5);
                                        txtModeloTDP.append(content6);
                                        txtNroMotor.append(content7);
                                        txtNroChasis.append(content8);
                                        txtCliente.append(content9);

                                    }
                                } else {

                                AlertDialog.Builder builder = new AlertDialog.Builder(FormularioOTActivity.this);
                                builder.setMessage("No se encontró el vehículo. ¿Desea agregarlo?");
                                builder.setTitle("Cancelar");
                                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(FormularioOTActivity.this, "Creando vehículo.", Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(FormularioOTActivity.this, CreateVehiculoActivity.class);
                                        startActivity(i);
                                    }
                                });
                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog dialog = builder.create();
                                dialog.show();

                                }






                        }

                        @Override
                        public void onFailure(Call<List<Vehiculo>> call, Throwable t) {
                            Toast.makeText(FormularioOTActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            String err = (t.getMessage()==null)?"failed":t.getMessage();
                            Log.e("err2:",err);
                        }
                    });


                }
            });
    }

    private void getFactura() {
        btn_buscarFactura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtFacturaF.setText("");
                txtRuc.setText("");
                txtDireccion.setText("");
                Toast.makeText(FormularioOTActivity.this, "Buscando ...", Toast.LENGTH_SHORT).show();
                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.22.3.29:3000").addConverterFactory(GsonConverterFactory.create()).build();
                APIPerumotor apiPerumotor = retrofit.create(APIPerumotor.class);
                Call<List<Factura>> call = apiPerumotor.getFactura(txtBuscarFactura.getText().toString().trim());


                call.enqueue(new Callback<List<Factura>>() {
                    @Override
                    public void onResponse(Call<List<Factura>> call, Response<List<Factura>> response) {

                        List<Factura> facturas = response.body();


                        for (Factura factura : facturas) {

                            String content1 = factura.getIDCLIEPROV();
                            String content2 = factura.getRAZON_SOCIAL();
                            String direccion = factura.getDIRECCION();

                            txtFacturaF.append(content1);
                            txtRuc.append(content2);
                            txtDireccion.append(direccion);

                        }

                    }

                    @Override
                    public void onFailure(Call<List<Factura>> call, Throwable t) {
                        Toast.makeText(FormularioOTActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        String err = (t.getMessage()==null)?"failed":t.getMessage();
                        Log.e("err2:",err);
                    }
                });

            }
        });
    }


}
