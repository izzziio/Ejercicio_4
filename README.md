# EnEscena - Ejercicio 4

Aplicación de consola desarrollada en Java para administrar el inventario y los alquileres de equipos de la empresa EnEscena.

## Funcionalidades

El programa permite:

- Registrar proyectores, cámaras de video y equipos de sonido.
- Consultar el inventario.
- Cotizar alquileres.
- Confirmar alquileres.
- Registrar devoluciones.
- Consultar la disponibilidad de los equipos.
- Mostrar un reporte general con los ingresos acumulados.

## Estructura

El sistema utiliza una clase abstracta `Equipo` como base para las siguientes categorías:

- `Proyector`
- `CamaraVideo`
- `EquipoSonido`

Cada categoría implementa su propia regla para calcular el costo de un alquiler mediante el método `calcularCosto(int dias)`.

La clase `Inventario` administra los equipos, las operaciones de alquiler y los ingresos acumulados.

La clase `Main` contiene el menú de consola y permite al usuario interactuar con el sistema.

## Requisitos

- Java JDK instalado.
- Una terminal o consola.

## Compilación

Desde la carpeta raíz del proyecto ejecutar:

```bash
javac *.java