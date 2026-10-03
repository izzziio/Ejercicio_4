import java.util.ArrayList;

public class Inventario {

    private ArrayList<Equipo> equipos;
    private double ingresosAcumulados;

    public Inventario() {
        equipos = new ArrayList<>();
        ingresosAcumulados = 0.0;
    }

    public boolean registrarEquipo(Equipo equipo) {
        if (equipo == null) {
            return false;
        }

        if (buscarEquipo(equipo.getCodigo()) != null) {
            return false;
        }

        equipos.add(equipo);
        return true;
    }

    public Equipo buscarEquipo(String codigo) {
        if (codigo == null) {
            return null;
        }

        for (Equipo equipo : equipos) {
            if (equipo.getCodigo().equalsIgnoreCase(codigo.trim())) {
                return equipo;
            }
        }

        return null;
    }

    public void mostrarInventario() {
        if (equipos.isEmpty()) {
            System.out.println("No hay equipos registrados.");
            return;
        }

        System.out.println("\n=== INVENTARIO ===");

        for (Equipo equipo : equipos) {
            System.out.println(equipo);
        }
    }

    public double cotizar(String codigo, int dias) {
        Equipo equipo = buscarEquipo(codigo);

        if (equipo == null) {
            throw new IllegalArgumentException(
                "No existe un equipo con ese codigo."
            );
        }

        if (dias <= 0) {
            throw new IllegalArgumentException(
                "Los dias de alquiler deben ser mayores que cero."
            );
        }

        return equipo.calcularCosto(dias);
    }

    public boolean confirmarAlquiler(String codigo, int dias) {
        Equipo equipo = buscarEquipo(codigo);

        if (equipo == null || !equipo.isDisponible() || dias <= 0) {
            return false;
        }

        double total = equipo.calcularCosto(dias);

        ingresosAcumulados += total;
        equipo.alquilar();

        return true;
    }

    public boolean registrarDevolucion(String codigo) {
        Equipo equipo = buscarEquipo(codigo);

        if (equipo == null || equipo.isDisponible()) {
            return false;
        }

        equipo.devolver();
        return true;
    }

    public void mostrarReporte() {
        int proyectoresDisponibles = 0;
        int proyectoresAlquilados = 0;

        int camarasDisponibles = 0;
        int camarasAlquiladas = 0;

        int sonidosDisponibles = 0;
        int sonidosAlquilados = 0;

        for (Equipo equipo : equipos) {

            if (equipo instanceof Proyector) {
                if (equipo.isDisponible()) {
                    proyectoresDisponibles++;
                } else {
                    proyectoresAlquilados++;
                }

            } else if (equipo instanceof CamaraVideo) {
                if (equipo.isDisponible()) {
                    camarasDisponibles++;
                } else {
                    camarasAlquiladas++;
                }

            } else if (equipo instanceof EquipoSonido) {
                if (equipo.isDisponible()) {
                    sonidosDisponibles++;
                } else {
                    sonidosAlquilados++;
                }
            }
        }

        System.out.println("\n=== REPORTE GENERAL ===");
        System.out.println("Total de equipos: " + equipos.size());

        System.out.println("\nProyectores:");
        System.out.println("Disponibles: " + proyectoresDisponibles);
        System.out.println("Alquilados: " + proyectoresAlquilados);

        System.out.println("\nCamaras de video:");
        System.out.println("Disponibles: " + camarasDisponibles);
        System.out.println("Alquiladas: " + camarasAlquiladas);

        System.out.println("\nEquipos de sonido:");
        System.out.println("Disponibles: " + sonidosDisponibles);
        System.out.println("Alquilados: " + sonidosAlquilados);

        System.out.printf(
            "%nIngresos acumulados: Q%.2f%n",
            ingresosAcumulados
        );
    }

    public double getIngresosAcumulados() {
        return ingresosAcumulados;
    }
}