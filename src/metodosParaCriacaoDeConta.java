import java.time.LocalDate;
import java.time.Period;
import java.util.InputMismatchException;
import java.util.Scanner;

public class metodosParaCriacaoDeConta {
    public static Cliente cadastroNovoCliente(){
        Scanner sc = new Scanner(System.in);
        Cliente cliente = new Cliente();
        String nome = null;

        System.out.print("Me informe o nome do cliente: ");
        nome = sc.nextLine();
        cliente.setNome(nome);

        System.out.print("Me informe o CPF do cliente(Apenas números): ");
        String cpf = sc.nextLine();
        cliente.setCpf(cpf);

        System.out.print("Me informe a data de nascimento do cliente, no formato (Dia/Mês/Ano). Por exemplo: 01/01/2020: ");
        String dataNascimento = sc.nextLine();
        cliente.setDataDeNascimento(dataNascimento);

        return cliente;
    }

    public static int verificarIdade(LocalDate dataNascimento){

        LocalDate dataAtual = LocalDate.now();
        Period p = Period.between(dataNascimento, dataAtual);
        int idade = p.getYears();
        return idade;
    }

    public static int criarQualConta(int criacaoDeConta){
        Scanner sc = new Scanner(System.in);

        do {
            try {
                System.out.println("Digite 1 Caso queira criar uma Conta Corrente");
                System.out.println("Digite 2 Caso queira criar uma Conta Poupança");
                System.out.println("Digite 3 Caso queira criar uma Conta Corrente e uma Conta Poupança");
                System.out.print("Valor: ");
                criacaoDeConta = sc.nextInt();

            } catch (InputMismatchException e){
                System.out.println();
                System.out.println("Valor inserido inválido.");
            }

            if (criacaoDeConta <= 0 || criacaoDeConta > 3){
                System.out.println();
                System.out.println("Por favor insira um número válido.");
            }
            System.out.println();
            sc.nextLine();
        } while(criacaoDeConta <= 0 || criacaoDeConta > 3);
        return criacaoDeConta;
    }

    public static Conta criacaoContaCorrente(Cliente cliente){
        ContaCorrente contaCorrente = new ContaCorrente(cliente);
        contaCorrente.setSenha(senha("Conta Corrente"));
        return contaCorrente;
    }

    public static Conta criacaoContaPoupanca(Cliente cliente){
        ContaPoupanca contaPoupanca = new ContaPoupanca(cliente);
        contaPoupanca.setSenha(senha("Conta Poupanca"));
        return contaPoupanca;
    }

    public static Banco criacaoDeConta(){
        Banco bancoTemporario = new Banco();
        Cliente cliente = cadastroNovoCliente();

        int idadeCliente = verificarIdade(cliente.getDataDeNascimento());
        if (idadeCliente < 18){
            System.out.println();
            System.out.println("Cadastro não concluido!");
            System.out.println("Só é possível realizar o cadastro a partir dos 18 anos.");
            cliente.resetAll();
            return null;
        } else {
            System.out.println();
            System.out.println("Cadastro do Cliente " + cliente.getNome() + " criado com sucesso!");
            System.out.println();
            int criacaoDeConta = criarQualConta(0);
            switch (criacaoDeConta){
                case 1:
                    Conta contaCorrente = criacaoContaCorrente(cliente);
                    bancoTemporario.setContas(contaCorrente);
                    System.out.println("Conta criada com sucesso!");
                    bancoTemporario.imprimirContas();
                    break;

                case 2:
                    Conta contaPoupanca = metodosParaCriacaoDeConta.criacaoContaPoupanca(cliente);
                    bancoTemporario.setContas(contaPoupanca);
                    System.out.println("Conta criada com sucesso!");
                    bancoTemporario.imprimirContas();
                    break;

                case 3:
                    contaCorrente = metodosParaCriacaoDeConta.criacaoContaCorrente(cliente);
                    contaPoupanca = metodosParaCriacaoDeConta.criacaoContaPoupanca(cliente);
                    bancoTemporario.setContas(contaCorrente);
                    bancoTemporario.setContas(contaPoupanca);
                    System.out.println("Contas criadas com sucesso!");
                    bancoTemporario.imprimirContas();
                    break;
            }
        }
        return bancoTemporario;
    }


    public static int senha(String tipoDeConta){
        Scanner sc = new Scanner(System.in);
        int senhaDaConta = -1;
        int confirmacaoSenha = -1;

        do {
            try {
                System.out.print("Digite a senha da " + tipoDeConta + ": ");
                senhaDaConta = sc.nextInt();
                System.out.print("Confirme sua senha: ");
                confirmacaoSenha = sc.nextInt();

            } catch (InputMismatchException e){
                System.out.println();
                System.out.println("Por favor, insira um valor válido.");
            }

            if (confirmacaoSenha != senhaDaConta){
                System.out.println();
                System.out.println("Senha errada");
                System.out.println("As senhas precisam ser iguais");
            }
            System.out.println();
        } while(senhaDaConta < 0);
        return senhaDaConta;
    }
}
