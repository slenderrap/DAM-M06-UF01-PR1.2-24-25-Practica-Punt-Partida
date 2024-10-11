package com.project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.project.excepcions.IOFitxerExcepcio;
import com.project.objectes.PR122persona;

public class PR122main {
    private static String filePath = System.getProperty("user.dir") + "/data/PR122persones.dat";

    public static void main(String[] args) {
        List<PR122persona> persones = new ArrayList<>();
        persones.add(new PR122persona("Maria", "López", 36));
        persones.add(new PR122persona("Gustavo", "Ponts", 63));
        persones.add(new PR122persona("Irene", "Sales", 54));

        try {
            serialitzarPersones(persones);
            List<PR122persona> deserialitzades = deserialitzarPersones();
            deserialitzades.forEach(System.out::println);  // Mostra la informació per pantalla
        } catch (IOFitxerExcepcio e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Mètode per serialitzar la llista de persones
    public static void serialitzarPersones(List<PR122persona> persones) throws IOFitxerExcepcio {
        File arxiu = new File(getFilePath());
        if (!arxiu.exists()){
            try {
                if (arxiu.createNewFile()){
                    System.out.println("L'arxiu s'ha generat correctament");
                }
            } catch (IOException e) {
                throw new IOFitxerExcepcio("No s'ha pogut crear correctament");
            }
        }else {
            System.out.println("L'arxiu ja existeix");
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(arxiu);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(persones);
            System.out.println("L'arxiu s'ha omplert correctament");
        } catch (IOException e) {
            throw new IOFitxerExcepcio("S'ha produir un error al crear el fitxer");
        }finally {

            try {
                assert fos != null;
                fos.close();
            } catch (IOException e) {
                throw new IOFitxerExcepcio("S'ha produit un error en tancar l'arxiu");
            }
        }

    }

    // Mètode per deserialitzar la llista de persones
    public static List<PR122persona> deserialitzarPersones() throws IOFitxerExcepcio {

        File arxiu = new File(getFilePath());
        try {


            if (!arxiu.exists()) {
                System.out.println("EL fitxer no s'ha trobat");
                throw new IOException();
            } else if (!arxiu.isFile()) {
                System.out.println("El fitxer no s'ha trobat");
                throw new IOException();
            }
            FileInputStream fis = new FileInputStream(arxiu);
            ObjectInputStream ois = new ObjectInputStream(fis);

            List<PR122persona> personas = (List<PR122persona>) ois.readObject();
            fis.close();
            System.out.println("S'ha desserialitzat correctament");
            return personas;
        } catch (IOException e) {
            throw new IOFitxerExcepcio("Fitxer no trobat");
        }catch (ClassNotFoundException e){
            throw new IOFitxerExcepcio("Erro al desserialitzar l'arxiu");
        }
    }
        // Getter i Setter per a filePath (opcional)
    public static String getFilePath() {
        return filePath;
    }

    public static void setFilePath(String newFilePath) {
        filePath = newFilePath;
    }
}
