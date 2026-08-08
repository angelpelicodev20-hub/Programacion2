package Ejercicio3_SistemaPagos;

public abstract class Pago {
    private String tipoPago;
    private double cantidad;

    public Pago(String tipoPago, double cantidad) {
        this.tipoPago = tipoPago;
        this.cantidad = cantidad;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void procesarPago() {
        System.out.println("Tipo de pago: " + getTipoPago());
        System.out.println("Cantidad: " + getCantidad());
    }
}