package com.project;

import java.io.*;

import com.project.excepcions.IOFitxerExcepcio;
import com.project.objectes.PR121hashmap;

public class PR121mainEscriu {
    private static String filePath = System.getProperty("user.dir") + "/data/PR121HashMapData.ser";

    public static void main(String[] args) {
        PR121hashmap hashMap = new PR121hashmap();
        hashMap.getPersones().put("Anna", 25);
        hashMap.getPersones().put("Bernat", 30);
        hashMap.getPersones().put("Carla", 22);

        try {
            serialitzarHashMap(hashMap);
        } catch (IOFitxerExcepcio e) {
            System.err.println("Error al desar l'arxiu: " + e.getMessage());
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

    public static void serialitzarHashMap(PR121hashmap hashMap) throws IOFitxerExcepcio {
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
        try {
            FileOutputStream fos = new FileOutputStream(arxiu);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(hashMap);

            System.out.println("L'arxiu s'ha creat correctament");
        } catch (IOException e) {
            throw new IOFitxerExcepcio("No s'ha pogut obrir l'arxiu d'escriptura");
        }

    }
}