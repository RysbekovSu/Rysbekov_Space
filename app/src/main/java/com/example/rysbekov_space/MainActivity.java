package com.example.rysbekov_space;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.rysbekov_space.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.b1.setOnClickListener(v -> {
            binding.editText.setVisibility(View.VISIBLE);
            binding.b2.setVisibility(View.VISIBLE);
            binding.b1.setVisibility(View.INVISIBLE);
            binding.b3.setVisibility(View.VISIBLE);

        });

        binding.b2.setOnClickListener(v1 -> {
            try{
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "My message");
                emailIntent.putExtra(Intent.EXTRA_TEXT, binding.editText.getText().toString());

                if (emailIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(emailIntent);}
            }catch (Exception ex){
                Toast.makeText(this, "Can't send nothing!!!", Toast.LENGTH_SHORT).show();
            }
        });

        binding.b3.setOnClickListener(v3 -> {

            try{
                String telegramPackageName = "org.telegram.messenger";

                Intent telegramIntent = new Intent(Intent.ACTION_SEND);
                telegramIntent.setType("text/plain");
                telegramIntent.setPackage(telegramPackageName);
                telegramIntent.putExtra(Intent.EXTRA_TEXT, binding.editText.getText().toString());

                    if (telegramIntent.resolveActivity(getPackageManager()) == null) {
                    startActivity(telegramIntent);
                } else {
                    Toast.makeText(this, "Telegram app not installed!", Toast.LENGTH_SHORT).show();
                }

            }catch (Exception ex){
                Toast.makeText(this, "Can't send nothing!!!", Toast.LENGTH_SHORT).show();
            }


        });
    }
}
