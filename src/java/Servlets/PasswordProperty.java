/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.FileOutputStream;
import java.util.Properties;

/**
 *
 * @author Bilal
 */
public class PasswordProperty {
public static void main(String arg[])throws Exception
{
    Properties p=new Properties();
    p.setProperty("Muhammad","Bilal" );
    p.setProperty("Imran", "bell");
    p.setProperty("sachal", "labs");
    FileOutputStream f=new FileOutputStream(" paawords.properties");
    p.save(f,"MyPasswords");
    f.close();
    System.out.println("Successfully Build Password");
}
}
