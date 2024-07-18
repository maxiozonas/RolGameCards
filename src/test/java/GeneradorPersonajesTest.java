import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GeneradorPersonajesTest {

    private GeneradorPersonajes generador;

    @BeforeEach
    public void setUp() {
        generador = new GeneradorPersonajes();
    }

    @Test
    public void testGenerarPersonajesAleatorios() {
        List<Personaje> personajes = GeneradorPersonajes.generarPersonajesAleatorios(6);

        assertNotNull(personajes);
        assertEquals(6, personajes.size());

        for (Personaje personaje : personajes) {
            assertNotNull(personaje.getRaza());
            assertTrue(personaje instanceof Humano || personaje instanceof Orco || personaje instanceof Elfo);

            assertNotNull(personaje.getNombre());
            assertNotNull(personaje.getApodo());
            assertNotNull(personaje.getFechaNacimiento());
            assertTrue(personaje.getEdad() >= 1 && personaje.getEdad() <= 300);
            assertTrue(personaje.getVelocidad() >= 1 && personaje.getVelocidad() <= 10);
            assertTrue(personaje.getDestreza() >= 1 && personaje.getDestreza() <= 5);
            assertTrue(personaje.getFuerza() >= 1 && personaje.getFuerza() <= 10);
            assertTrue(personaje.getArmadura() >= 1 && personaje.getArmadura() <= 10);

            // Asegurar que la salud inicial es 100
            assertEquals(100, personaje.getSalud());
        }
    }

    @Test
    public void testIngresarPersonajesManualmente() {
        // Simular datos de entrada manual (simulados para pruebas)
        String raza1 = "Humano";
        String nombre1 = "Gandalf";
        String apodo1 = "El Gris";
        String fechaNacimiento1 = "01/01/2000";
        int edad1 = 50;
        int nivel1 = 8;
        int velocidad1 = 5;
        int destreza1 = 3;
        int fuerza1 = 7;
        int armadura1 = 8;

        String raza2 = "Elfo";
        String nombre2 = "Legolas";
        String apodo2 = "Leggy";
        String fechaNacimiento2 = "01/01/2001";
        int edad2 = 50;
        int nivel2 = 8;
        int velocidad2 = 6;
        int destreza2 = 4;
        int fuerza2 = 6;
        int armadura2 = 7;

        String raza3 = "Orco";
        String nombre3 = "Thrall";
        String apodo3 = "Warchief";
        String fechaNacimiento3 = "01/01/1995";
        int edad3 = 50;
        int nivel3 = 8;
        int velocidad3 = 4;
        int destreza3 = 5;
        int fuerza3 = 9;
        int armadura3 = 10;

        // Agregar los personajes manualmente usando los nuevos métodos
        generador.ingresarPersonajesManualmente(raza1, nombre1, apodo1, fechaNacimiento1, edad1, nivel1, velocidad1, destreza1, fuerza1, armadura1);
        generador.ingresarPersonajesManualmente(raza2, nombre2, apodo2, fechaNacimiento2, edad2, nivel2, velocidad2, destreza2, fuerza2, armadura2);
        generador.ingresarPersonajesManualmente(raza3, nombre3, apodo3, fechaNacimiento3, edad3, nivel3, velocidad3, destreza3, fuerza3, armadura3);

        // Obtener la lista de personajes ingresados
        List<Personaje> personajes = generador.getPersonajes();

        // Verificar que se hayan ingresado los personajes correctamente
        assertNotNull(personajes);
        assertEquals(3, personajes.size());

        // Verificar los atributos del primer personaje
        Personaje personaje1 = personajes.get(0);
        assertEquals("Humano", personaje1.getRaza());
        assertEquals("Gandalf", personaje1.getNombre());
        assertEquals("El Gris", personaje1.getApodo());
        assertEquals(50, personaje1.getEdad());
        assertEquals(8, personaje1.getNivel());
        assertEquals(100, personaje1.getSalud());
        assertEquals(5, personaje1.getVelocidad());
        assertEquals(3, personaje1.getDestreza());
        assertEquals(7, personaje1.getFuerza());
        assertEquals(8, personaje1.getArmadura());

        // Verificar los atributos del segundo personaje
        Personaje personaje2 = personajes.get(1);
        assertEquals("Elfo", personaje2.getRaza());
        assertEquals("Legolas", personaje2.getNombre());
        assertEquals("Leggy", personaje2.getApodo());
        assertEquals(50, personaje2.getEdad());
        assertEquals(8, personaje2.getNivel());
        assertEquals(100, personaje2.getSalud());
        assertEquals(6, personaje2.getVelocidad());
        assertEquals(4, personaje2.getDestreza());
        assertEquals(6, personaje2.getFuerza());
        assertEquals(7, personaje2.getArmadura());

        // Verificar los atributos del tercer personaje
        Personaje personaje3 = personajes.get(2);
        assertEquals("Orco", personaje3.getRaza());
        assertEquals("Thrall", personaje3.getNombre());
        assertEquals("Warchief", personaje3.getApodo());
        assertEquals(50, personaje3.getEdad());
        assertEquals(8, personaje3.getNivel());
        assertEquals(100, personaje3.getSalud());
        assertEquals(4, personaje3.getVelocidad());
        assertEquals(5, personaje3.getDestreza());
        assertEquals(9, personaje3.getFuerza());
        assertEquals(10, personaje3.getArmadura());
    }

    @Test
    public void testNombreYApodoDuplicados() {
        String raza = "Humano";
        String nombre = "Aragorn";
        String apodo = "El Valiente";
        String fechaNacimiento = "01/01/2000";
        int edad = 50;
        int nivel = 8;
        int velocidad = 5;
        int destreza = 3;
        int fuerza = 7;
        int armadura = 8;

        // Agregar el primer personaje
        generador.ingresarPersonajesManualmente(raza, nombre, apodo, fechaNacimiento, edad, nivel, velocidad, destreza, fuerza, armadura);

        // Intentar agregar un personaje con el mismo nombre y apodo
        generador.ingresarPersonajesManualmente(raza, nombre, apodo, fechaNacimiento, edad, nivel, velocidad, destreza, fuerza, armadura);

        // Verificar que solo se haya agregado el primer personaje
        List<Personaje> personajes = generador.getPersonajes();
        assertNotNull(personajes);
        assertEquals(1, personajes.size());
    }

    @Test
    public void testNombreYApodoDisponibles() {
        String raza1 = "Humano";
        String nombre1 = "Aragorn";
        String apodo1 = "El Valiente";
        String fechaNacimiento1 = "01/01/2000";
        int edad1 = 50;
        int nivel1 = 8;
        int velocidad1 = 5;
        int destreza1 = 3;
        int fuerza1 = 7;
        int armadura1 = 8;

        String raza2 = "Elfo";
        String nombre2 = "Legolas";
        String apodo2 = "El Arquero";
        String fechaNacimiento2 = "01/01/2001";
        int edad2 = 50;
        int nivel2 = 8;
        int velocidad2 = 6;
        int destreza2 = 4;
        int fuerza2 = 6;
        int armadura2 = 7;

        // Agregar los personajes manualmente usando los nuevos métodos
        generador.ingresarPersonajesManualmente(raza1, nombre1, apodo1, fechaNacimiento1, edad1, nivel1, velocidad1, destreza1, fuerza1, armadura1);
        generador.ingresarPersonajesManualmente(raza2, nombre2, apodo2, fechaNacimiento2, edad2, nivel2, velocidad2, destreza2, fuerza2, armadura2);

        // Obtener la lista de personajes ingresados
        List<Personaje> personajes = generador.getPersonajes();

        // Verificar que se hayan ingresado los personajes correctamente
        assertNotNull(personajes);
        assertEquals(2, personajes.size());
    }
}



