package models;

import java.util.Date;
import tools.CaixaDeFerramentas;

/**
 *
 * @author afmireski
 */
public class Mercadoria {

    private String id;
    private String idCriador;
    private String descricao;
    private int quantidadeEmEstoque;
    private Date dataCadastro;
    private Date dataAtualizacao;
    private Date dataExclusao;

    public Mercadoria() {
    }

    public Mercadoria(String id, String idCriador, String descricao, int quantidadeEmEstoque, Date dataCadastro, Date dataAtualizacao, Date dataExclusao) {
        this.id = id;
        this.idCriador = idCriador;
        this.descricao = descricao;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
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

    public String getIdCriador() {
        return idCriador;
    }

    public void setIdCriador(String idCriador) {
        this.idCriador = idCriador;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
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

        return id + " - " + idCriador;
    }

    @Override
    public String toString() {
        CaixaDeFerramentas cf = new CaixaDeFerramentas();
        return id + ";" + idCriador + ";" + descricao + ";" + quantidadeEmEstoque 
                + ";" + cf.converteDeDateParaString(dataCadastro)
                + ";" + cf.converteDeDateParaString(dataAtualizacao)
                + ";" + cf.converteDeDateParaString(dataExclusao);
    }
}
