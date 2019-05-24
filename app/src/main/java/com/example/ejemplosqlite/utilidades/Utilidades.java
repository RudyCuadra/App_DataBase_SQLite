package com.example.ejemplosqlite.utilidades;

public class Utilidades {

    //Constantes campos tabla usuario
    public static final String TABLA_USUARIO = "usuario";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOMRRE = "nombre";
    public static final String CAMPO_TELEFONO = "telefono";


    public static final String CREAR_TABLA_USUARIO="CREATE TABLE" +
            " "+TABLA_USUARIO+" ("+CAMPO_ID+" " +
            "INTEGER, "+CAMPO_NOMRRE+" TEXT, "+CAMPO_TELEFONO+" TEXT)";

}
