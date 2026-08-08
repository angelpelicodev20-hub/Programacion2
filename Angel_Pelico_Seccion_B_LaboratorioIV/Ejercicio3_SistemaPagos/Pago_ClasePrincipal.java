package Ejercicio3_SistemaPagos;

public class Pago_ClasePrincipal {
    public static void main(String[] args) {
        Pago[] pagos = {
            new PagoEfectivo(150),
            new PagoTarjeta(500, "4532111122223333")
        };

        for (Pago p : pagos) {
            p.procesarPago();
            System.out.println();
        }
    }
}
