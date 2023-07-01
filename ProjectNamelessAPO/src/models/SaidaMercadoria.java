package models;

import java.util.Date;
import tools.CaixaDeFerramentas;

/**
 *
 * @author afmireski
 */
public class SaidaMercadoria {

    private String id;
    private String idMercadoria;
    private String idCriador;
    private int quantidadeSaida;
    private Date dataSaida;

    public SaidaMercadoria() {
    }

    public SaidaMercadoria(String id, String idMercadoria, String idCriador, int quantidadeSaida, Date dataSaida) {
        this.id = id;
        this.idMercadoria = idMercadoria;
        this.idCriador = idCriador;
        this.quantidadeSaida = quantidadeSaida;
        this.dataSaida = dataSaida;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMercadoria() {
        return idMercadoria;
    }

    public void setIdMercadoria(String idMercadoria) {
        this.idMercadoria = idMercadoria;
    }

    public String getIdCriador() {
        return idCriador;
    }

    public void setIdCriador(String idCriador) {
        this.idCriador = idCriador;
    }

    public int getQuantidadeSaida() {
        return quantidadeSaida;
    }

    public void setQuantidadeSaida(int quantidadeSaida) {
        this.quantidadeSaida = quantidadeSaida;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String toFk() {
        //GERA UMA STRING PARA SER USADA COMO FK

        return id + " - " + idMercadoria;
    }

    @Override
    public String toString() {
        CaixaDeFerramentas cf = new CaixaDeFerramentas();
        return id + ";" + idMercadoria + ";" + idCriador + ";" + quantidadeSaida + ";" + cf.converteDeDateParaString(dataSaida);
    }
}
