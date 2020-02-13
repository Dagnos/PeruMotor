package com.example.prueba.Views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prueba.Adapter.OrdenTrabajoAdapter;
import com.example.prueba.Interface.APIPerumotor;
import com.example.prueba.Interface.RetrofitClient;
import com.example.prueba.Models.OrdenTrabajo;
import com.example.prueba.Models.Vehiculo;
import com.example.prueba.R;
import com.kyanogen.signatureview.SignatureView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class FormularioOTActivity extends AppCompatActivity {


    String placa = "awh-946";

    ImageView btnLIzquierdo, btnLDerecho, btnMedio, btnFrontal, btnIFrontal, btnIPosterior, btnPosterior;

    Bitmap bitmap;
    Button clear, save;
    SignatureView signatureView;
    String path;
    EditText txtBuscarPlaca;
    TextView txtMarca, txtModelo, txtAño, txtColor, txtPlaca, txtModeloTDP, txtNroMotor, txtNroChasis;
    private static final String IMAGE_DIRECTORY = "/Camera";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_ot_layout);

        //datos vehículos
        txtMarca = (TextView) findViewById(R.id.txtMarcaF);
        txtModelo = (TextView) findViewById(R.id.txtModeloF);
        txtColor = (TextView) findViewById(R.id.txtColorF);
        txtPlaca = (TextView) findViewById(R.id.txtplacaF);
        txtModeloTDP = (TextView) findViewById(R.id.txtModeloTDPF);
        txtNroMotor = (TextView) findViewById(R.id.txtNMotorF);
        txtNroChasis = (TextView) findViewById(R.id.txtNChasisF);
        txtBuscarPlaca = (EditText) findViewById(R.id.txtVehiculoF);




        signatureView = (SignatureView) findViewById(R.id.signature_view);
        clear = (Button) findViewById(R.id.clearf);
        save = (Button) findViewById(R.id.savef);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signatureView.clearCanvas();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmap = signatureView.getSignatureBitmap();
                path = saveImage(bitmap);
                Log.d("TAG", "Estás intentando guardar");

            }

            public String saveImage(Bitmap myBitmap) {
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                File wallpaperDirectory = new File(
                        Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY /*iDyme folder*/);
                // have the object build the directory structure, if needed.
                if (!wallpaperDirectory.exists()) {
                    wallpaperDirectory.mkdirs();
                    Log.d("hhhhh", wallpaperDirectory.toString());
                }

                try {
                    File f = new File(wallpaperDirectory, Calendar.getInstance()
                            .getTimeInMillis() + ".jpg");
                    f.createNewFile();
                    FileOutputStream fo = new FileOutputStream(f);
                    fo.write(bytes.toByteArray());
                    MediaScannerConnection.scanFile(FormularioOTActivity.this,
                            new String[]{f.getPath()},
                            new String[]{"image/jpeg"}, null);
                    fo.close();
                    Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

                    return f.getAbsolutePath();
                } catch (IOException e1) {
                    e1.printStackTrace();
                    Toast.makeText(FormularioOTActivity.this, "Error al cargar imange", Toast.LENGTH_LONG).show();
                }
                return "";

            }
        });


        //tomar foto Lateral Izquierdo

        btnLIzquierdo = findViewById(R.id.imgLIzquierdo);
        btnLIzquierdo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] opciones = {"Tomar Foto", "Cargar Imagen"};
                final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(FormularioOTActivity.this);

                alertOpciones.setTitle("Seleccione una opción:");
                alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (opciones[i].equals("Tomar Foto")) {
                            if (ContextCompat.checkSelfPermission(FormularioOTActivity.this, Manifest.permission.CAMERA)
                                    == PackageManager.PERMISSION_GRANTED) {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, 20);
                            } else {
                                if (!ActivityCompat.shouldShowRequestPermissionRationale(FormularioOTActivity.this,
                                        Manifest.permission.CAMERA)) {
                                    ActivityCompat.requestPermissions(FormularioOTActivity.this,
                                            new String[]{Manifest.permission.CAMERA},
                                            30);
                                }
                            }
                        } else {
                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/");
                            startActivityForResult(intent.createChooser(intent, "Seleccione la Aplicación"), 10);
                        }
                    }
                });

                alertOpciones.show();
            }
        });

        //tomar foto Lateral Derecho

        btnLDerecho = findViewById(R.id.imgLDerecho);
        btnLDerecho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] opciones = {"Tomar Foto", "Cargar Imagen"};
                final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(FormularioOTActivity.this);

                alertOpciones.setTitle("Seleccione una opción:");
                alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (opciones[i].equals("Tomar Foto")) {
                            if (ContextCompat.checkSelfPermission(FormularioOTActivity.this, Manifest.permission.CAMERA)
                                    == PackageManager.PERMISSION_GRANTED) {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, 20);
                            } else {
                                if (!ActivityCompat.shouldShowRequestPermissionRationale(FormularioOTActivity.this,
                                        Manifest.permission.CAMERA)) {
                                    ActivityCompat.requestPermissions(FormularioOTActivity.this,
                                            new String[]{Manifest.permission.CAMERA},
                                            30);
                                }
                            }
                        } else {
                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/");
                            startActivityForResult(intent.createChooser(intent, "Seleccione la Aplicación"), 10);
                        }
                    }
                });

                alertOpciones.show();
            }
        });

        //tomar foto Medio

        btnMedio = findViewById(R.id.imgMedio);
        btnMedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] opciones = {"Tomar Foto", "Cargar Imagen"};
                final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(FormularioOTActivity.this);

                alertOpciones.setTitle("Seleccione una opción:");
                alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (opciones[i].equals("Tomar Foto")) {
                            if (ContextCompat.checkSelfPermission(FormularioOTActivity.this, Manifest.permission.CAMERA)
                                    == PackageManager.PERMISSION_GRANTED) {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, 20);
                            } else {
                                if (!ActivityCompat.shouldShowRequestPermissionRationale(FormularioOTActivity.this,
                                        Manifest.permission.CAMERA)) {
                                    ActivityCompat.requestPermissions(FormularioOTActivity.this,
                                            new String[]{Manifest.permission.CAMERA},
                                            30);
                                }
                            }
                        } else {
                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/");
                            startActivityForResult(intent.createChooser(intent, "Seleccione la Aplicación"), 10);
                        }
                    }
                });

                alertOpciones.show();
            }
        });

        //tomar foto Frontal

        btnFrontal = findViewById(R.id.imgFrontal);
        btnFrontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] opciones = {"Tomar Foto", "Cargar Imagen"};
                final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(FormularioOTActivity.this);

                alertOpciones.setTitle("Seleccione una opción:");
                alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (opciones[i].equals("Tomar Foto")) {
                            if (ContextCompat.checkSelfPermission(FormularioOTActivity.this, Manifest.permission.CAMERA)
                                    == PackageManager.PERMISSION_GRANTED) {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, 20);
                            } else {
                                if (!ActivityCompat.shouldShowRequestPermissionRationale(FormularioOTActivity.this,
                                        Manifest.permission.CAMERA)) {
                                    ActivityCompat.requestPermissions(FormularioOTActivity.this,
                                            new String[]{Manifest.permission.CAMERA},
                                            30);
                                }
                            }
                        } else {
                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/");
                            startActivityForResult(intent.createChooser(intent, "Seleccione la Aplicación"), 10);
                        }
                    }
                });

                alertOpciones.show();
            }
        });

        //tomar foto Interior Frontal

        btnIFrontal = findViewById(R.id.imgIFrontal);
        btnIFrontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] opciones = {"Tomar Foto", "Cargar Imagen"};
                final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(FormularioOTActivity.this);

                alertOpciones.setTitle("Seleccione una opción:");
                alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (opciones[i].equals("Tomar Foto")) {
                            if (ContextCompat.checkSelfPermission(FormularioOTActivity.this, Manifest.permission.CAMERA)
                                    == PackageManager.PERMISSION_GRANTED) {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, 20);
                            } else {
                                if (!ActivityCompat.shouldShowRequestPermissionRationale(FormularioOTActivity.this,
                                        Manifest.permission.CAMERA)) {
                                    ActivityCompat.requestPermissions(FormularioOTActivity.this,
                                            new String[]{Manifest.permission.CAMERA},
                                            30);
                                }
                            }
                        } else {
                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/");
                            startActivityForResult(intent.createChooser(intent, "Seleccione la Aplicación"), 10);
                        }
                    }
                });

                alertOpciones.show();
            }
        });

        //tomar foto Posterior

        btnPosterior = findViewById(R.id.imgPosterior);
        btnPosterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] opciones = {"Tomar Foto", "Cargar Imagen"};
                final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(FormularioOTActivity.this);

                alertOpciones.setTitle("Seleccione una opción:");
                alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (opciones[i].equals("Tomar Foto")) {
                            if (ContextCompat.checkSelfPermission(FormularioOTActivity.this, Manifest.permission.CAMERA)
                                    == PackageManager.PERMISSION_GRANTED) {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, 20);
                            } else {
                                if (!ActivityCompat.shouldShowRequestPermissionRationale(FormularioOTActivity.this,
                                        Manifest.permission.CAMERA)) {
                                    ActivityCompat.requestPermissions(FormularioOTActivity.this,
                                            new String[]{Manifest.permission.CAMERA},
                                            30);
                                }
                            }
                        } else {
                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/");
                            startActivityForResult(intent.createChooser(intent, "Seleccione la Aplicación"), 10);
                        }
                    }
                });

                alertOpciones.show();
            }
        });

        //tomar foto Interior Posterior

        btnIPosterior = findViewById(R.id.imgIPosterior);
        btnIPosterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final CharSequence[] opciones = {"Tomar Foto", "Cargar Imagen"};
                final AlertDialog.Builder alertOpciones = new AlertDialog.Builder(FormularioOTActivity.this);

                alertOpciones.setTitle("Seleccione una opción:");
                alertOpciones.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (opciones[i].equals("Tomar Foto")) {
                            if (ContextCompat.checkSelfPermission(FormularioOTActivity.this, Manifest.permission.CAMERA)
                                    == PackageManager.PERMISSION_GRANTED) {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, 20);
                            } else {
                                if (!ActivityCompat.shouldShowRequestPermissionRationale(FormularioOTActivity.this,
                                        Manifest.permission.CAMERA)) {
                                    ActivityCompat.requestPermissions(FormularioOTActivity.this,
                                            new String[]{Manifest.permission.CAMERA},
                                            30);
                                }
                            }
                        } else {
                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/");
                            startActivityForResult(intent.createChooser(intent, "Seleccione la Aplicación"), 10);
                        }
                    }
                });

                alertOpciones.show();
            }
        });

        getVehiculo();

    }
    private void getVehiculo() {
        Retrofit retrofit=new Retrofit.Builder().baseUrl("http://10.22.3.29:3000").addConverterFactory(GsonConverterFactory.create()).build();
        APIPerumotor apiPerumotor=retrofit.create(APIPerumotor.class);
        Call<Vehiculo> call=apiPerumotor.getVehiculo(placa);




        call.enqueue(new Callback<Vehiculo>() {
            @Override
            public void onResponse(Call<Vehiculo> call, Response<Vehiculo> response) {


                Log.d("idvehiculo", response.body().getIDVEHICULO());
                txtMarca.setText(response.body().getDSMARCA());
                txtModelo.setText(response.body().getDSMODELO());
                txtAño.setText(response.body().getANIO());
                txtColor.setText(response.body().getDSCOLOR());
                txtPlaca.setText(response.body().getPLACA());
                txtModeloTDP.setText(response.body().getCODIGOTDP());
                txtNroMotor.setText(response.body().getNROMOTOR());
                txtNroChasis.setText(response.body().getNROCHASIS());
            }

            @Override
            public void onFailure(Call<Vehiculo> call, Throwable t) {
                Toast.makeText(FormularioOTActivity.this,"Error",Toast.LENGTH_SHORT).show();
                Log.d("error", t.getMessage());
            }
        });
    }

}
