public class EquipoSonido extends Equipo {

    private double potenciaKW;

    public EquipoSonido(
        String codigo,
        String marca,
        String modelo,
        double tarifaDiaria,
        double potenciaKW
    ) {
        super(codigo, marca, modelo, tarifaDiaria);

        if (potenciaKW <= 0) {
            throw new IllegalArgumentException(
                "La potencia nominal debe ser mayor que cero."
            );
        }

        this.potenciaKW = potenciaKW;
    }

    public double getPotenciaKW() {
        return potenciaKW;
    }

    @Override
    public double calcularCosto(int dias) {
        if (dias <= 0) {
            throw new IllegalArgumentException(
                "Los dias de alquiler deben ser mayores que cero."
            );
        }

        double costoBase = getTarifaDiaria() * dias;
        double recargoPotencia = 100 * potenciaKW * dias;

        return costoBase + recargoPotencia;
    }

    @Override
    public String toString() {
        return super.toString()
            + String.format(
                " | Potencia nominal: %.2f kW",
                potenciaKW
            );
    }
}