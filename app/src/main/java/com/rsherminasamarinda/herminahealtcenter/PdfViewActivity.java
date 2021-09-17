package com.rsherminasamarinda.herminahealtcenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.zxing.WriterException;
import com.rsherminasamarinda.herminahealtcenter.Alert.AlertHakAkses;
import com.rsherminasamarinda.herminahealtcenter.Alert.AlertKoneksi;
import com.rsherminasamarinda.herminahealtcenter.model.Historylabdetail;
import com.rsherminasamarinda.herminahealtcenter.model.HistorylabdetailResponse;
import com.rsherminasamarinda.herminahealtcenter.model.MetaData;
import com.rsherminasamarinda.herminahealtcenter.model.Testindonesium;
import com.rsherminasamarinda.herminahealtcenter.rest.ApiClient;
import com.rsherminasamarinda.herminahealtcenter.rest.ApiInterface;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PdfViewActivity extends AppCompatActivity {

    PDFView pdfView;
    String  notransaksi, tgllahir, nobukti, nocm, nmpasien, umur, dokternama, tglsampling, jamsampling, analis, dktrpatologi, shift, hasilnumeriks;
    private static final int CREATE_FILE = 1;
    Boolean on = true;
    private String stringFilePath;
    private File file;
    Bitmap bmp, scaledbmp, bitmap, scaledbmpQr;
    int pageWidth = 701, barisklmpknama, barisy, i, p, dataakhir;
    Date dateObj;
    DateFormat dateFormat;
    private ImageView backLaboratoriumfilepdf;
    QRGEncoder qrgEncoder;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);
        pdfView = findViewById(R.id.pdfView);
        if (on) {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(view.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        backLaboratoriumfilepdf = findViewById(R.id.IVbacklaboratoriumpdf);

        notransaksi = getNotransaksi() + ".pdf";
        stringFilePath = Environment.getExternalStorageDirectory().getPath() + "/Download/" + notransaksi;
        file = new File(stringFilePath);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.pdfheader_ng);
        scaledbmp = Bitmap.createScaledBitmap(bmp, 701, 169, false);

        backLaboratoriumfilepdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getData();


    }


    private void createFile(Uri pickerInitialUri) {

        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/pdf");
        intent.putExtra(Intent.EXTRA_TITLE, "invoice2.pdf");

        // Optionally, specify a URI for the directory that should be opened in
        // the system file picker when your app creates the document.
        intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri);
        startActivityForResult(intent, CREATE_FILE);
    }

    private void getData() {


        dateObj = new Date();
        ApiInterface apiService =
                ApiClient.createService(ApiInterface.class, "admin", "h3rm1n4c4r3");


        Call<HistorylabdetailResponse> call = apiService.hlabdet(getNotransaksi());
        call.enqueue(new Callback<HistorylabdetailResponse>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<HistorylabdetailResponse> call, Response<HistorylabdetailResponse> response) {
                MetaData code = response.body().getMetaData();
                String message = code.getMessage();
                String MetaCode = code.getCode();
                Historylabdetail historylabdetailResponse = response.body().getHistorylabdetail();

                nobukti = historylabdetailResponse.getNotransaksi();
                nocm = historylabdetailResponse.getPatientid();
                nmpasien = historylabdetailResponse.getPatientnama();
                tgllahir = historylabdetailResponse.getTgllahir();
                umur = historylabdetailResponse.getUmur();
                dokternama = historylabdetailResponse.getDokternama();
                tglsampling = historylabdetailResponse.getTglsampling();
                jamsampling = historylabdetailResponse.getJamsampling();
                shift = historylabdetailResponse.getShift();
                analis = historylabdetailResponse.getAnalis();
                dktrpatologi = historylabdetailResponse.getDokterpatologi();

                final List<Testindonesium> testindonesiums = historylabdetailResponse.getTestindonesia();
//                int ukuran = testindonesiums.size();
//                System.out.println("datalab : " + ukuran) ;


                if (MetaCode.equals("200")) {

//                    generate qrcode
// below line is for getting
                    // the windowmanager service.
                    WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);

                    // initializing a variable for default display.
                    Display display = manager.getDefaultDisplay();
// creating a variable for point which
                    // is to be displayed in QR Code.
                    Point point = new Point();
                    display.getSize(point);

                    // getting width and
                    // height of a point
                    int width = point.x;
                    int height = point.y;

                    // generating dimension from width and height.
                    int dimen = width < height ? width : height;
                    dimen = dimen * 3 / 4;

                    // setting this dimensions inside our qr code
                    // encoder to generate our qr code.
                    qrgEncoder = new QRGEncoder(nobukti+dokternama+analis+jamsampling+dktrpatologi, null, QRGContents.Type.TEXT, dimen);
                    try {
                        // getting our qrcode in the form of bitmap.
                        bitmap = qrgEncoder.encodeAsBitmap();
                        scaledbmpQr = bitmap.createScaledBitmap(bitmap, 200, 200, false);
                        // the bitmap is set inside our image
                        // view using .setimagebitmap method.
//                        qrCodeIV.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        // this method is called for
                        // exception handling.
                        Log.e("Tag", e.toString());
                    }

                    //create file pdf
                    float ukuran = testindonesiums.size();
                    barisklmpknama = 0;
                    barisy = 0;
                    float datarowpage = 10;
                    dataakhir = -1;
                    float ihih = ukuran / datarowpage;
                    float pagecount = (int) Math.ceil(ihih);

//                    Toast.makeText(PdfViewActivity.this, "Jumlah page :" + pagecount + ", Jumlah Data :" + ukuran + ", Hasil Bagi : " + ihih, Toast.LENGTH_LONG).show();
                    PdfDocument pdfDocument = new PdfDocument();
                    Paint myPaint = new Paint();
                    Paint titlePaint = new Paint();

                    for (p = 1; p <= pagecount; p++) {
                        barisklmpknama = 400;
                        barisy = 415;
                        String halaman = String.valueOf(p);
                        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(pageWidth, 1500, p).create();
                        PdfDocument.Page page = pdfDocument.startPage(pageInfo);
                        Canvas canvas = page.getCanvas();

                        //header pdf
                        canvas.drawBitmap(scaledbmp, 0, 0, myPaint);


                        // JUDUL PDF
                        titlePaint.setTextAlign(Paint.Align.CENTER);
                        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                        titlePaint.setTextSize(18);
                        canvas.drawText("HASIL PEMERIKSAAN LABORATORIUM", pageWidth / 2, 180, titlePaint);

                        // header pdf left

                        myPaint.setTextAlign(Paint.Align.LEFT);
                        myPaint.setTextSize(12);
                        myPaint.setColor(Color.BLACK);
                        canvas.drawText("No. Transaksi", 20, 220, myPaint);
                        canvas.drawText(": " + notransaksi, 120, 220, myPaint);
                        canvas.drawText("No. RM", 20, 240, myPaint);
                        canvas.drawText(": " + nocm, 120, 240, myPaint);
                        canvas.drawText("Nama Pasien", 20, 260, myPaint);
                        canvas.drawText(": " + nmpasien, 120, 260, myPaint);
                        canvas.drawText("Tgl.Lahir/Umur", 20, 280, myPaint);
                        canvas.drawText(": " + tgllahir, 120, 280, myPaint);
                        canvas.drawText("Dokter Pengirim", 20, 300, myPaint);
                        canvas.drawText(": " + dokternama, 120, 300, myPaint);

                        // header pdf right


                        myPaint.setTextAlign(Paint.Align.RIGHT);
                        myPaint.setTextSize(12);
                        myPaint.setColor(Color.BLACK);
                        canvas.drawText("Tgl. Sampling :", pageWidth - 120, 220, myPaint);
                        canvas.drawText(tglsampling, pageWidth - 20, 220, myPaint);
                        canvas.drawText("Jam Sampling :", pageWidth - 120, 240, myPaint);
                        canvas.drawText(jamsampling, pageWidth - 20, 240, myPaint);
                        canvas.drawText("Shift :", pageWidth - 120, 260, myPaint);
                        canvas.drawText(shift, pageWidth - 20, 260, myPaint);
                        canvas.drawText("Halaman ", pageWidth - 120, 280, myPaint);
                        canvas.drawText(halaman, pageWidth - 20, 280, myPaint);


                        //table pemeriksaan
                        myPaint.setStyle(Paint.Style.STROKE);
                        myPaint.setStrokeWidth(2);
                        canvas.drawRect(20, 340, pageWidth - 20, 380, myPaint);

                        myPaint.setTextAlign(Paint.Align.LEFT);
                        myPaint.setStyle(Paint.Style.FILL);
                        canvas.drawText("Pemeriksaan", 50, 365, myPaint);

                        canvas.drawLine(200, 340, 200, 380, myPaint);
                        myPaint.setTextAlign(Paint.Align.LEFT);
                        myPaint.setStyle(Paint.Style.FILL);
                        canvas.drawText("Hasil", 230, 365, myPaint);
                        canvas.drawLine(300, 340, 300, 380, myPaint);
                        myPaint.setTextAlign(Paint.Align.LEFT);
                        myPaint.setStyle(Paint.Style.FILL);
                        canvas.drawText("Nilai Normal", 320, 365, myPaint);
                        canvas.drawLine(450, 340, 450, 380, myPaint);
                        myPaint.setTextAlign(Paint.Align.LEFT);
                        myPaint.setStyle(Paint.Style.FILL);
                        canvas.drawText("Satuan", 480, 365, myPaint);
                        canvas.drawLine(550, 340, 550, 380, myPaint);
                        myPaint.setTextAlign(Paint.Align.LEFT);
                        myPaint.setStyle(Paint.Style.FILL);
                        canvas.drawText("Keterangan", 570, 365, myPaint);

                        //list pemeriksaan

                        for (i = 0; i <= ukuran; i++) {
//                        System.out.println("testindonesia " + i + " : " + testindonesiums.get(i).getTestindonesia());

                            if (i == datarowpage || dataakhir == ukuran - 1) {
                                break;
                            }

                            ++dataakhir;

                            myPaint.setStyle(Paint.Style.STROKE);
                            myPaint.setStrokeWidth(2);
                            canvas.drawRect(20, 380, pageWidth - 20, 40 * datarowpage + 380, myPaint);
                            myPaint.setTextAlign(Paint.Align.LEFT);
                            myPaint.setStyle(Paint.Style.FILL);

                            canvas.drawText(testindonesiums.get(dataakhir).getKelompoknama(), 30, barisklmpknama, myPaint);
                            barisklmpknama = barisklmpknama + 30;
                            canvas.drawText(testindonesiums.get(dataakhir).getTestindonesia(), 50, barisy, myPaint);
                            canvas.drawLine(200, 380, 200, 40 * datarowpage + 380, myPaint);
                            hasilnumeriks = testindonesiums.get(dataakhir).getHasilnumerik();
                            if (testindonesiums.get(dataakhir).getHasilnumerik().equals("0")) {
                                myPaint.setTextAlign(Paint.Align.LEFT);
                                myPaint.setStyle(Paint.Style.FILL);
                                canvas.drawText(testindonesiums.get(dataakhir).getHasilkarakter(), 210, barisy, myPaint);
                            } else {
                                myPaint.setTextAlign(Paint.Align.LEFT);
                                myPaint.setStyle(Paint.Style.FILL);
                                canvas.drawText(testindonesiums.get(dataakhir).getHasilnumerik(), 210, barisy, myPaint);
                            }
                            canvas.drawLine(300, 380, 300, 40 * datarowpage + 380, myPaint);
                            myPaint.setTextAlign(Paint.Align.LEFT);
                            myPaint.setStyle(Paint.Style.FILL);
                            canvas.drawText(testindonesiums.get(dataakhir).getNormalkarakter(), 310, barisy, myPaint);
                            canvas.drawLine(450, 380, 450, 40 * datarowpage + 380, myPaint);

                            myPaint.setTextAlign(Paint.Align.LEFT);
                            myPaint.setStyle(Paint.Style.FILL);
                            canvas.drawText(testindonesiums.get(dataakhir).getSatuanindonesia(), 470, barisy, myPaint);
                            barisy = barisy + 30;
                            canvas.drawLine(550, 380, 550, 40 * datarowpage + 380, myPaint);
//                                System.out.println("Cek perulangan :" + p + " " + i + " " + dataakhir + " " + testindonesiums.size());

                        }

//                        Toast.makeText(PdfViewActivity.this, String.valueOf("Data akhir: "+dataakhir), Toast.LENGTH_LONG).show();

                        myPaint.setTextAlign(Paint.Align.LEFT);
                        myPaint.setTextSize(12);
                        myPaint.setColor(Color.BLACK);
                        canvas.drawText("Dokter Patologi Klinik ", 20, 40 * datarowpage + 395, myPaint);
                        canvas.drawText(": " + dktrpatologi, 170, 40 * datarowpage + 395, myPaint);

                        dateFormat = new SimpleDateFormat("dd/MM/yy");

                        myPaint.setTextAlign(Paint.Align.RIGHT);
                        myPaint.setTextSize(12);
                        myPaint.setColor(Color.BLACK);
                        canvas.drawText("Samarinda, " + dateFormat.format(dateObj), pageWidth - 80, 40 * datarowpage + 400, myPaint);
                        canvas.drawBitmap(scaledbmpQr,pageWidth - 220,40 * datarowpage + 410,myPaint);
                        canvas.drawLine(pageWidth - 220, 40 * datarowpage + 600, pageWidth - 20, 40 * datarowpage + 600, myPaint);
                        canvas.drawText(analis, pageWidth - 80, 40 * datarowpage + 620, myPaint);
                        pdfDocument.finishPage(page);
                    }


                    try {
                        pdfDocument.writeTo(new FileOutputStream(file));
                    } catch (Exception e) {
                        e.printStackTrace();
//                        Toast.makeText(PdfViewActivity.this, "PDF gagal dibuat", Toast.LENGTH_LONG).show();
                        AlertHakAkses alertHakAkses = new AlertHakAkses();
                        alertHakAkses.showDialog(PdfViewActivity.this,"Kami membutuhkan ijin dari anda untuk membuat file pdf. Seperti gambar di atas");
                    }
                    pdfDocument.close();
                    pdfView.fromFile(file).load();

//                    Toast.makeText(PdfViewActivity.this, "PDF sudah dibuat /" + getFilesDir(), Toast.LENGTH_LONG).show();
                } else {
                    AlertKoneksi alert = new AlertKoneksi();
                    alert.showDialog(PdfViewActivity.this, message);
                }
            }

            @Override
            public void onFailure(Call<HistorylabdetailResponse> call, Throwable t) {

            }
        });

    }

    private String getNotransaksi() {
        Intent mIntent = getIntent();
        Bundle mBundle = mIntent.getExtras();
        if (mBundle != null) {
            notransaksi = mBundle.getString("notransaksi");
        }
        return notransaksi;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


//    public void ReadPDF(){
//        try {
//            PdfReader pdfReader = new PdfReader(file.getPath());
//            String stringParse = PdfTextExtractor.getTextFromPage(pdfReader,1).trim();
//            pdfReader.close();
////            textView.setText(stringParse);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            Toast.makeText(PdfViewActivity.this, "PDF gagal dibaca /", Toast.LENGTH_LONG).show();
//        }
//    }

}