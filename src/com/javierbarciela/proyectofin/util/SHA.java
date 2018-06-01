/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author iñigo
 */
public class SHA {
    
    private static HashMap<String,String> palabras = new HashMap<>();
    
    private static ArrayList<String> passwd = new  ArrayList<>();
    
    private static HashMap<String,String> digitos = new HashMap<>();
    
    private static ArrayList<String> caracteres = new ArrayList<>();
    
    private static HashMap<String,String> caracteresMap = new HashMap<>();
    
    public static String getSHA(String password){
        String hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes("UTF-8")); 
            byte[] digest = md.digest();
            hash = String.format("%064x", new java.math.BigInteger(1, digest));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SHA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SHA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hash;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String resp1 = JOptionPane.showInputDialog(null, "Introduce una contraseña:");
        String resp2 = JOptionPane.showInputDialog(null, "Introduce una contraseña:");
        String resp3 = JOptionPane.showInputDialog(null, "Introduce una contraseña:");
        String resp4 = JOptionPane.showInputDialog(null, "Introduce una contraseña:");
        String sha1 = getSHA(resp1);
        String sha2 = getSHA(resp2);
        String sha3 = getSHA(resp3);
        String sha4 = getSHA(resp4);
        JOptionPane.showMessageDialog(null, getSHA(resp1));
        /*leerPalabras();
        leerPasswd();
        compararPasswd();
        ficheroDigitos();
        leerDigitos();
        compararPasswd2();
        ficheroCaracteres();
        leerCaracteres();
        compararPasswd3();*/
    }
    
    private static void leerPalabras() throws FileNotFoundException, IOException {
        //String nombreArchivo = JOptionPane.showInputDialog(null, "Introduce el nombre del fichero");
        String cadena;
        FileReader f = new FileReader("palabras.txt"/*nombreArchivo*/);
        BufferedReader b = new BufferedReader(f);
        System.out.println("Generando mapa de claves (palabras)...");
        while((cadena = b.readLine())!=null) {
            int pos = cadena.indexOf(",");
            palabras.put(cadena.substring(0, pos), cadena.substring(pos + 1));
        }      
        b.close();
    }
    
    private static void leerPasswd() throws FileNotFoundException, IOException{
        FileReader f = new FileReader("passwd");
        BufferedReader b = new BufferedReader(f);
        String cadena;
        while ((cadena = b.readLine()) != null) {
            passwd.add(cadena);
        }
        b.close();
    }
    
    private static void leerDigitos() throws FileNotFoundException, IOException {
        //String nombreArchivo = JOptionPane.showInputDialog(null, "Introduce el nombre del fichero");
        String cadena;
        FileReader f = new FileReader("digitos.txt"/*nombreArchivo*/);
        BufferedReader b = new BufferedReader(f);
        System.out.println("Generando mapa de claves (digitos)...");
        while((cadena = b.readLine())!=null) {
            int pos = cadena.indexOf(",");
            digitos.put(cadena.substring(0, pos), cadena.substring(pos + 1));
        }      
        b.close();
    }
    
    private static void leerCaracteres() throws FileNotFoundException, IOException {
        //String nombreArchivo = JOptionPane.showInputDialog(null, "Introduce el nombre del fichero");
        String cadena;
        FileReader f = new FileReader("caracteres.txt"/*nombreArchivo*/);
        BufferedReader b = new BufferedReader(f);
        System.out.println("Generando mapa de claves (caracteres)...");
        while((cadena = b.readLine())!=null) {
            int pos = cadena.lastIndexOf(",");
            caracteresMap.put(cadena.substring(0, pos), cadena.substring(pos + 1));
        }      
        b.close();
    }
    
    private static void compararPasswd(){ // comparar con palabras
        
        for (String passwd : passwd){
        
            int pos = passwd.indexOf(",");
            String usuario = passwd.substring(0, pos);
            String encriptado = passwd.substring(pos + 1);
            
            for (Map.Entry<String, String> palabras : palabras.entrySet()) {
                if (encriptado.equals(palabras.getValue())){
                    System.out.println(usuario + " : " + palabras.getKey());
                }
                
            }
            
        }
          
    }
    
    private static void compararPasswd2(){ // comparar con digitos
        
        for (String passwd : passwd){
        
            int pos = passwd.indexOf(",");
            String usuario = passwd.substring(0, pos);
            String encriptado = passwd.substring(pos + 1);
            
            for (Map.Entry<String, String> digitos : digitos.entrySet()) {
                if (encriptado.equals(digitos.getValue())){
                    System.out.println(usuario + " : " + digitos.getKey());
                }
                
            }
            
        }
          
    }
    
    private static void compararPasswd3(){ // comparar con caracteres
        
        for (String passwd : passwd){
        
            int pos = passwd.lastIndexOf(",");
            String usuario = passwd.substring(0, pos);
            String encriptado = passwd.substring(pos + 1);
            
            for (Map.Entry<String, String> caracteresMap : caracteresMap.entrySet()) {
                if (encriptado.equals(caracteresMap.getValue())){
                    System.out.println(usuario + " : " + caracteresMap.getKey());
                }
                
            }
            
        }
          
    }
    
    private static void ficheroDigitos() throws IOException{
        File fichero = new File("digitos.txt");
        if (!fichero.exists()){
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
            
            for(int i=0; i<1000000; i++){
                bw.write(i + "," + getSHA(Integer.toString(i)));
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
    }
    
    private static void ficheroCaracteres() throws IOException{
        File fichero = new File("caracteres.txt");
        if(!fichero.exists()){
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
            for (char c = '('; c < 'z' + 1; c++){
                caracteres.add(Character.toString(c));
            }
            String s;
            for(int i = 0; i < caracteres.size(); i++){
                bw.write(caracteres.get(i) + "," + getSHA(caracteres.get(i)));
                bw.newLine();
                for(int j = 0; j < caracteres.size(); j++){
                    s = caracteres.get(i) + caracteres.get(j);
                    bw.write(s + "," + getSHA(s));
                    bw.newLine();
                    for(int k = 0; k < caracteres.size(); k++){
                        s = caracteres.get(i) + caracteres.get(j) + caracteres.get(k);
                        bw.write(s + "," + getSHA(s));
                        bw.newLine(); 
                        /*for(int l = 0; l < caracteres.size(); l++){
                        s = caracteres.get(i) + caracteres.get(j) + caracteres.get(k) + caracteres.get(l);
                        bw.write(s + "," + getSHA(s));
                        bw.newLine();
                            for(int m = 0; m < caracteres.size(); m++){
                            s = caracteres.get(i) + caracteres.get(j) + caracteres.get(k) + caracteres.get(l)
                                     + caracteres.get(m);
                            bw.write(s + "," + getSHA(s));
                            bw.newLine(); 
                            }
                        }*/
                    }
                }
            }
            
            bw.flush();
            bw.close();
        }
    }
    
}
