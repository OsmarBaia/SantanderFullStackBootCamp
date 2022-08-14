package Bancos;

import Agencias.Agencia;
import Clientes.Cliente;
import Contas.Conta;

import java.util.ArrayList;
import java.util.Scanner;

public class Banco {
    private final String name;
    private final ArrayList<Agencia> agencias;

    public Banco(String name) {
        this.name = name;
        this.agencias = new ArrayList<>();
    }

    private void AcessarBancoDeDados() {
        //Simula Dados no armazenados no banco de dados

        //Cria 2 agencias fictícias
        CadastrarAgencia("Brasilia");
        CadastrarAgencia("São paulo");

        //Cria 3 Contas fictícias na Agencia 1
        agencias.get(0).CadastrarCliente("João");       // João Conta-corrente (0)
        agencias.get(0).CadastrarCliente("Pedro");      // Pedro Conta-corrente (1)
        agencias.get(0).CadastrarCliente("Ana");        // Ana Conta-Corrente (2)

        //Cria 2 Contas fictícias na Agencia 2
        agencias.get(1).CadastrarCliente("Paulo");      // Paulo Conta-corrente (0)
        agencias.get(1).CadastrarCliente("Maria");      // Maria Conta-Poupança (1)

        //Cria conta Poupança para Paulo e Ana
        agencias.get(0).CadastrarConta(agencias.get(0).GetCliente(2), 1);   // Ana Conta-Poupança (3)
        agencias.get(1).CadastrarConta(agencias.get(1).GetCliente(0), 1);   // Paulo Conta-Poupança (2)

        //Colocar Saldo fictício
        agencias.get(0).GetConta(0).Deposito(1500);     //Joao 1500
        agencias.get(0).GetConta(1).Deposito(500);      //Pedro 500
        agencias.get(0).GetConta(2).Deposito(250);      //Ana CC 250
        agencias.get(0).GetConta(3).Deposito(750);      //Ana CP 750

    }


    public void Init() {
        AcessarBancoDeDados();

        boolean sair = false;
        while (!sair) {
            Scanner sc = new Scanner(System.in);

            System.out.println("\nSeja bem-vindo ao " + name + " !!");
            System.out.println("\nDigite: \n 1 - Para acessar sua conta \n 2 - Para criar uma conta \n 0 - Para Sair \nAguardando resposta... ");
            int escolhaMenu = sc.nextInt();
            switch (escolhaMenu) {
                case 0:
                    sair = true;
                    sc.close();
                    break;
                case 1:
                    AcessarConta(sc);
                    break;
                case 2:
                    AbriConta(sc);
                    break;
                default:
                    PressioneEnter("\nEscolha invalida!");
                    break;
            }
        }
    }

    private void PressioneEnter(String msg) {
        System.out.println(msg + "\n Pressione enter para continuar...");
        try {
            System.in.read();
        } catch (Exception ignored) {
        }
    }

    private void CadastrarAgencia(String dados) {
        Agencia ag = new Agencia(dados, agencias.size() + 1);
        this.agencias.add(ag);
        System.out.println("Agencia Existe: " + ag);

    }

    private Agencia GetAgencia(int id) {
        if (this.agencias.isEmpty()) {
            return null;
        } else {
            return this.agencias.get(id - 1);
        }
    }

    private void AcessarConta(Scanner sc) {
        System.out.println("\n--- Acesso ---");
        System.out.println("\nDigite o numero da sua agencia: ");
        int id_agencia = sc.nextInt();
        Agencia ag = GetAgencia(id_agencia);

        if (ag != null) {
            System.out.println("\nDigite o numero da sua conta: ");
            int id_conta = sc.nextInt();
            Conta c = ag.GetConta(id_conta);

            if (c != null) {
                System.out.println("\nSeja bem-vindo " + c.GetCliente().GetDados() + " !!");
                boolean logout = false;
                while (!logout) {
                    System.out.println("\nDigite: \n 1 - Para Extrato  \n 2 - Saque \n 3 - Deposito \n 4 - Transferência  \n 0 - Para Sair \nAguardando resposta... ");
                    int escolhaConta = sc.nextInt();
                    switch (escolhaConta) {
                        case 1:
                            c.Extrato();
                            PressioneEnter("");
                            break;
                        case 2:
                            System.out.println("\nDigite o valor do saque: ");
                            c.Saque(sc.nextDouble());
                            PressioneEnter("\nPor favor retire seu dinheiro... ");
                            break;
                        case 3:
                            System.out.println("\nInforme o valor de deposito: ");
                            double valorDeposito = sc.nextDouble();
                            PressioneEnter("\nPor favor aguarde a verificação das cédulas... ");
                            c.Deposito(valorDeposito);
                            PressioneEnter("");
                            break;
                        case 4:
                            System.out.println("\nInforme a agencia de destino: ");
                            Agencia agDestino = GetAgencia(sc.nextInt());
                            if (agDestino != null) {
                                System.out.println("\nInforme a conta de destino: ");
                                Conta contaDestino = agDestino.GetConta(sc.nextInt());
                                if (contaDestino != null) {
                                    System.out.println("\nInforme o valor a transferir: ");
                                    double tValue = sc.nextDouble();
                                    c.Transferir(contaDestino, tValue);
                                    PressioneEnter("");
                                } else {
                                    PressioneEnter("\n Conta invalida!");
                                }
                            } else {
                                PressioneEnter("\n Agencia invalida!");
                            }
                            break;
                        case 0:
                            logout = true;
                            break;
                        default:
                            PressioneEnter("\n Escolha invalida!");
                            break;
                    }
                }
            } else {
                PressioneEnter("\nO numero da conta esta incorreto ou a conta não existe!");
            }
        } else {
            PressioneEnter("\nO numero da agencia esta incorreto ou a agencia não existe!");
        }
    }

    private void AbriConta(Scanner sc) {
        //Executar menu de cadastro
        System.out.println("\nInforme seu nome: ");

        String nome = sc.next();
        System.out.println("\nAgencias disponíveis: ");
        for (int i = 0; i < this.agencias.size(); i++) {
            System.out.printf("%d  - %s \n", this.agencias.get(i).GetNumero(), this.agencias.get(i).GetDados());
        }

        System.out.println("\nInforme a agencia que deseja: ");
        int id = sc.nextInt();

        Agencia ag = this.GetAgencia(id);

        if (ag != null) {
            ag.CadastrarCliente(nome);
            Cliente cli = ag.GetCliente(nome);

            if (cli != null) {
                ag.PrintAllClientes();
                System.out.print("\n--- RESUMO ---");
                System.out.print("\nNumero da sua Agencia é: " + ag.GetNumero());
                System.out.print("\nNumero da sua Conta é: " + cli.GetConta(0).GetNumero());
                PressioneEnter("Cadastro realizado com SUCESSO!");
            }

        } else {
            this.PressioneEnter("Falha no cadastro, insira uma agencia valida!");
        }
    }


}
