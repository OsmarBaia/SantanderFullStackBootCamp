package Contas;


public class Conta {
    private final int agencia;
    private final int numero;
    private double saldo;

    public Conta(int agencia, int numero, double saldo) {
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = saldo;
    }

    public int GetAgencia() {
        return agencia;
    }

    public int GetNumero() {
        return numero;
    }

    public double GetSaldo() {
        return saldo;
    }

    protected void SetSaldo(double valor) {
        this.saldo = valor;
    }

    public void Saque(double valor) {
        this.saldo -= valor;
    }

    public void Deposito(double valor) {
        this.saldo += valor;
    }

    public void Transferir(Conta conta) {

    }
}
