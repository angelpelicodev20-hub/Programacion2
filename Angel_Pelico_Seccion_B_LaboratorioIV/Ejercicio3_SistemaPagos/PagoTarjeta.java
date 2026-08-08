package Ejercicio3_SistemaPagos;

public class PagoTarjeta extends Pago {
    private String numeroTarjeta;

    public PagoTarjeta(double cantidad, String numeroTarjeta) {
        super("Tarjeta", cantidad);
        this.numeroTarjeta = numeroTarjeta;
    }

    @Override
    public void procesarPago() {
        super.procesarPago();
        System.out.println("Cobro autorizado con tarjeta terminación: " +
            numeroTarjeta.substring(numeroTarjeta.length() - 4));
    }
}