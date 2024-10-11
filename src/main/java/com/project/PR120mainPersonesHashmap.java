package com.project;

import java.io.*;
import java.util.HashMap;

import com.project.excepcions.IOFitxerExcepcio;

public class PR120mainPersonesHashmap {
    private static String filePath = System.getProperty("user.dir") + "/data/PR120persones.dat";

    public static void main(String[] args) throws IOFitxerExcepcio {
        HashMap<String, Integer> persones = new HashMap<>();
        persones.put("Anna", 25);
        persones.put("Bernat", 30);
        persones.put("Carla", 22);
        persones.put("David", 35);
        persones.put("Elena", 28);

        try {

            llegirPersones();
            escriurePersones(persones);

        } catch (IOFitxerExcepcio e) {
            throw new IOFitxerExcepcio("Error en llegir les persones del fitxer: " + filePath, e);
        }
    }

    // Getter per a filePath
    public static String getFilePath() {
        return filePath;
    }

    // Setter per a filePath
    public static void setFilePath(String newFilePath) {
        filePath = newFilePath;
    }

    // Mètode per escriure les persones al fitxer
    public static void escriurePersones(HashMap<String, Integer> persones) throws IOFitxerExcepcio {
        File arxiuEscritura = new File(getFilePath());
        if (!arxiuEscritura.exists()){
            try {
                if (arxiuEscritura.createNewFile()){
                    System.out.println("L'arxiu s'ha creat correctament");
                }else {
                    System.err.println("Hi ha hagut algun error a la creacio de l'arxiu");
                }
            } catch (IOException e) {
                throw new IOFitxerExcepcio("Error en llegir les persones del fitxer: " + filePath, e);
            }
        }
        FileOutputStream fos = null;
        DataOutputStream dos = null;


        try{
            fos = new FileOutputStream(arxiuEscritura);
            dos = new DataOutputStream(fos);

            for (String clave: persones.keySet()){
                dos.writeUTF(clave);
                dos.writeInt(persones.get(clave));
                dos.flush();
            }



        } catch (IOException e) {
            throw new IOFitxerExcepcio("Error en escriure les persones al fitxer:  " + filePath, e);

        } finally {
            try {
                if (fos!=null){fos.close();}
                if (dos!=null){dos.close();}

            } catch (IOException e) {
                throw new IOFitxerExcepcio("Error al tancar el fitxer: " + filePath, e);
            }
        }


    }

    // Mètode per llegir les persones des del fitxer
    public static void llegirPersones() throws IOFitxerExcepcio {
        File arxiuLectura = new File(getFilePath());
        if (!arxiuLectura.exists() && arxiuLectura.isFile()){
            System.err.println("L'arxiu no es pot obrir");
        }else {
            FileInputStream fis = null;
            DataInputStream dis = null;
            String nom;
            int edat;
            try {
                fis = new FileInputStream(arxiuLectura);
                dis = new DataInputStream(fis);
                while (dis.read()!=1){
                    nom = dis.readUTF();
                    edat = dis.readInt();
                    System.out.println(nom+": " + edat + " anys" );
}

            } catch (IOException e) {

                throw new IOFitxerExcepcio("Error en llegir les persones del fitxer: " + filePath, e);
            }finally {
                try {
                    if (fis!=null){fis.close();}
                    if (dis!=null){dis.close();}

                } catch (IOException e) {
                    throw new IOFitxerExcepcio("Error al tancar el fitxer: " + filePath, e);
                }
            }
        }
    }
}
