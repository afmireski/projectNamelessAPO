package models;

import java.util.Date;
import tools.CaixaDeFerramentas;

/**
 *
 * @author afmireski
 */
public class Funcionario {

    private String id;
    private String nome;
    private String departamento;
    private Date dataCadastro;
    private Date dataAtualizacao;
    private Date dataExclusao;

    public Funcionario() {
    }

    public Funcionario(String id, String nome, String departamento, Date dataCadastro, Date dataAtualizacao, Date dataExclusao) {
        this.id = id;
        this.nome = nome;
        this.departamento = departamento;
        this.dataCadastro = dataCadastro;
        this.dataAtualizacao = dataAtualizacao;
        this.dataExclusao = dataExclusao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public Date getDataExclusao() {
        return dataExclusao;
    }

    public void setDataExclusao(Date dataExclusao) {
        this.dataExclusao = dataExclusao;
    }

    public String toFk() {
        //GERA UMA STRING PARA SER USADA COMO FK

        return id + " - " + nome;
    }

    @Override
    public String toString() {
        CaixaDeFerramentas cf = new CaixaDeFerramentas();
        return id + ";" + nome + ";" + departamento 
                + ";" + cf.converteDeDateParaString(dataCadastro)
                + ";" + cf.converteDeDateParaString(dataAtualizacao)
                + ";" + cf.converteDeDateParaString(dataExclusao);
    }
}
