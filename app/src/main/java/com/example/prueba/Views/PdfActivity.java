package com.example.prueba.Views;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.pdf.PdfDocument;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prueba.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    private LinearLayout ly_pdf;
    private Button btn_pdf;
    private Bitmap bitmap;

    private int modificarX = 15;
    private int modificarY = 15;

    private TextView orden_trabajo, factura_a, propietario, direccion, fecha, ruc, asesor,
    marca, modelo, modelotdp, año, color, placa, nromotor, nrochasis, kilometraje, tipotrabajo, observaciones;

    private ImageView indi_combustible,
            btn_uno1, btn_uno2, btn_uno3, btn_uno4,btn_uno5, btn_uno6, btn_uno7, btn_uno8, btn_uno9, btn_uno10, btn_uno11,
    btn_uno12, btn_uno13, btn_uno14, btn_uno15, btn_uno16, btn_uno17, btn_uno18, btn_uno19, btn_uno20, btn_uno21, btn_uno22,
    btn_uno23, btn_uno24,
            btn_dos1, btn_dos2, btn_dos3, btn_dos4, btn_dos5, btn_dos6, btn_dos7, btn_dos8, btn_dos9, btn_dos10, btn_dos11,
    btn_dos12, btn_dos13, btn_dos14, btn_dos15, btn_dos16, btn_dos17, btn_dos18, btn_dos19, btn_dos20,
            btn_tres1, btn_tres2, btn_tres3, btn_tres4, btn_tres5, btn_tres6, btn_tres7, btn_tres8, btn_tres9, btn_tres10, btn_tres11,
    btn_tres12, btn_tres13, btn_tres14, btn_tres15, btn_tres16, btn_tres17, btn_tres18, btn_tres19, btn_tres20,
    btn_cuatro1, btn_cuatro2, btn_cuatro3, btn_cuatro4, btn_cuatro5, btn_cuatro6, btn_cuatro7, btn_cuatro8, btn_cuatro9, btn_cuatro10, btn_cuatro11,
    btn_cuatro12, btn_cuatro13, btn_cuatro14, btn_cuatro15, btn_cuatro16, btn_cuatro17, btn_cuatro18, btn_cuatro19, btn_cuatro20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        getSupportActionBar().hide();

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        btn_pdf = (Button) findViewById(R.id.btn_pdf);
        ly_pdf = (LinearLayout) findViewById(R.id.ly_pdf);

        orden_trabajo = (TextView) findViewById(R.id.txt_tipo_ordenPDF);
        factura_a = (TextView) findViewById(R.id.txtFacturaPDF);
        propietario = (TextView) findViewById(R.id.txtPropietarioPDF);
        direccion = (TextView) findViewById(R.id.txtDireccionPDF);
        fecha = (TextView) findViewById(R.id.txtFechaPDF);
        ruc = (TextView) findViewById(R.id.txtRucPDF);
        asesor = (TextView) findViewById(R.id.txtAsesorPDF);
        marca = (TextView) findViewById(R.id.txtMarcaPDF);
        modelo = (TextView) findViewById(R.id.txtModeloPDF);
        modelotdp = (TextView) findViewById(R.id.txtModeloTDPPDF);
        año = (TextView) findViewById(R.id.txtAñoPDF);
        color = (TextView) findViewById(R.id.txtColorPDF);
        placa = (TextView) findViewById(R.id.txtPlacaPDF);
        nromotor = (TextView) findViewById(R.id.txtNromotorPDF);
        nrochasis = (TextView) findViewById(R.id.txtNroChasisPDF);
        kilometraje = (TextView) findViewById(R.id.txtKilometrajePDF);
        tipotrabajo = (TextView) findViewById(R.id.txtTipotrabajoPDF);
        observaciones = (TextView) findViewById(R.id.txtObservacionesPDF);


        //Recibiendo datos
        String dato1 = getIntent().getStringExtra("orden_trabajo");
        orden_trabajo.setText("N° "+dato1);
        String dato2 = getIntent().getStringExtra("facturaa");
        factura_a.setText(dato2);
        String dato3 = getIntent().getStringExtra("propietario");
        propietario.setText(dato3);
        String dato4 = getIntent().getStringExtra("direccion");
        direccion.setText(dato4);
        String dato5 = getIntent().getStringExtra("fecha");
        fecha.setText(dato5);
        String dato6 = getIntent().getStringExtra("ruc");
        ruc.setText(dato6);
        String dato7 = getIntent().getStringExtra("asesor");
        asesor.setText(dato7);
        String dato9 = getIntent().getStringExtra("marca");
        marca.setText(dato9);
        String dato10 = getIntent().getStringExtra("modelo");
        modelo.setText(dato10);
        String dato11 = getIntent().getStringExtra("modelotdp");
        modelotdp.setText(dato11);
        String dato12 = getIntent().getStringExtra("año");
        año.setText(dato12);
        String dato13 = getIntent().getStringExtra("color");
        color.setText(dato13);
        String dato14 = getIntent().getStringExtra("placa");
        placa.setText(dato14);
        String dato15 = getIntent().getStringExtra("nromotor");
        nromotor.setText(dato15);
        String dato16 = getIntent().getStringExtra("nrochasis");
        nrochasis.setText(dato16);
        String dato17 = getIntent().getStringExtra("kilometraje");
        kilometraje.setText(dato17);
        String dato18 = getIntent().getStringExtra("tipotrabajo");
        tipotrabajo.setText(dato18);
        String dato19 = getIntent().getStringExtra("observaciones");
        observaciones.setText(dato19);
        //finaliza recibo de datos

        //crear pdf
        btn_pdf.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Log.d("Tamaño"," " + ly_pdf.getWidth() +"  " + ly_pdf.getWidth());
                bitmap = loadBitmapFromView(ly_pdf, ly_pdf.getWidth(), ly_pdf.getHeight());
                createPdf();
            }
        });
        //fin crear pdf

        //mover imagen
        indi_combustible = (ImageView) findViewById(R.id.indicador_combustible);
        indi_combustible.setOnTouchListener(eventoMover);
        btn_uno1 = (ImageView) findViewById(R.id.btn_uno1);
        btn_uno1.setOnTouchListener(eventoMover);
        btn_uno2 = (ImageView) findViewById(R.id.btn_uno2);
        btn_uno2.setOnTouchListener(eventoMover);
        btn_uno3 = (ImageView) findViewById(R.id.btn_uno3);
        btn_uno3.setOnTouchListener(eventoMover);
        btn_uno4 = (ImageView) findViewById(R.id.btn_uno4);
        btn_uno4.setOnTouchListener(eventoMover);
        btn_uno5 = (ImageView) findViewById(R.id.btn_uno5);
        btn_uno5.setOnTouchListener(eventoMover);
        btn_uno6 = (ImageView) findViewById(R.id.btn_uno6);
        btn_uno6.setOnTouchListener(eventoMover);
        btn_uno7 = (ImageView) findViewById(R.id.btn_uno7);
        btn_uno7.setOnTouchListener(eventoMover);
        btn_uno8 = (ImageView) findViewById(R.id.btn_uno8);
        btn_uno8.setOnTouchListener(eventoMover);
        btn_uno9 = (ImageView) findViewById(R.id.btn_uno9);
        btn_uno9.setOnTouchListener(eventoMover);
        btn_uno10 = (ImageView) findViewById(R.id.btn_uno10);
        btn_uno10.setOnTouchListener(eventoMover);
        btn_uno11 = (ImageView) findViewById(R.id.btn_uno11);
        btn_uno11.setOnTouchListener(eventoMover);
        btn_uno12 = (ImageView) findViewById(R.id.btn_uno12);
        btn_uno12.setOnTouchListener(eventoMover);
        btn_uno13 = (ImageView) findViewById(R.id.btn_uno13);
        btn_uno13.setOnTouchListener(eventoMover);
        btn_uno14 = (ImageView) findViewById(R.id.btn_uno14);
        btn_uno14.setOnTouchListener(eventoMover);
        btn_uno15 = (ImageView) findViewById(R.id.btn_uno15);
        btn_uno15.setOnTouchListener(eventoMover);
        btn_uno16 = (ImageView) findViewById(R.id.btn_uno16);
        btn_uno16.setOnTouchListener(eventoMover);
        btn_uno17 = (ImageView) findViewById(R.id.btn_uno17);
        btn_uno17.setOnTouchListener(eventoMover);
        btn_uno18 = (ImageView) findViewById(R.id.btn_uno18);
        btn_uno18.setOnTouchListener(eventoMover);
        btn_uno19 = (ImageView) findViewById(R.id.btn_uno19);
        btn_uno19.setOnTouchListener(eventoMover);
        btn_uno20 = (ImageView) findViewById(R.id.btn_uno20);
        btn_uno20.setOnTouchListener(eventoMover);
        btn_uno21 = (ImageView) findViewById(R.id.btn_uno21);
        btn_uno21.setOnTouchListener(eventoMover);
        btn_uno22 = (ImageView) findViewById(R.id.btn_uno22);
        btn_uno22.setOnTouchListener(eventoMover);
        btn_uno23 = (ImageView) findViewById(R.id.btn_uno23);
        btn_uno23.setOnTouchListener(eventoMover);
        btn_uno24 = (ImageView) findViewById(R.id.btn_uno24);
        btn_uno24.setOnTouchListener(eventoMover);

        btn_dos1 = (ImageView) findViewById(R.id.btn_dos1);
        btn_dos2 = (ImageView) findViewById(R.id.btn_dos2);
        btn_dos3 = (ImageView) findViewById(R.id.btn_dos3);
        btn_dos4 = (ImageView) findViewById(R.id.btn_dos4);
        btn_dos5 = (ImageView) findViewById(R.id.btn_dos5);
        btn_dos6 = (ImageView) findViewById(R.id.btn_dos6);
        btn_dos7 = (ImageView) findViewById(R.id.btn_dos7);
        btn_dos8 = (ImageView) findViewById(R.id.btn_dos8);
        btn_dos9 = (ImageView) findViewById(R.id.btn_dos9);
        btn_dos10 = (ImageView) findViewById(R.id.btn_dos10);
        btn_dos11 = (ImageView) findViewById(R.id.btn_dos11);
        btn_dos12 = (ImageView) findViewById(R.id.btn_dos12);
        btn_dos13 = (ImageView) findViewById(R.id.btn_dos13);
        btn_dos14 = (ImageView) findViewById(R.id.btn_dos14);
        btn_dos15 = (ImageView) findViewById(R.id.btn_dos15);
        btn_dos16 = (ImageView) findViewById(R.id.btn_dos16);
        btn_dos17 = (ImageView) findViewById(R.id.btn_dos17);
        btn_dos18 = (ImageView) findViewById(R.id.btn_dos18);
        btn_dos19 = (ImageView) findViewById(R.id.btn_dos19);
        btn_dos20 = (ImageView) findViewById(R.id.btn_dos20);
        btn_dos1.setOnTouchListener(eventoMover);
        btn_dos2.setOnTouchListener(eventoMover);
        btn_dos3.setOnTouchListener(eventoMover);
        btn_dos4.setOnTouchListener(eventoMover);
        btn_dos5.setOnTouchListener(eventoMover);
        btn_dos6.setOnTouchListener(eventoMover);
        btn_dos7.setOnTouchListener(eventoMover);
        btn_dos8.setOnTouchListener(eventoMover);
        btn_dos9.setOnTouchListener(eventoMover);
        btn_dos10.setOnTouchListener(eventoMover);
        btn_dos11.setOnTouchListener(eventoMover);
        btn_dos12.setOnTouchListener(eventoMover);
        btn_dos13.setOnTouchListener(eventoMover);
        btn_dos14.setOnTouchListener(eventoMover);
        btn_dos15.setOnTouchListener(eventoMover);
        btn_dos16.setOnTouchListener(eventoMover);
        btn_dos17.setOnTouchListener(eventoMover);
        btn_dos18.setOnTouchListener(eventoMover);
        btn_dos19.setOnTouchListener(eventoMover);
        btn_dos20.setOnTouchListener(eventoMover);


        btn_tres1 = (ImageView) findViewById(R.id.btn_tres1);
        btn_tres2 = (ImageView) findViewById(R.id.btn_tres2);
        btn_tres3 = (ImageView) findViewById(R.id.btn_tres3);
        btn_tres4 = (ImageView) findViewById(R.id.btn_tres4);
        btn_tres5 = (ImageView) findViewById(R.id.btn_tres5);
        btn_tres6 = (ImageView) findViewById(R.id.btn_tres6);
        btn_tres7 = (ImageView) findViewById(R.id.btn_tres7);
        btn_tres8 = (ImageView) findViewById(R.id.btn_tres8);
        btn_tres9 = (ImageView) findViewById(R.id.btn_tres9);
        btn_tres10 = (ImageView) findViewById(R.id.btn_tres10);
        btn_tres11 = (ImageView) findViewById(R.id.btn_tres11);
        btn_tres12 = (ImageView) findViewById(R.id.btn_tres12);
        btn_tres13 = (ImageView) findViewById(R.id.btn_tres13);
        btn_tres14 = (ImageView) findViewById(R.id.btn_tres14);
        btn_tres15 = (ImageView) findViewById(R.id.btn_tres15);
        btn_tres16 = (ImageView) findViewById(R.id.btn_tres16);
        btn_tres17 = (ImageView) findViewById(R.id.btn_tres17);
        btn_tres18 = (ImageView) findViewById(R.id.btn_tres18);
        btn_tres19 = (ImageView) findViewById(R.id.btn_tres19);
        btn_tres20 = (ImageView) findViewById(R.id.btn_tres20);
        btn_tres1.setOnTouchListener(eventoMover);
        btn_tres2.setOnTouchListener(eventoMover);
        btn_tres3.setOnTouchListener(eventoMover);
        btn_tres4.setOnTouchListener(eventoMover);
        btn_tres5.setOnTouchListener(eventoMover);
        btn_tres6.setOnTouchListener(eventoMover);
        btn_tres7.setOnTouchListener(eventoMover);
        btn_tres8.setOnTouchListener(eventoMover);
        btn_tres9.setOnTouchListener(eventoMover);
        btn_tres10.setOnTouchListener(eventoMover);
        btn_tres11.setOnTouchListener(eventoMover);
        btn_tres12.setOnTouchListener(eventoMover);
        btn_tres13.setOnTouchListener(eventoMover);
        btn_tres14.setOnTouchListener(eventoMover);
        btn_tres15.setOnTouchListener(eventoMover);
        btn_tres16.setOnTouchListener(eventoMover);
        btn_tres17.setOnTouchListener(eventoMover);
        btn_tres18.setOnTouchListener(eventoMover);
        btn_tres19.setOnTouchListener(eventoMover);
        btn_tres20.setOnTouchListener(eventoMover);


        btn_cuatro1 = (ImageView) findViewById(R.id.btn_cuatro1);
        btn_cuatro2 = (ImageView) findViewById(R.id.btn_cuatro2);
        btn_cuatro3 = (ImageView) findViewById(R.id.btn_cuatro3);
        btn_cuatro4 = (ImageView) findViewById(R.id.btn_cuatro4);
        btn_cuatro5 = (ImageView) findViewById(R.id.btn_cuatro5);
        btn_cuatro6 = (ImageView) findViewById(R.id.btn_cuatro6);
        btn_cuatro7 = (ImageView) findViewById(R.id.btn_cuatro7);
        btn_cuatro8 = (ImageView) findViewById(R.id.btn_cuatro8);
        btn_cuatro9 = (ImageView) findViewById(R.id.btn_cuatro9);
        btn_cuatro10 = (ImageView) findViewById(R.id.btn_cuatro10);
        btn_cuatro11 = (ImageView) findViewById(R.id.btn_cuatro11);
        btn_cuatro12 = (ImageView) findViewById(R.id.btn_cuatro12);
        btn_cuatro13 = (ImageView) findViewById(R.id.btn_cuatro13);
        btn_cuatro14 = (ImageView) findViewById(R.id.btn_cuatro14);
        btn_cuatro15 = (ImageView) findViewById(R.id.btn_cuatro15);
        btn_cuatro16 = (ImageView) findViewById(R.id.btn_cuatro16);
        btn_cuatro17 = (ImageView) findViewById(R.id.btn_cuatro17);
        btn_cuatro18 = (ImageView) findViewById(R.id.btn_cuatro18);
        btn_cuatro19 = (ImageView) findViewById(R.id.btn_cuatro19);
        btn_cuatro20 = (ImageView) findViewById(R.id.btn_cuatro20);
        btn_cuatro1.setOnTouchListener(eventoMover);
        btn_cuatro2.setOnTouchListener(eventoMover);
        btn_cuatro3.setOnTouchListener(eventoMover);
        btn_cuatro4.setOnTouchListener(eventoMover);
        btn_cuatro5.setOnTouchListener(eventoMover);
        btn_cuatro6.setOnTouchListener(eventoMover);
        btn_cuatro7.setOnTouchListener(eventoMover);
        btn_cuatro8.setOnTouchListener(eventoMover);
        btn_cuatro9.setOnTouchListener(eventoMover);
        btn_cuatro10.setOnTouchListener(eventoMover);
        btn_cuatro11.setOnTouchListener(eventoMover);
        btn_cuatro12.setOnTouchListener(eventoMover);
        btn_cuatro13.setOnTouchListener(eventoMover);
        btn_cuatro14.setOnTouchListener(eventoMover);
        btn_cuatro15.setOnTouchListener(eventoMover);
        btn_cuatro16.setOnTouchListener(eventoMover);
        btn_cuatro17.setOnTouchListener(eventoMover);
        btn_cuatro18.setOnTouchListener(eventoMover);
        btn_cuatro19.setOnTouchListener(eventoMover);
        btn_cuatro20.setOnTouchListener(eventoMover);
        //fin mover imagen

    }
    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap bmpImg = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmpImg);
        v.draw(c);
        return bmpImg;
    }
    // Funcion para generar el PDF
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void createPdf(){
        if (CheckPermission()) {
            DisplayMetrics displaymetrics = new DisplayMetrics();
            this.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            float hight = displaymetrics.heightPixels;
            float width = displaymetrics.widthPixels;

            int convertHighet = (int) hight, convertWidth = (int) width;
            PdfDocument document = new PdfDocument();
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
            PdfDocument.Page page = document.startPage(pageInfo);

            Canvas canvas = page.getCanvas();

            Paint paint = new Paint();
            canvas.drawPaint(paint);

            bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

            paint.setColor(Color.BLUE);
            canvas.drawBitmap(bitmap, 0, 0, null);
            document.finishPage(page);
            // Ruta donde se almacenara
            //String targetPdf = "/sdcard/PDFGenerado.pdf";
            File filePath;
            //filePath = new File(targetPdf);
            //filePath = new File(this.getBaseContext().getFilesDir(), "pdffromlayout.pdf");
            filePath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "pdffromlayout.pdf");
            try {
                document.writeTo(new FileOutputStream(filePath));
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Algo anda mal: " + e.toString(), Toast.LENGTH_LONG).show();
            }
            document.close();
            Toast.makeText(this, "PDF esta creado!!!", Toast.LENGTH_SHORT).show();
            abrirPDFGenerado();
        }
    }
    // Abrir el archivo PDF una vez generado.
    private void abrirPDFGenerado(){
        if (CheckPermission()){
        //File file = new File("/sdcard/pdffromlayout.pdf");
        //File file = new File(this.getBaseContext().getFilesDir(), "pdffromlayout.pdf");
        File file  = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) , "pdffromlayout.pdf");
        if (file.exists())
        {
            Intent intent=new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            try
            {
                startActivity(intent);
            }
            catch(ActivityNotFoundException e)
            {
                Toast.makeText(PdfActivity.this, "No hay aplicación disponible para ver pdf", Toast.LENGTH_LONG).show();
            }
        }
        }
    }
    public boolean CheckPermission() {
        if (ContextCompat.checkSelfPermission(PdfActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(PdfActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(PdfActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(PdfActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(PdfActivity.this,
                    Manifest.permission.CAMERA) || ActivityCompat.shouldShowRequestPermissionRationale(PdfActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                new AlertDialog.Builder(PdfActivity.this)
                        .setTitle("Permission")
                        .setMessage("Please accept the permissions")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(PdfActivity.this,
                                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        MY_PERMISSIONS_REQUEST_LOCATION);


                                startActivity(new Intent(PdfActivity
                                        .this, MainActivity.class));
                                PdfActivity.this.overridePendingTransition(0, 0);
                            }
                        })
                        .create()
                        .show();


            } else {
                ActivityCompat.requestPermissions(PdfActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }

            return false;
        } else {

            return true;
        }
    }
    //mover imagen
    View.OnTouchListener eventoMover = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            PointF downPT = new PointF();
            PointF startPT = new PointF();
            int eid = motionEvent.getAction();
            switch (eid){
                case MotionEvent.ACTION_MOVE:
                    startPT = new PointF(view.getX(),view.getY());
                    PointF mv = new PointF(motionEvent.getX() - downPT.x,motionEvent.getY() - downPT.y);
                    view.setX((startPT.x+mv.x) - modificarX);
                    view.setY((startPT.y+mv.y) - modificarY);
                    break;

                case MotionEvent.ACTION_DOWN:
                    downPT.x = motionEvent.getX();
                    downPT.y = motionEvent.getY();
                    break;

                case MotionEvent.ACTION_UP:
                    view.performClick();
                    break;

                default:
                    break;
            }

            return true;
        }
    };
    //fin mover imagen
}
