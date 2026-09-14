package com.example.multiplastelas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE =
            "br.edu.fatec.jahu.mensagemapp.MESSAGE";

    EditText tMensagem;
    Button btSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {

                    androidx.core.graphics.Insets systemBars =
                            insets.getInsets(
                                    WindowInsetsCompat.Type.systemBars()
                            );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );

        tMensagem = findViewById(R.id.textView1);
        btSend = findViewById(R.id.btnSend);

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(
                        MainActivity.this,
                        DisplayMessageActivity.class
                );

                String mensagem = tMensagem.getText().toString();

                intent.putExtra(EXTRA_MESSAGE, mensagem);

                startActivity(intent);
            }
        });
    }
}
