import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        Banco banco = new Banco();
        Conta contaUtilizada = null;
        mensagemDeEntrada();

        int operacao = menuEscolhaInicial();

        while(operacao != 0){
            switch (operacao){
                case 1:
                    Banco bancoTemporario = metodosParaCriacaoDeConta.criacaoDeConta();
                    if (bancoTemporario != null){
                        for (Conta c : bancoTemporario.getContas()){
                            banco.setContas(c);
                        }
                    }
                    break;
                case 2:
                    Conta contaTemporaria = acessarConta(banco);
                    if (contaTemporaria != null){
                        contaUtilizada = contaTemporaria;
                        System.out.println();
                        System.out.println("Bem vindo " + contaUtilizada.getCliente().getNome() + "!");
                    } else {
                        break;
                    }
                    int operacaoAposAcesso = menuEscolhaAposAcesso();

                    while(operacaoAposAcesso != 0){
                        switch (operacaoAposAcesso){
                            case 1:
                                contaUtilizada.imprimirDadosCliente();
                                break;
                            case 2:
                                banco.remove(contaUtilizada);
                                System.out.println("Conta removida");
                                break;
                            case 3:
                                System.out.println("Quanto deseja sacar? ");
                                System.out.println("Valor em Conta " + contaUtilizada.getSaldo());
                                System.out.println();
                                System.out.print("Valor: ");
                                double valorDesejadoParaSacar = sc.nextDouble();
                                contaUtilizada.sacar(valorDesejadoParaSacar);
                                break;
                            case 4:
                                System.out.println("Quanto deseja depositar? ");
                                System.out.print("Valor: ");
                                double valorDeposito = sc.nextDouble();
                                contaUtilizada.depositar(valorDeposito);
                                System.out.println("Deposito de R$" + valorDeposito + " realizado com sucesso!");
                                System.out.println("Saldo da conta: " + contaUtilizada.getSaldo());
                                break;
                            case 5:
                                System.out.println("Saldo da Conta: " + contaUtilizada.getSaldo());
                                System.out.print("Valor que será transferido: ");
                                double valorTransferido = sc.nextDouble();
                                System.out.print("Insira o numero da Conta que receberá a transfêrencia: ");
                                int contaTransferencia = sc.nextInt();

                                boolean transferenciaFinalizada = contaUtilizada.transferir(banco, valorTransferido, contaTransferencia);
                                if (transferenciaFinalizada){
                                    System.out.println("Transferencia realizada com sucesso!");
                                } else {
                                    System.out.println("Não foi possível finalizar a transferencia!");
                                }
                                break;
                        }

                        if (operacaoAposAcesso == 2){
                            operacaoAposAcesso = 0;
                        } else {
                            operacaoAposAcesso = menuEscolhaAposAcesso();
                        }

                    }
                    fimDasOperacoesAposAcesso();
                    break;
            }
            operacao = menuEscolhaInicial();
        }

        fimDasOperacoes();

        }

    public static Conta acessarConta(Banco banco) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o Numero da Conta: ");
        int numeroDaConta = sc.nextInt();

        for (Conta c : banco.getContas()){
            if(c.getNumero() == numeroDaConta){
                System.out.print("Digite a senha da Conta: ");
                int senha = sc.nextInt();

                if(c.getSenha() == senha){
                    return c;
                } else {
                    System.out.println("Senha inválida");
                    return null;
                }
            }
        }

        System.out.println();
        System.out.println("Não foi possivel encontrar a conta");
        return null;
    }



    public static void mensagemDeEntrada(){
        String linha = "=".repeat(18);
        System.out.println(linha);
        System.out.println("       BANCO");
        System.out.println(linha);
        System.out.println("  Seja Bem Vindo!");
    }

    public static void MenuInicial(){

        String linha = "=".repeat(18);
        System.out.println(linha);
        System.out.println();
        System.out.println("Digite o número referente a operação que deseja realizar: ");
        System.out.println("[1] Criar uma nova conta");
        System.out.println("[2] Acessar uma conta");
        System.out.println("[0] Sair");
        System.out.print("Operação escolhida: ");
    }

    public static int menuEscolhaInicial(){
        int operacao = -1;
        Scanner sc = new Scanner(System.in);

        do {
            try {
                MenuInicial();
                operacao = sc.nextInt();

            } catch (InputMismatchException e){
                System.out.println();
                System.out.println("Valor inserido inválido.");
            }

            if (operacao < 0 || operacao > 2){
                System.out.println();
                System.out.println("Por favor insira um número válido.");
            }
            System.out.println();
            sc.nextLine();
        } while(operacao < 0 || operacao > 2);
        return operacao;
    }
    public static void MenuAposAcesso(){

        String linha = "=".repeat(18);
        System.out.println(linha);
        System.out.println();
        System.out.println("Digite o número referente a operação que deseja realizar: ");
        System.out.println("[1] Ver os dados da Conta");
        System.out.println("[2] Remover a conta");
        System.out.println("[3] Realizar saque");
        System.out.println("[4] Realizar depósito");
        System.out.println("[5] Realizar transferência");
        System.out.println("[0] Sair");
        System.out.print("Operação escolhida: ");
    }

    public static int menuEscolhaAposAcesso(){
        int operacao = -1;
        Scanner sc = new Scanner(System.in);

        do {
            try {
                MenuAposAcesso();
                operacao = sc.nextInt();

            } catch (InputMismatchException e){
                System.out.println();
                System.out.println("Valor inserido inválido.");
            }

            if (operacao < 0 || operacao > 6){
                System.out.println();
                System.out.println("Por favor insira um número válido.");
            }
            System.out.println();
            sc.nextLine();
        } while(operacao < 0 || operacao > 6);
        return operacao;
    }


    public static void fimDasOperacoes(){
        String linha = "=".repeat(18);
        System.out.println(linha);
        System.out.println("Fim das Operacoes");
        System.out.println(linha);
    }

    public static void fimDasOperacoesAposAcesso(){
        String linha = "=".repeat(18);
        System.out.println(linha);
        System.out.println("Encerrando a Sessão");

    }

}