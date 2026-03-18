package com.example.cara_coroa;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Random random = new Random();

    Button jogarMoeda;
    ImageView cara;
    ImageView coroa;
    ImageView demonstracaoCara;
    ImageView demonstracaoCoroa;

    jogar escolhaUsuario;
    public enum jogar {
        cara,
        coroa
    }

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

        //instanciando os componentes

        jogarMoeda = findViewById(R.id.jogarMoeda);
        cara = findViewById(R.id.cara);
        coroa = findViewById(R.id.coroa);
        demonstracaoCara = findViewById(R.id.demonstracaoCara);
        demonstracaoCoroa = findViewById(R.id.demonstracaoCoroa);

        //caso o usuário escolha cara
        demonstracaoCara.setOnClickListener(v -> {
            escolhaUsuario = jogar.cara;
            Toast.makeText(this, "Você escolheu cara.", Toast.LENGTH_SHORT).show();
        });

        //caso o usuário escolha coroa
        demonstracaoCoroa.setOnClickListener(v -> {
            escolhaUsuario = jogar.coroa;
            Toast.makeText(this, "Você escolheu coroa.", Toast.LENGTH_SHORT).show();
        });

        jogarMoeda.setOnClickListener(v -> jogarMoedaEscolhida());
    }

    //metodo para sortear a moeda
    public jogar sortearJogada() {
        jogar[] jogadas = jogar.values();
        return jogadas[random.nextInt(jogadas.length)];
    }

    public void jogarMoedaEscolhida() {

        //caso o usuário não escolha nenhuma opção
        if (escolhaUsuario == null) {
            Toast.makeText(this, "Escolha uma opção.", Toast.LENGTH_SHORT).show();
            return;
        }

        //pegando o resultado baseado no enum
        jogar resultado = sortearJogada();

        //verificando as jogadas possíveis.
        if (resultado == jogar.cara) {
            cara.setVisibility(View.VISIBLE);
            coroa.setVisibility(View.INVISIBLE);
        } else {
            cara.setVisibility(View.INVISIBLE);
            coroa.setVisibility(View.VISIBLE);
        }

        if (resultado == escolhaUsuario) {
            Toast.makeText(this, "Você ganhou!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Você perdeu!", Toast.LENGTH_SHORT).show();
        }

    }
}