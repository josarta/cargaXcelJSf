/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.archivos.controlador;

import edu.archivos.entity.Ciudades;
import edu.archivos.entity.Departamentos;
import edu.archivos.entity.Rutas;
import java.io.File;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 *
 * @author JoseLuis
 */
@Named(value = "archivosControladorJSF")
@SessionScoped
public class ArchivosControladorJSF implements Serializable {

    @EJB
    private edu.archivos.facade.RutasFacade rutasFacade;

    @EJB
    private edu.archivos.facade.CiudadesFacade ciudadesFacade;

    @EJB
    private edu.archivos.facade.DepartamentosFacade departamentosFacade;

    private String rutaLibro;
    private Rutas miRuta = new Rutas();
    private Workbook archivoExcel;
    private Sheet miSucursal;
    private Sheet miRut;
    private Sheet miUsuario;
    private Sheet miCiudad;
    private Sheet miDepartamento;
    private Sheet miPerfil;
    private Sheet miTarifas;
    private Sheet miVehiculo;
    private String ciudadO;
    private int rutaID;
    private String ciudadD;
    private String Tiempo;
    private String opcion;

    /**
     * Creates a new instance of ArchivosControladorJSF
     */
    public ArchivosControladorJSF() {
    }

    public Workbook getArchivoExcel() {
        return archivoExcel;
    }

    public void setArchivoExcel(Workbook archivoExcel) {
        this.archivoExcel = archivoExcel;
    }

    public Rutas getMiRuta() {
        return miRuta;
    }

    public void setMiRuta(Rutas miRuta) {
        this.miRuta = miRuta;
    }

    public boolean ingresarCiudad() {

        Ciudades miciudad = new Ciudades();
        miciudad.setCiudadID(rutaID);
        miciudad.setNombre(ciudadD);

        Departamentos mideparta = new Departamentos();
        mideparta.setDepartamentoID(Integer.parseInt(ciudadO));
        miciudad.setDepartamentoID(mideparta);

        try {
            ciudadesFacade.create(miciudad);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    
    public boolean ingresarDepartamento(){
    
        Departamentos mideparta = new Departamentos();
        mideparta.setDepartamentoID(rutaID);
        mideparta.setNombre(ciudadD);
        try {
            departamentosFacade.create(mideparta);
            return true;
        } catch (Exception e) {
            return false;
        }
    
    }
    

    public boolean ingresarRuta() throws ParseException {

        Ciudades ciudadOrigen = new Ciudades();
        ciudadOrigen.setCiudadID(Integer.parseInt(ciudadO));

        Ciudades ciudadDestino = new Ciudades();
        ciudadDestino.setCiudadID(Integer.parseInt(ciudadD));

        miRuta.setCiudadOrigen(ciudadOrigen);
        miRuta.setCiudadDestino(ciudadDestino);

        Date date = new SimpleDateFormat("HH:mm:ss").parse(Tiempo);
        miRuta.setTiempo(date);

        miRuta.setRutaID(rutaID);

        try {
            rutasFacade.create(miRuta);
            return true;

        } catch (Exception e) {
            return false;
        }

    }

    public String getCiudadO() {
        return ciudadO;
    }

    public void setCiudadO(String ciudadO) {
        this.ciudadO = ciudadO;
    }

    public String getCiudadD() {
        return ciudadD;
    }

    public void setCiudadD(String ciudadD) {
        this.ciudadD = ciudadD;
    }

    public String getTiempo() {
        return Tiempo;
    }

    public void setTiempo(String Tiempo) {
        this.Tiempo = Tiempo;
    }

    public int getRutaID() {
        return rutaID;
    }

    public void setRutaID(int rutaID) {
        this.rutaID = rutaID;
    }

    /**
     * @return the opcion
     */
    public String getOpcion() {
        return opcion;
    }

    /**
     * @param opcion the opcion to set
     */
    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public String cargarPagina() {
        return "";
    }

    public String getRutaLibro() {
        return rutaLibro;
    }

    public void setRutaLibro(String rutaLibro) {
        this.rutaLibro = rutaLibro;
    }

    public void cargarRuta(String ruta) throws IOException, BiffException {
        if (!ruta.equals("")) {
            this.rutaLibro = ruta;
            archivoExcel = Workbook.getWorkbook(new File(rutaLibro));
            miSucursal = archivoExcel.getSheet(0);
            miCiudad = archivoExcel.getSheet(1);
            miDepartamento = archivoExcel.getSheet(2);
            miPerfil = archivoExcel.getSheet(3);
            miRut = archivoExcel.getSheet(4);
            miTarifas = archivoExcel.getSheet(5);
            miVehiculo = archivoExcel.getSheet(6);
            miUsuario = archivoExcel.getSheet(7);

        }
    }

    public Sheet getMiRut() {
        return miRut;
    }

    public void setMiRut(Sheet miRut) {
        this.miRut = miRut;
    }

    public Sheet getMiUsuario() {
        return miUsuario;
    }

    public void setMiUsuario(Sheet miUsuario) {
        this.miUsuario = miUsuario;
    }

    public Sheet getMiCiudad() {
        return miCiudad;
    }

    public void setMiCiudad(Sheet miCiudad) {
        this.miCiudad = miCiudad;
    }

    public Sheet getMiDepartamento() {
        return miDepartamento;
    }

    public void setMiDepartamento(Sheet miDepartamento) {
        this.miDepartamento = miDepartamento;
    }

    public Sheet getMiPerfil() {
        return miPerfil;
    }

    public void setMiPerfil(Sheet miPerfil) {
        this.miPerfil = miPerfil;
    }

    public Sheet getMiTarifas() {
        return miTarifas;
    }

    public void setMiTarifas(Sheet miTarifas) {
        this.miTarifas = miTarifas;
    }

    public Sheet getMiVehiculo() {
        return miVehiculo;
    }

    public void setMiVehiculo(Sheet miVehiculo) {
        this.miVehiculo = miVehiculo;
    }

    public Sheet getMiSucursal() {
        return miSucursal;
    }

    public void setMiSucursal(Sheet miSucursal) {
        this.miSucursal = miSucursal;
    }

}
