package com.example.prueba;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FormularioOTActivity extends AppCompatActivity {

    ImageView btnLIzquierdo, btnLDerecho, btnMedio, btnFrontal, btnIFrontal, btnIPosterior, btnPosterior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_ot_layout);

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
                        if (opciones[i].equals("Tomar Foto")){
                            if (ContextCompat.checkSelfPermission(FormularioOTActivity.this, Manifest.permission.CAMERA)
                                    == PackageManager.PERMISSION_GRANTED) {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent,20);
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
                            startActivityForResult(intent.createChooser(intent,"Seleccione la Aplicación"),10);
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
                        if (opciones[i].equals("Tomar Foto")){
                            if (ContextCompat.checkSelfPermission(FormularioOTActivity.this, Manifest.permission.CAMERA)
                                    == PackageManager.PERMISSION_GRANTED) {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent,20);
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
                            startActivityForResult(intent.createChooser(intent,"Seleccione la Aplicación"),10);
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
                        if (opciones[i].equals("Tomar Foto")){
                            if (ContextCompat.checkSelfPermission(FormularioOTActivity.this, Manifest.permission.CAMERA)
                                    == PackageManager.PERMISSION_GRANTED) {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent,20);
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
                            startActivityForResult(intent.createChooser(intent,"Seleccione la Aplicación"),10);
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
                        if (opciones[i].equals("Tomar Foto")){
                            if (ContextCompat.checkSelfPermission(FormularioOTActivity.this, Manifest.permission.CAMERA)
                                    == PackageManager.PERMISSION_GRANTED) {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent,20);
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
                            startActivityForResult(intent.createChooser(intent,"Seleccione la Aplicación"),10);
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
                        if (opciones[i].equals("Tomar Foto")){
                            if (ContextCompat.checkSelfPermission(FormularioOTActivity.this, Manifest.permission.CAMERA)
                                    == PackageManager.PERMISSION_GRANTED) {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent,20);
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
                            startActivityForResult(intent.createChooser(intent,"Seleccione la Aplicación"),10);
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
                        if (opciones[i].equals("Tomar Foto")){
                            if (ContextCompat.checkSelfPermission(FormularioOTActivity.this, Manifest.permission.CAMERA)
                                    == PackageManager.PERMISSION_GRANTED) {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent,20);
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
                            startActivityForResult(intent.createChooser(intent,"Seleccione la Aplicación"),10);
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
                        if (opciones[i].equals("Tomar Foto")){
                            if (ContextCompat.checkSelfPermission(FormularioOTActivity.this, Manifest.permission.CAMERA)
                                    == PackageManager.PERMISSION_GRANTED) {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent,20);
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
                            startActivityForResult(intent.createChooser(intent,"Seleccione la Aplicación"),10);
                        }
                    }
                });

                alertOpciones.show();
            }
        });

    }

}
