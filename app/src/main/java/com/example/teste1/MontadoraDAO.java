package com.example.teste1;

import java.util.ArrayList;
import java.util.List;

public class MontadoraDAO {

    private final static List<Montadora> montadoras = new ArrayList<>();//variavel com lista
    private int contadorDeIds = 1;

    public void salva(Montadora montadora) {//"Montadora é referente a instancia" e "montadora é valor"?
        montadora.setId(contadorDeIds);
        montadoras.add(montadora);
        atualizaId();
    }

    private void atualizaId() {
        contadorDeIds++;
    }

    public List<Montadora> todos() { return new ArrayList<>(montadoras); } //cópia da lista?

    public void edita(Montadora montadora) {
        Montadora montadoraEncontrada = buscaMontadoraId(montadora);
        if (montadoraEncontrada != null) {   //null = negativo, nulo
            montadoras.set(montadoras.indexOf(montadoraEncontrada), montadora);   //??
        }
    }

    private Montadora buscaMontadoraId(Montadora montadora) {
        for (Montadora a : montadoras) {
            if (a.getId() == montadora.getId()) {
                return a;
            }
        }
        return null;
    }

    public void remove(Montadora montadora) {
        Montadora montadoraEncontrada = buscaMontadoraId(montadora);
        if (montadoraEncontrada != null)
            montadoras.remove(montadoraEncontrada);
    }
}
