package org.example;

public abstract class Personaje {

    protected String nombre;
    protected String apodo;
    protected String raza;
    protected String fechaNacimiento;
    protected int edad;
    protected int nivel;
    protected int salud = 100;
    protected int velocidad;
    protected int destreza;
    protected int fuerza;
    protected int armadura;

    public Personaje(String nombre, String apodo, String raza, String fechaNacimiento, int edad, int nivel, int salud, int velocidad, int destreza, int fuerza, int armadura) {
        this.nombre = nombre;
        this.apodo = apodo;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.nivel = nivel;
        this.salud = salud;
        this.velocidad = velocidad;
        this.destreza = destreza;
        this.fuerza = fuerza;
        this.armadura = armadura;
    }

    @Override
    public String toString() {
        return "Personaje{" +
                "raza='" + raza + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apodo='" + apodo + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", edad=" + edad +
                ", salud=" + salud +
                ", velocidad=" + velocidad +
                ", destreza=" + destreza +
                ", fuerza=" + fuerza +
                ", armadura=" + armadura +
                '}';
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getArmadura() {
        return armadura;
    }

    public void setArmadura(int armadura) {
        this.armadura = armadura;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public abstract double calcularDanio(double va, double ed, double pdef);

    public void mejorarSalud() {
        this.salud += 10;
    }
}
