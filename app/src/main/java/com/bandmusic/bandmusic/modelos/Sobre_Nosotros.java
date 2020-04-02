package com.bandmusic.bandmusic.modelos;

import java.io.Serializable;

public class Sobre_Nosotros implements Serializable {
    private String Nombre, Profesion, Correo, Celular;
    private int Imagen;


    public Sobre_Nosotros(String nombre) {
        Nombre = nombre;
    }

    public Sobre_Nosotros(String nombre, String profesion, String correo, String celular) {
        Nombre = nombre; Correo = correo;
        Profesion = profesion; Celular = celular;
    }

    public Sobre_Nosotros(String nombre, String profesion, String correo, String celular, int imagen) {
        Nombre = nombre; Correo = correo;
        Profesion = profesion; Celular = celular;
        Imagen = imagen;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getProfesion() {
        return Profesion;
    }

    public void setProfesion(String profesion) {
        Profesion = profesion;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }
    public String getCelular() {
        return Celular;
    }

    public void setCelular(String celular) {
        Celular = celular;
    }

    public int getImagen() {
        return Imagen;
    }

    public void setImagen(int imagen) {
        Imagen = imagen;
    }
}