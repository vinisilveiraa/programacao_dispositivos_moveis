package br.edu.fatecjahu.loginapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // 0. Criar os objetos Java.
    TextView tLogin, tSenha;
    Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. cria objeto
        btLogin = (Button) findViewById(R.id.btnLogin);

        // 2. Metodo do botão "Login"
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 3. cria os botoes
                tLogin = (TextView) findViewById(R.id.tLogin);
                tSenha = (TextView) findViewById(R.id.tSenha);

                //  4. cria user e senha padrao
                final String LOGIN = "vini";
                final String SENHA = "12345";

                // 5. captura user e senha
                String login = tlogin.getText().toString();
                String senha = tSenha.getText().toString();

                // 6. validacao do user
                if (LOGIN.equals(login) && SENHA.equals(senha)){
                    // cria indent
                    // cria pacote
                    // adiciona chaves e valores
                } else {

                }
            }
        });

    }
}