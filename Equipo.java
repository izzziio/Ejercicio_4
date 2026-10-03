public abstract class Equipo {

    private String codigo;
    private String marca;
    private String modelo;
    private double tarifaDiaria;
    private boolean disponible;

    public Equipo(String codigo, String marca, String modelo, double tarifaDiaria) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo de inventario no puede estar vacio.");
        }

        if (tarifaDiaria <= 0) {
            throw new IllegalArgumentException("La tarifa diaria debe ser mayor que cero.");
        }

        this.codigo = codigo;
        this.marca = marca;
        this.modelo = modelo;
        this.tarifaDiaria = tarifaDiaria;
        this.disponible = true;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getTarifaDiaria() {
        return tarifaDiaria;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void alquilar() {
        disponible = false;
    }

    public void devolver() {
        disponible = true;
    }

    public abstract double calcularCosto(int dias);

    @Override
    public String toString() {
        return String.format(
            "Codigo: %s | Marca: %s | Modelo: %s | Tarifa diaria: Q%.2f | Estado: %s",
            codigo,
            marca,
            modelo,
            tarifaDiaria,
            disponible ? "Disponible" : "Alquilado"
        );
    }
}