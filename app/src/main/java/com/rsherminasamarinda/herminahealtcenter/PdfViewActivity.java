package com.rsherminasamarinda.herminahealtcenter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class PdfViewActivity extends AppCompatActivity {

    PDFView pdfView;
    File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);

        pdfView = findViewById(R.id.pdfView);
        file = new File("/storage/emulated/0/Hello.pdf");
        pdfView.fromFile(file).load();
    }
}