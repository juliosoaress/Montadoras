package com.example.teste1;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Montadora implements Serializable {//Serializable?
    private int id;
    private String nomeMontadora;//como gerar os get / set de forma automática?

    public Montadora()
    {


    }

    public void setNomeMontadora(String nomeMontadora) { this.nomeMontadora = nomeMontadora; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNomeMontadora() { return nomeMontadora; }

    public Montadora(String nomeMontadora) {
        this.nomeMontadora = nomeMontadora;
    }

    @NonNull//?
    @Override//?
    public String toString() { return nomeMontadora; }//

    public boolean temIDValido() { return id > 0; }//?

}
