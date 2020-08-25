package br.unioeste.biblioteca;

import java.io.*;
import java.util.Scanner;

public class Arquivo {
    private String nomeArquivo;

    public Arquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public void lerArquivo() {
        try {
            File myObj = new File(this.nomeArquivo);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void escreverArquivo() {
        try {
            File myObj = new File(this.nomeArquivo);
            if (myObj.createNewFile()) {
                System.out.println("Arquivo criado: " + myObj.getName());
            } else {
                System.out.println("Arquivo já existe.");
            }
            BufferedWriter myWriter = new BufferedWriter(new FileWriter(this.nomeArquivo, true));
            myWriter.write("\nTeste escrita");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
