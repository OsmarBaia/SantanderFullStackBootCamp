package Agencias;

import Clientes.Cliente;
import Contas.Conta;
import Contas.ContaCorrente;
import Contas.ContaPoupanca;

import java.util.ArrayList;

public class Agencia {
    //Properties
    private final String dados;
    private final int numero;
    private final ArrayList<Conta> contas;
    private final ArrayList<Cliente> clientes;

    //Constructor
    public Agencia(String dados, int numero) {
        this.dados = dados;
        this.numero = numero;
        this.contas = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    //Getters
    public String GetDados() {
        return this.dados;
    }

    public int GetNumero() {
        return this.numero;
    }

    public Conta GetConta(int numero) {
        return this.contas.get(numero);
    }

    public Cliente GetCliente(int numero) {
        return clientes.get(numero);
    }

    public void PrintAllClientes() {
        for (int i = 0; i < this.clientes.size(); i++) {
            System.out.println("Cliente: " + this.clientes.get(i).GetDados() + " Conta: " + this.clientes.get(i).GetConta(0));
        }
    }

    public Cliente GetCliente(String dados) {
        for (Cliente cliente : this.clientes) {
            if (cliente.GetDados().equals(dados)) {
                return cliente;
            }
        }
        return null;
    }

    //Methods
    public void CadastrarCliente(String dados) {
        Cliente cliente = new Cliente(this.clientes.size(), dados);

        this.clientes.add(cliente);
        CadastrarConta(cliente, 0);
    }

    public void CadastrarConta(Cliente cliente, int tipo) {
        Conta conta = null;
        switch (tipo) {
            case 0:
                conta = new ContaCorrente(this.GetNumero(), this.contas.size(), cliente);
                break;
            case 1:
                conta = new ContaPoupanca(this.GetNumero(), this.contas.size(), cliente);
                break;
            default:
                conta = null;
                break;
        }

        if (conta != null) {
            cliente.VincularConta(conta);
            this.contas.add(conta);
        } else {
            System.out.println("Falha ao criar a conta!");
        }

    }
}
