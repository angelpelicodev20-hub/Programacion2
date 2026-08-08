package Ejercicio3_SistemaPagos;

public class PagoEfectivo extends Pago {

    public PagoEfectivo(double cantidad) {
        super("Efectivo", cantidad);
    }

    @Override
    public void procesarPago() {
        super.procesarPago();
        System.out.println("Pago recibido en efectivo. Verifique el cambio a entregar.");
    }
}