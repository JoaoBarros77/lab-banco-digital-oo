import java.util.ArrayList;
import java.util.List;

public class Banco {
    private List<Conta> contas = new ArrayList<>();

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(Conta contas) {
        this.contas.add(contas);
    }

    public void remove(Conta conta){

        getContas().removeIf(c -> c.equals(conta));

    }
    public void imprimirContas(){
        List<Conta> imprimir = getContas();
        imprimir.forEach(Conta::imprimirDadosCliente);
    }


}
