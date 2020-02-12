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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.prueba.R;
import com.kyanogen.signatureview.SignatureView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;


public class FormularioOTActivity extends AppCompatActivity {


    ImageView btnLIzquierdo, btnLDerecho, btnMedio, btnFrontal, btnIFrontal, btnIPosterior, btnPosterior;

    Bitmap bitmap;
    Button clear, save;
    SignatureView signatureView;
    String path;
    private static final String IMAGE_DIRECTORY = "/Camera";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_ot_layout);


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

    }

}
