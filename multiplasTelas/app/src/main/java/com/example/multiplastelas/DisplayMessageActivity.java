package com.example.multiplastelas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_display_message);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(
                    WindowInsetsCompat.Type.systemBars()
            );

            v.setPadding(
                    systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    systemBars.bottom
            );

            return insets;
        });

        // Recupera a Intent enviada pela MainActivity
        Intent intent = getIntent();

        // Recupera a mensagem usando a mesma chave
        String mensagem = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Encontra o TextView
        TextView texto = findViewById(R.id.textView1);

        // Exibe a mensagem
        texto.setText(mensagem);
    }
}
