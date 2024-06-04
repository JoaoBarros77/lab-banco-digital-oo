import java.time.format.DateTimeFormatter;

public abstract class Conta {

    private static final int AGENCIA_PADRAO = 1;
    private static int NUMERO = 1;
    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;
    protected String tipoDeConta;
    protected int senha;
    public Conta(Cliente cliente) {
        this.agencia = AGENCIA_PADRAO;
        this.numero = NUMERO++;
        this.cliente = cliente;
    }

    public void sacar(double valor){
        if(this.saldo < valor){
            System.out.println("Saldo insuficiente!");
        } else {
            this.saldo -= valor;
            System.out.println("Saque de " + valor + " realizado com sucesso!");
        }

    }

    public void depositar(double valor){
        this.saldo += valor;
    }

    public boolean transferir(Banco banco, double valor, int numeroDaConta){

        if(this.saldo < valor){
            System.out.println("Valor Insuficiente");
            return false;
        }

        for(Conta c : banco.getContas()){
            if(c.getNumero() == numeroDaConta){
                this.saldo -= valor;
                c.depositar(valor);
                return true;
            }
        }

        System.out.println("Conta não encontrada.");
        return false;

    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTipoDeConta() {return tipoDeConta; }

    public int getSenha() {
        return senha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    protected void imprimirDadosCliente(){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String linha = "=".repeat(18);
        System.out.println(linha);
        System.out.println("  " + this.tipoDeConta);
        System.out.println(linha);
        System.out.println();
        System.out.println("Titular: " + this.cliente.getNome());
        System.out.println("CPF: " + this.cliente.getCpf());
        System.out.println("Data de Nascimento: " + dateFormat.format(this.cliente.getDataDeNascimento()));

        System.out.println(String.format("Agencia: %d", this.agencia));
        System.out.println(String.format("Numero: %d", this.numero));
        System.out.println(String.format("Saldo: %.2f", this.saldo));
        System.out.println();
    }


}
