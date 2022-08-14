package Clientes;

import Contas.Conta;

import java.util.ArrayList;

public class Cliente {
    private final int numero;
    private final ArrayList<Conta> contas;
    //Dados Cliente
    private final String dados;


    public Cliente(int numero, String dados) {
        this.contas = new ArrayList<>();
        this.numero = numero;
        this.dados = dados;
    }

    public String GetDados() {
        return this.dados;
    }

    public int GetNumero() {
        return this.numero;
    }

    public void VincularConta(Conta conta) {
        this.contas.add(conta);
    }

    public void DesvincularConta(Conta conta) {
        this.contas.remove(conta);
    }

    public Conta GetConta(int tipoConta) {
        return switch (tipoConta) {
            case 0 -> this.contas.get(0);
            case 1 -> this.contas.get(1);
            default -> null;
        };
    }
}