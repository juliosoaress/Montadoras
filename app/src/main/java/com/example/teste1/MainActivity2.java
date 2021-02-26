package com.example.teste1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVA_MONTADORA = "Nova montadora";
    private static final String TITULO_APPBAR_EDITA_MONTADORA = "Editar montadora";
    private EditText campoMontadora;
    private final MontadoraDAO dao = new MontadoraDAO();
    private Montadora montadora;
    private Button button;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        inicializacaoCampos();
        carregaMontadora();
    }

    //listener para o clique do botão salvar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_formulario_montadora_menu_salvar) {
            preencheMontadora();
            salvaMontadora();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //cria o menu para utilizar o botão salvar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_montadora_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    private void carregaMontadora() {
        Intent dados = getIntent();
        if (dados.hasExtra(MainActivity.CHAVE_MONTADORA)) {
            montadora = (Montadora) dados.getSerializableExtra(MainActivity.CHAVE_MONTADORA);
            preencheCampos();
            setTitle(TITULO_APPBAR_EDITA_MONTADORA);}
        else {
            montadora = new Montadora();
            setTitle(TITULO_APPBAR_NOVA_MONTADORA);
        }
    }

    private void preencheCampos() {
        campoMontadora.setText(montadora.getNomeMontadora());
    }

    private void salvaMontadora() {
        if (montadora.temIDValido())
            dao.edita(montadora);
        else
            dao.salva(montadora);
    }


    private void inicializacaoCampos() {
        campoMontadora = findViewById(R.id.montadoras);
    }

    private void preencheMontadora() {
        String nomeMontadora = campoMontadora.getText().toString();

        montadora.setNomeMontadora(nomeMontadora);
    }
}