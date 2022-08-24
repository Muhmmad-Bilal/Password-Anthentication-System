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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.util.Properties;

/**
 *
 * @author Bilal
 */
public class Anthentication extends HttpServlet {
Properties p;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *`
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

public void init(ServletConfig cong)throws ServletException
{
    
}

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String name=request.getParameter("user");
            String password=request.getParameter("password");
            System.out.println(name);
            System.out.println(password);
            
           p= new Properties();
            FileInputStream f=new FileInputStream("Servlets\\paawords.properties");
            p.load(f);
            System.out.println("Passwords Successfully loaded");
            String realPassword=p.getProperty(name);
            System.out.println(realPassword);
            if(realPassword==null)
            {
                reSendLoginPage(out,response,"Invalid User");          
            }
            else
                {
                    
                        if(realPassword.equals(password))
                        {
                            response.sendRedirect("index.html");
                        }
                        else
                        {
                            reSendLoginPage(out,response,"Invalid User");
                        }

                        }
        }

      
        }
    
        public void welcome(PrintWriter out)
        throws IOException{
            out.println("Welcome");
}
        public void reSendLoginPage(PrintWriter out,HttpServletResponse response,String str)
        throws ServletException{
            try
            {
            response.sendRedirect("index.html");
        }catch(Exception e)
        {
            e.printStackTrace();
        }
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
