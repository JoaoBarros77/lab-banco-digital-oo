import java.time.LocalDate;

import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Cliente {

    private String nome;
    private String cpf;
    private LocalDate dataDeNascimento;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataDeNascimento(String data) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate date = LocalDate.parse(data, dateFormat);

        this.dataDeNascimento = date;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void resetAll(){
        this.cpf = null;
        this.nome = null;
        this.dataDeNascimento = null;
    }

}
