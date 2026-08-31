package br.edu.fatecjahu.helloapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView tSaudacao;
    Button btTraduzir, btReiniciar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tvSaudacao), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tSaudacao = findViewById(R.id.tvSaudacao);
        btTraduzir = findViewById(R.id.btnTraduzir);
        btReiniciar = findViewById(R.id.btnReiniciar);

        btTraduzir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void  onClick(View v) {
                tSaudacao.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                tSaudacao.setTextColor(getColor(R.color.blue));
                tSaudacao.setBackgroundColor(getColor(R.color.yellow));
                tSaudacao.setText("Ola Mundo!!!");
            }
        });

        btReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void  onClick(View v) {
                tSaudacao.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                tSaudacao.setTextColor(getColor(R.color.lightblue));
                tSaudacao.setBackgroundColor(getColor(R.color.yellow));
                tSaudacao.setText("Hello World!");
            }
        });
    }
}