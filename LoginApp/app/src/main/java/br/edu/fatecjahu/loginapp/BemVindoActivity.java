package br.edu.fatecjahu.loginapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.MessageFormat;

public class BemVindoActivity extends AppCompatActivity {

    // 17. Declaração de objetos Java de texto.
    TextView text1, text2, text3, text4, text5, text6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bem_vindo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 0. adicionar a barra de titulo na tela2 para mostrar a seta de retorno
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        // 18. Recebe o pacote vindo da intent de MainActivity.
        Bundle args = getIntent().getExtras();

        //Log.i(TAG, getClassName() + " => Dados do pacote recuperados " + getClassName() + "!!!");

        // 19. Recebe o nome enviado por parâmetro.         // key (chave)     value(valor)
        String nome = args.getString("nome");          //        nome = "Alex Batista";

        Float     valor1 = args.getFloat("valor1");    //      valor1 = 9.00;
        Double    valor2 = args.getDouble("valor2");   //      valor2 = 5.50;
        Integer   valor3 = args.getInt("valor3");      //      valor3 = 20;
        Character valor4 = args.getChar("valor4");     //      valor4 = 'A';

        String login = args.getString("acesso");      //        login = acesso;

        // 20. Adiciona o nome do usuário na barra de título da tela bem-vindo.
        setTitle("Bem-vindo: " + login);

        //Log.i(TAG, getClassName() + " => Variáveis receberam seus dados!!!");

        // 21. Associar os componentes de tela (View)
        //     aos objetos Java (Controller).
        text1 = (TextView) findViewById(R.id.textView1);
        text2 = (TextView) findViewById(R.id.textView2);
        text3 = (TextView) findViewById(R.id.textView3);
        text4 = (TextView) findViewById(R.id.textView4);
        text5 = (TextView) findViewById(R.id.textView5);
        text6 = (TextView) findViewById(R.id.textView6);

        // 22. atualizar o texto do TextView1
        // com uma msg de texto de bem-vindo
        String saudacao = "Olá " + nome + ", \n seja bem-vindo!!!";
        text1.setLines(2); // define 2 linhas no objeto text1
        text1.setText(saudacao); // quebra em dusa linhas o conteudo

        // 23. saida de texto convertida com o metodo format da classe String
        text2.setText(String.format("Valor1 : %s", valor1));

        // 24. saida de texto convertida com o metodo format da classe MessageFormat do Java
        text3.setText(MessageFormat.format("Valor2: {0}", String.valueOf(valor2)));

        // 25. saida de texto com uso de objeto sb da classe StringBuilder(linguagem Java)
        // stringbuilder basicamente transforma uma string em uma string de saida
        StringBuilder sb = new StringBuilder();
        sb.append("Valor 3: ").append(String.valueOf(valor3));
        text4.setText(sb);

        // 26. Saída de texto convertida com o metodo format da classe String.
        text5.setText(String.format("Valor4 : %s", valor4));

        // 27. Gerado um cálculo para mostra na saída (text6) convertida
        //     com o metodo format da classe String.
        Double resultado = valor1 - valor2;
        text6.setText(String.format("Resultado: %s", resultado));

        // Log.i(TAG, getClassName() + " => Todos os dados foram exibidos no aplicativo!!!");
    }
}