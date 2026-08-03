import java.util.Scanner;

public class ClaseCuenta {
    private double saldo;

    // Constructor
    public ClaseCuenta() {
        this.saldo = 0.0;
    }

    // 2. Método depositar
    public void depositar(double monto) {
        try {
            if (monto <= 0) {
                throw new IllegalArgumentException("El monto a depositar debe ser mayor a cero.");
            }
            saldo += monto;
            System.out.println("Depósito exitoso. Saldo actual: " + saldo);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // 3. Método retirar con validación / 4. Evitar saldo negativo
    public void retirar(double monto) {
        try {
            if (monto <= 0) {
                throw new IllegalArgumentException("El monto a retirar debe ser mayor a cero.");
            }
            if (monto > saldo) {
                throw new IllegalArgumentException("Saldo insuficiente. Saldo disponible: " + saldo);
            }
            saldo -= monto;
            System.out.println("Retiro exitoso. Saldo actual: " + saldo);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // 5. Mostrar saldo
    public void mostrarSaldo() {
        System.out.println("Saldo actual: " + saldo);
    }

    public double getSaldo() {
        return saldo;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ClaseCuenta cuenta = new ClaseCuenta();

        System.out.print("Ingrese el número de cuenta: ");
        String numeroCuentaStr = sc.next();

        if (numeroCuentaStr.length() != 8) {
            System.out.println("Error: el número de cuenta debe tener exactamente 8 dígitos.");
        } else {
            System.out.println("Número de cuenta válido.");
        }

        System.out.print("Ingrese el monto a depositar: ");
        double monto = sc.nextDouble();
        cuenta.depositar(monto);

        System.out.print("Ingrese el monto a retirar: ");
        double montoRetiro = sc.nextDouble();
        cuenta.retirar(montoRetiro);

        cuenta.mostrarSaldo();

        sc.close();
    }
}
