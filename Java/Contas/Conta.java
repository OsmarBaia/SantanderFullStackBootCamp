package Contas;


import Clientes.Cliente;

public abstract class Conta {
    //Properties
    private final int numero;
    private final int agencia;
    private final Cliente cliente;
    private double saldo;

    //Constructor
    public Conta(int agencia, int numero, Cliente cliente) {
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = 0;
        this.cliente = cliente;
    }

    //Getters

    public Cliente GetCliente() {
        return this.cliente;
    }

    public int GetAgencia() {
        return this.agencia;
    }

    public int GetNumero() {
        return this.numero;
    }

    public double GetSaldo() {
        return this.saldo;
    }

    //Setters
    protected void SetSaldo(double valor) {
        this.saldo = valor;
    }

    //Methods
    public void Extrato() {
        System.out.printf("\n --- Extrato --- \n" +
                "Saldo: %.2f \n", GetSaldo());
    }

    public void Saque(double valor) {
        this.saldo -= valor;
        System.out.printf("\nSaque de %.2f executado com SUCESSO!\n", valor);
    }

    public void Deposito(double valor) {
        this.saldo += valor;
        System.out.printf("\nDeposito de %.2f recebido com SUCESSO\n", valor);
    }

    public void Transferir(Conta contaDestino, double valor) {
        this.saldo -= valor;
        contaDestino.Deposito(valor);
        System.out.printf("\nTransferência de %.2f para %s executado com SUCESSO!\n", valor, contaDestino.GetCliente().GetDados());
    }
}
