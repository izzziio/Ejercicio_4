public class CamaraVideo extends Equipo {

    private int resolucionMaxima;

    public CamaraVideo(
        String codigo,
        String marca,
        String modelo,
        double tarifaDiaria,
        int resolucionMaxima
    ) {
        super(codigo, marca, modelo, tarifaDiaria);

        if (resolucionMaxima <= 0) {
            throw new IllegalArgumentException(
                "La resolucion maxima debe ser mayor que cero."
            );
        }

        this.resolucionMaxima = resolucionMaxima;
    }

    public int getResolucionMaxima() {
        return resolucionMaxima;
    }

    @Override
    public double calcularCosto(int dias) {
        if (dias <= 0) {
            throw new IllegalArgumentException(
                "Los dias de alquiler deben ser mayores que cero."
            );
        }

        double costo = getTarifaDiaria() * dias;

        if (resolucionMaxima > 1080) {
            costo += 75;
        }

        return costo;
    }

    @Override
    public String toString() {
        return super.toString()
            + String.format(
                " | Resolucion maxima: %dp",
                resolucionMaxima
            );
    }
}