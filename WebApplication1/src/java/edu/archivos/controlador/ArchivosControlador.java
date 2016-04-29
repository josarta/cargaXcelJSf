/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.archivos.controlador;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author JoseLuis
 */
public class ArchivosControlador extends HttpServlet {
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
           
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);

            final String UPLOAD_DIRECTORY = getServletContext().getRealPath("/Archivos");

            if(isMultipart){
                // Create a factory for disk-based file items
                FileItemFactory factory = new DiskFileItemFactory();
                
                 // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);
                
                String name = "";
                try {
                    // Parse the request
                    List<FileItem> multiparts = upload.parseRequest(request);

                    for (FileItem item : multiparts) {
                        if (!item.isFormField()) {
                            name = new File(item.getName()).getName();
                            item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
                        }
                    }
                   
                    request.setAttribute("ruta",UPLOAD_DIRECTORY +"/"+ name);
                                  
                   
                
                } catch (Exception e) {
                    
                    request.setAttribute("message", "Cargar archivo ha fallado debido a " + e);
                }
                
                
            }else{
                
              request.getRequestDispatcher("/faces/index.xhtml").forward(request, response);
            }
            
             request.getRequestDispatcher("/faces/muestraCarga.xhtml").forward(request, response);
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
