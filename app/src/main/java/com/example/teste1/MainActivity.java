package com.example.teste1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Montadoras";
    public static final String CHAVE_MONTADORA = "montadora";
    private final MontadoraDAO dao = new MontadoraDAO();
    private ArrayAdapter<Montadora> adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(TITULO_APPBAR);
        configuraFabNovaMontadora();
        configuraLista();
        dao.salva(new Montadora("Chevrolet"));
        dao.salva(new Montadora("Chevrolet"));
    }

    @Override
    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_montadora_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item){
        int idMenu = item.getItemId();
        if (idMenu == R.id.activity_formulario_montadora_menu_remover) {
            AdapterView.AdapterContextMenuInfo itemAdapter = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Montadora montadoraEscolhida = adapter.getItem(itemAdapter.position);
            remove(montadoraEscolhida);
        }

        return super.onContextItemSelected(item);
    }

    private void configuraFabNovaMontadora () {
        Button botaoNovaMontadora = findViewById(R.id.button);
        botaoNovaMontadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    abreFormularioModoInsereMontadora();
                }

            private void abreFormularioModoInsereMontadora() {

                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }

        });

    }

    @Override
    protected void onResume () {
        super.onResume();
        atualizaMontadoras ();
    }

    private void atualizaMontadoras () {
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    private void configuraLista () {
        ListView listaDeMontadoras = findViewById(R.id.nome);
        configuraAdapter(listaDeMontadoras);
        configuraListenerDeCliquePorItem(listaDeMontadoras);
        registerForContextMenu(listaDeMontadoras);
    }

    private void remove (Montadora montadora) {
        dao.remove(montadora);
        adapter.remove(montadora);
    }

    private void configuraListenerDeCliquePorItem(ListView listaDeMontadoras) {
        listaDeMontadoras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Montadora montadoraEscolhida = (Montadora) adapterView.getItemAtPosition(position);
                abreFormularioEditaMontadora(montadoraEscolhida);
            }

        });
    }

    private void abreFormularioEditaMontadora(Montadora montadoraEscolhida) {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        intent.putExtra(CHAVE_MONTADORA, montadoraEscolhida);
        startActivity(intent);
    }

    private void configuraAdapter(ListView listaDeMontadoras) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listaDeMontadoras.setAdapter(adapter);
    }
}





