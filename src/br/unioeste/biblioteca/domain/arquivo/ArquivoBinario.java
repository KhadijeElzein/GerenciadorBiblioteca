package br.unioeste.biblioteca.domain.arquivo;

import br.unioeste.biblioteca.domain.Biblioteca;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ArquivoBinario {
    private String nomeArquivo;

    public ArquivoBinario(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public void armazenarDados(Biblioteca biblioteca) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.nomeArquivo));
            objectOutputStream.writeObject(biblioteca);
            objectOutputStream.close();
        } catch (Exception e) {
            System.out.println("Erro ao gravar arquivo de armazenamento.");
            e.printStackTrace();
        }
    }

    public Biblioteca obterDados() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(this.nomeArquivo));
            Biblioteca biblioteca = (Biblioteca) objectInputStream.readObject();
            objectInputStream.close();
            return biblioteca;
        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo de armazenamento.");
            e.printStackTrace();
            return null;
        }
    }
}
