/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import jakarta.servlet.ServletConfig;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.StringTokenizer;
import sun.misc.BASE64Decoder;

/**
 *
 * @author Bilal
 */
public class ProtectPage extends HttpServlet {

    private Properties passwords;
    @Override
    public void init(ServletConfig conf)
    throws ServletException{
        super.init(conf);
        String filename="E:\\java_prog\\J2EE\\AnthenticationSystem\\src\\java\\Servlets\\paawords.properties";
        passwords=new Properties();
        try
        {
            FileInputStream f=new FileInputStream(filename);
            
            passwords.load(f);
            //passwords.clear();
            
            //passwords.clear();
            System.out.println("Password Loaded");
            
        }catch(Exception e)
        {
            e.printStackTrace();
        }
                
    }
    
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String auth=request.getHeader("Authorization");
            if(auth==null)
                askForPassword(response);
            else
            {
                String userInto=auth.substring(6).trim();
                BASE64Decoder decoder=new BASE64Decoder();
                String userAndPassword=new String(decoder.decodeBuffer(userInto));
                StringTokenizer tokens=new StringTokenizer(userAndPassword);
                System.out.println(userAndPassword);
                System.out.println(tokens);
                String user=tokens.nextToken();
                String password=tokens.nextToken();
                String realPassword=passwords.getProperty(user);
                System.out.println(realPassword);
                if(realPassword==null)
                    askForPassword(response);
                else
                {
                    if(realPassword.equals(password))
                        welcome(out);
                }
            }
        }
    }
    public void welcome(PrintWriter out)
    throws ServletException{
        out.println("<h1>Hello</h1>");
    }
    public void askForPassword(HttpServletResponse response)
    throws ServletException{
        response.setStatus(response.SC_UNAUTHORIZED);
        response.setHeader("WWW-Authenticate","BASIC realm=\'privileged-few'");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
