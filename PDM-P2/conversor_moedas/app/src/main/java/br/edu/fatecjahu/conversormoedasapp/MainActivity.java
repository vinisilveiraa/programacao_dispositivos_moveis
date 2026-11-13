package br.edu.fatecjahu.conversormoedasapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private EditText valorReal;
    private Spinner spinnerMoedas;
    private Button botaoConverter;
    private TextView resultado;
    private EditText valorReal2;
    private Spinner spinnerMoedas2;
    private Button botaoConverter2;
    private TextView resultado2;
    private OkHttpClient client;
    private OkHttpClient client2;
    private ExecutorService executor;
    private ExecutorService executor2;
    private Handler mainHandler;
    private Handler mainHandler2;

    private String[] moedas = {"USD (Dólar)", "EUR (Euro)", "GBP (Libra)"};
    private String[] codigosMoedas = {"USD", "EUR", "GBP"};
    private String urlApi = "https://api.exchangerate-api.com/v4/latest/";

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


        inicializarComponentes();
        configurarSpinner();
        configurarExecutorEHandler();
        configurarBotao();

        inicializarComponentes2();
        configurarSpinner2();
        configurarExecutorEHandler2();
        configurarBotao2();
    }

    private void inicializarComponentes() {
        valorReal = findViewById(R.id.edtValorReal);
        spinnerMoedas = findViewById(R.id.spinnerMoedas);
        botaoConverter = findViewById(R.id.btnConverter);
        resultado = findViewById(R.id.tvResultado);

        client = new OkHttpClient();

    }

    private void inicializarComponentes2() {
        valorReal2 = findViewById(R.id.edtValorReal2);
        spinnerMoedas2 = findViewById(R.id.spinnerMoedas2);
        botaoConverter2 = findViewById(R.id.btnConverter2);
        resultado2 = findViewById(R.id.tvResultado2);

        client2 = new OkHttpClient();
    }

    private void configurarExecutorEHandler() {
        executor = Executors.newSingleThreadExecutor();
        mainHandler = new Handler(Looper.getMainLooper());
    }
    private void configurarExecutorEHandler2() {
        executor2 = Executors.newSingleThreadExecutor();
        mainHandler2 = new Handler(Looper.getMainLooper());
    }

    private void configurarSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, moedas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMoedas.setAdapter(adapter);
    }

    private void configurarSpinner2() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, moedas);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMoedas2.setAdapter(adapter);
    }

    private void configurarBotao() {
        botaoConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String valorStr = valorReal.getText().toString().trim();
                if (!valorStr.isEmpty()) {
                    double valor = Double.parseDouble(valorStr);
                    int posicao = spinnerMoedas.getSelectedItemPosition();
                    String codigoMoeda = codigosMoedas[posicao];
                    executarConversao(valor, codigoMoeda);
                } else {
                    resultado.setText(R.string.texto_msg_digite_um_valor_valido_em_reais);
                }
            }
        });
    }
    private void configurarBotao2() {
        botaoConverter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String valorStr = valorReal2.getText().toString().trim();
                if (!valorStr.isEmpty()) {
                    double valor = Double.parseDouble(valorStr);
                    int posicao = spinnerMoedas2.getSelectedItemPosition();
                    String codigoMoeda = codigosMoedas[posicao];
                    executarConversao2(valor, codigoMoeda);
                } else {
                    resultado2.setText(R.string.texto_msg_digite_um_valor_valido_estrangeiro);
                }

            }
        });
    }

    private void executarConversao(double valorReal, String moedaDestino) {
        // dentro do executor as coisas acontecem de forma assincrona
        executor.execute(() -> {
            String resultadoConversao = fazerConversaoEmBackGround(valorReal, moedaDestino);

            // de volta pra thread principal pra atualizar a tela
            mainHandler.post(() -> {
                resultado.setText(resultadoConversao);
            });
        });
    }
    private void executarConversao2(double valorEstrangeiro, String moedaOrigem) {
        // dentro do executor as coisas acontecem de forma assincrona
        executor2.execute(() -> {
            String resultadoConversao2 = fazerConversaoEmBackGround2(valorEstrangeiro, moedaOrigem);

            // de volta pra thread principal pra atualizar a tela
            mainHandler2.post(() -> {
                resultado2.setText(resultadoConversao2);
            });
        });
    }

    private String fazerConversaoEmBackGround(double valorReal, String moedaDestino) {
        try {
            // cria a requisicao http com a url definida
            Request request = new Request.Builder().url(urlApi + "BRL").build();
            // executa a request
            Response response = client.newCall(request).execute();

            if (response.isSuccessful() && response.body() != null) {

                // extrai o texto retornado pela api
                String jsonData = response.body().string();
                // converte pra um objeto json
                JSONObject jsonObject = new JSONObject(jsonData);

                // navega ate o bloco rates dentro do json
                JSONObject rates = jsonObject.getJSONObject("rates");

                // pega a taxa escolhida e faz o calculo da conversao
                double taxa = rates.getDouble(moedaDestino);
                double resultadoConversao = valorReal * taxa;

                // formata o texto e retorna ele
                @SuppressLint("DefaultLocale")
                String resultadoFinal =
                        String.format("R$ %.2f = %.2f %s\n(Taxa: 1 BRL = %.4f %s)",
                                valorReal, resultadoConversao, moedaDestino, taxa, moedaDestino);

                return resultadoFinal;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getString(R.string.texto_msg_erro_ao_obter_taxa_de_cambio_verifique_sua_conexao);
    }

    private String fazerConversaoEmBackGround2(double valorEstrangeiro, String moedaOrigem) {
        try {

            String apiFinal = urlApi + moedaOrigem;

            Request request = new Request.Builder().url(apiFinal).build();
            Response response = client2.newCall(request).execute();

            if (response.isSuccessful() && response.body() != null) {

                String jsonData = response.body().string();
                JSONObject jsonObject = new JSONObject(jsonData);
                JSONObject rates = jsonObject.getJSONObject("rates");

                double taxa2 = rates.getDouble("BRL");
                double resultadoConversao2 = valorEstrangeiro * taxa2;

                // formata o texto e retorna ele
                @SuppressLint("DefaultLocale")

                String resultadoFinal2 =
                        String.format("%.2f %s = R$ %.2f\n(Taxa: 1 %s = %.4f BRL)",
                                valorEstrangeiro, moedaOrigem, resultadoConversao2, moedaOrigem, taxa2);

                return resultadoFinal2;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getString(R.string.texto_msg_erro_ao_obter_taxa_de_cambio_verifique_sua_conexao);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (executor != null && !executor.isShutdown()) {
            executor.shutdown();
        }
        if (executor2 != null && !executor2.isShutdown()) {
            executor2.shutdown();
        }
    }
}