package com.dargoz.pdfium.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.dargoz.pdfium.android.databinding.ActivityMainBinding;
import com.dargoz.pdfium.core.PdfiumCore;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
        TextView tv = binding.sampleText;

        PdfiumCore pdfiumCore = new PdfiumCore(this);

    }


}