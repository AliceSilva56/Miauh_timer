package com.example.miauhtimer;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Carregar a animação
        Animation slideIn = AnimationUtils.loadAnimation(this, R.anim.slide_in);

        // Aplicar a animação ao ImageView e TextViews
        ImageView imageView = findViewById(R.id.imageView);
        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);

        imageView.startAnimation(slideIn);
        textView1.startAnimation(slideIn);
        textView2.startAnimation(slideIn);

        // Navegar para a tela principal após 3 segundos
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, TelaPrincipal.class);
            startActivity(intent);
            finish();
        }, 3000);
    }
}