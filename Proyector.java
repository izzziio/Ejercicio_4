public class Proyector extends Equipo {

    private int luminosidad;
    private boolean inalambrico;

    public Proyector(
        String codigo,
        String marca,
        String modelo,
        double tarifaDiaria,
        int luminosidad,
        boolean inalambrico
    ) {
        super(codigo, marca, modelo, tarifaDiaria);

        if (luminosidad <= 0) {
            throw new IllegalArgumentException(
                "La luminosidad debe ser mayor que cero."
            );
        }

        this.luminosidad = luminosidad;
        this.inalambrico = inalambrico;
    }

    public int getLuminosidad() {
        return luminosidad;
    }

    public boolean isInalambrico() {
        return inalambrico;
    }

    @Override
    public double calcularCosto(int dias) {
        if (dias <= 0) {
            throw new IllegalArgumentException(
                "Los dias de alquiler deben ser mayores que cero."
            );
        }

        double costo = getTarifaDiaria() * dias;

        if (inalambrico) {
            costo += 50 * dias;
        }

        return costo;
    }

    @Override
    public String toString() {
        return super.toString()
            + String.format(
                " | Luminosidad: %d lumenes | Inalambrico: %s",
                luminosidad,
                inalambrico ? "Si" : "No"
            );
    }
}