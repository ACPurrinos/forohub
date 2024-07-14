package com.acpurrinos.forohub.models;

public enum Curso {
    // Curso 1
    PROGRAMACION_WEB("Introducción a la Programación Web", "Programación"),

    // Curso 2
    DISENO_GRAFICO("Diseño Gráfico para principiantes", "Diseño"),

    // Curso 3
    MARKETING_DIGITAL("Marketing Digital: estrategias y herramientas", "Marketing"),

    // Curso 4
    DESARROLLO_MOVIL("Desarrollo de aplicaciones móviles con Android", "Programación"),

    // Curso 5
    ANALISIS_DE_DATOS("Análisis de datos con Python", "Análisis de Datos");

    private final String nombreCurso;
    private final String categoria;

    Curso(String nombre_curso, String categoria) {
        this.nombreCurso = nombre_curso;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombreCurso;
    }

    public String getCategoria() {
        return categoria;
    }
}

