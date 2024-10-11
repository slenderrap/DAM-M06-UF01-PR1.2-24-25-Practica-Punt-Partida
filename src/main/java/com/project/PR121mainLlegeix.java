package com.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import com.project.excepcions.IOFitxerExcepcio;
import com.project.objectes.PR121hashmap;

public class PR121mainLlegeix {
    private static String filePath = System.getProperty("user.dir") + "/data/PR121HashMapData.ser";

    public static void main(String[] args) {
        try {
            PR121hashmap hashMap = deserialitzarHashMap();
            hashMap.getPersones().forEach((nom, edat) -> System.out.println(nom + ": " + edat + " anys"));
        } catch (IOFitxerExcepcio e) {
            System.err.println("Error al llegir l'arxiu: " + e.getMessage());
        }
    }

    public static PR121hashmap deserialitzarHashMap() throws IOFitxerExcepcio {
        File arxiu = new File(getFilePath());
        try {


            if (!arxiu.exists()){
                System.out.println("L'arxiu no existeix");
                throw new IOException();
            }else if (!arxiu.isFile()){
                System.out.println("No es un arxiu");
                throw new IOException();
            }
            FileInputStream fis = new FileInputStream(arxiu);
            ObjectInputStream ois = new ObjectInputStream(fis);

            PR121hashmap hashmap = (PR121hashmap) ois.readObject();
            System.out.println("L'arxiu s'ha llegit correctament");
            return hashmap;

        }    catch (IOException | ClassNotFoundException e){
            throw new IOFitxerExcepcio("Error en deserialitzar l'objecte HashMap");
        }
    }

    // Getter
    public static String getFilePath() {
        return filePath;
    }

    // Setter
    public static void setFilePath(String newFilePath) {
        filePath = newFilePath;
    }    
}