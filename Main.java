import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventario inventario = new Inventario();

        cargarEquiposIniciales(inventario);

        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero(scanner);

            switch (opcion) {
                case 1:
                    registrarEquipo(scanner, inventario);
                    break;

                case 2:
                    consultarInventario(inventario);
                    break;

                case 3:
                    cotizar(scanner, inventario);
                    break;

                case 4:
                    alquilar(scanner, inventario);
                    break;

                case 5:
                    devolver(scanner, inventario);
                    break;

                case 6:
                    mostrarReporte(inventario);
                    break;

                case 7:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }

        } while (opcion != 7);

        scanner.close();
    }

    private static void cargarEquiposIniciales(Inventario inventario) {
        // Dos proyectores: uno inalambrico y uno sin conectividad inalambrica.
        inventario.registrarEquipo(
            new Proyector("P001", "Epson", "PowerLite X49", 150.0, 3600, true)
        );

        inventario.registrarEquipo(
            new Proyector("P002", "BenQ", "MH560", 140.0, 3800, false)
        );

        // Dos camaras: una de 1080p y otra superior a 1080p.
        inventario.registrarEquipo(
            new CamaraVideo("C001", "Sony", "HDR-CX405", 175.0, 1080)
        );

        inventario.registrarEquipo(
            new CamaraVideo("C002", "Panasonic", "HC-VX1", 250.0, 2160)
        );

        // Dos equipos de sonido con potencias diferentes.
        // S001 permite comprobar el ejemplo de Q1,050 por tres dias.
        inventario.registrarEquipo(
            new EquipoSonido("S001", "JBL", "EON715", 200.0, 1.5)
        );

        inventario.registrarEquipo(
            new EquipoSonido("S002", "Yamaha", "DXR12", 225.0, 1.1)
        );
    }

    private static void mostrarMenu() {
        System.out.println("\n=== ENESCENA ===");
        System.out.println("1. Registrar equipo");
        System.out.println("2. Consultar inventario");
        System.out.println("3. Cotizar alquiler");
        System.out.println("4. Confirmar alquiler");
        System.out.println("5. Registrar devolucion");
        System.out.println("6. Mostrar reporte general");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    private static int leerEntero(Scanner scanner) {
        while (true) {
            String entrada = scanner.nextLine().trim();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.print(
                    "Entrada invalida. Ingrese un numero entero: "
                );
            }
        }
    }

    private static double leerDouble(Scanner scanner) {
        while (true) {
            String entrada = scanner.nextLine().trim();

            try {
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                System.out.print(
                    "Entrada invalida. Ingrese un numero valido: "
                );
            }
        }
    }

    private static void registrarEquipo(
        Scanner scanner,
        Inventario inventario
    ) {
        System.out.println("\n=== REGISTRAR EQUIPO ===");
        System.out.println("1. Proyector");
        System.out.println("2. Camara de video");
        System.out.println("3. Equipo de sonido");
        System.out.print("Seleccione la categoria: ");

        int tipo = leerEntero(scanner);

        if (tipo < 1 || tipo > 3) {
            System.out.println("Categoria invalida.");
            return;
        }

        System.out.print("Codigo de inventario: ");
        String codigo = scanner.nextLine().trim();

        if (inventario.buscarEquipo(codigo) != null) {
            System.out.println(
                "Ya existe un equipo con ese codigo de inventario."
            );
            return;
        }

        System.out.print("Marca: ");
        String marca = scanner.nextLine().trim();

        System.out.print("Modelo: ");
        String modelo = scanner.nextLine().trim();

        System.out.print("Tarifa diaria: Q");
        double tarifaDiaria = leerDouble(scanner);

        try {
            Equipo equipo;

            switch (tipo) {
                case 1:
                    System.out.print("Luminosidad en lumenes: ");
                    int luminosidad = leerEntero(scanner);

                    System.out.print(
                        "Tiene conectividad inalambrica? (S/N): "
                    );
                    boolean inalambrico =
                        leerSiNo(scanner);

                    equipo = new Proyector(
                        codigo,
                        marca,
                        modelo,
                        tarifaDiaria,
                        luminosidad,
                        inalambrico
                    );
                    break;

                case 2:
                    System.out.print(
                        "Resolucion maxima en pixeles verticales: "
                    );
                    int resolucion = leerEntero(scanner);

                    equipo = new CamaraVideo(
                        codigo,
                        marca,
                        modelo,
                        tarifaDiaria,
                        resolucion
                    );
                    break;

                case 3:
                    System.out.print("Potencia nominal en kW: ");
                    double potencia = leerDouble(scanner);

                    equipo = new EquipoSonido(
                        codigo,
                        marca,
                        modelo,
                        tarifaDiaria,
                        potencia
                    );
                    break;

                default:
                    return;
            }

            if (inventario.registrarEquipo(equipo)) {
                System.out.println("Equipo registrado correctamente.");
            } else {
                System.out.println("No fue posible registrar el equipo.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static boolean leerSiNo(Scanner scanner) {
        while (true) {
            String respuesta = scanner.nextLine().trim();

            if (respuesta.equalsIgnoreCase("S")) {
                return true;
            }

            if (respuesta.equalsIgnoreCase("N")) {
                return false;
            }

            System.out.print("Respuesta invalida. Ingrese S o N: ");
        }
    }

    private static void consultarInventario(Inventario inventario) {
        inventario.mostrarInventario();
    }

    private static void cotizar(
        Scanner scanner,
        Inventario inventario
    ) {
        System.out.println("\n=== COTIZAR ALQUILER ===");

        System.out.print("Codigo del equipo: ");
        String codigo = scanner.nextLine().trim();

        Equipo equipo = inventario.buscarEquipo(codigo);

        if (equipo == null) {
            System.out.println(
                "No existe un equipo con ese codigo."
            );
            return;
        }

        System.out.print("Cantidad de dias: ");
        int dias = leerEntero(scanner);

        try {
            double total = inventario.cotizar(codigo, dias);

            System.out.println("\n" + equipo);
            System.out.println("Dias solicitados: " + dias);
            System.out.printf("Costo total: Q%.2f%n", total);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void alquilar(
        Scanner scanner,
        Inventario inventario
    ) {
        System.out.println("\n=== CONFIRMAR ALQUILER ===");

        System.out.print("Codigo del equipo: ");
        String codigo = scanner.nextLine().trim();

        Equipo equipo = inventario.buscarEquipo(codigo);

        if (equipo == null) {
            System.out.println(
                "No existe un equipo con ese codigo."
            );
            return;
        }

        if (!equipo.isDisponible()) {
            System.out.println(
                "El equipo se encuentra alquilado."
            );
            return;
        }

        System.out.print("Cantidad de dias: ");
        int dias = leerEntero(scanner);

        try {
            double total = inventario.cotizar(codigo, dias);

            System.out.println("\n" + equipo);
            System.out.printf("Total del alquiler: Q%.2f%n", total);
            System.out.print("Confirmar alquiler? (S/N): ");

            boolean confirmar = leerSiNo(scanner);

            if (!confirmar) {
                System.out.println(
                    "Alquiler cancelado. No se realizaron cambios."
                );
                return;
            }

            if (inventario.confirmarAlquiler(codigo, dias)) {
                System.out.println(
                    "Alquiler confirmado correctamente."
                );
            } else {
                System.out.println(
                    "No fue posible confirmar el alquiler."
                );
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void devolver(
        Scanner scanner,
        Inventario inventario
    ) {
        System.out.println("\n=== REGISTRAR DEVOLUCION ===");

        System.out.print("Codigo del equipo: ");
        String codigo = scanner.nextLine().trim();

        Equipo equipo = inventario.buscarEquipo(codigo);

        if (equipo == null) {
            System.out.println(
                "No existe un equipo con ese codigo."
            );
            return;
        }

        if (equipo.isDisponible()) {
            System.out.println(
                "El equipo ya se encuentra disponible."
            );
            return;
        }

        if (inventario.registrarDevolucion(codigo)) {
            System.out.println(
                "Devolucion registrada correctamente."
            );
        }
    }

    private static void mostrarReporte(Inventario inventario) {
        inventario.mostrarReporte();
    }
}