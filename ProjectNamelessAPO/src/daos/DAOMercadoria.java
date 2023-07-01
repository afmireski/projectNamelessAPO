package daos;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;
import models.Mercadoria;

/**
 *
 * @author afmireski
 */
public class DAOMercadoria extends DAOGeneric<Mercadoria> {
    public DAOMercadoria() {
    }

    @Override
    public Mercadoria retrieve(String id) {        
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)) {
                return list.get(i);
            }
        }
        return null;
    }    

    @Override
    public List<String> getFkList() {        
        //GERA UMA LISTA DE STRINGS EM FORMA DE CHAVE ESTRAGEIRA
        List<String> fks = new ArrayList<>();
        for (Mercadoria mercadoria : list) {
            String fk = mercadoria.toFk();
            fks.add(fk);
        }
        return fks;
    }
    
    @Override
    public void loadData(String path) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        if (!manipulaArquivo.existeOArquivo(path)) {
            manipulaArquivo.criarArquivoVazio(path);
        }

        List<String> stringList = manipulaArquivo.abrirArquivo(path);
        //converter de CSV para Mercadoria
        Mercadoria mercadoria;
        for (String string : stringList) {
            String aux[] = string.split(";");
            mercadoria = new Mercadoria(
                    aux[0], //id
                    aux[1], //idCriador
                    aux[2], //descricao
                    Integer.valueOf(aux[3]), //quantidadeEmEstoque
                    cf.converteDeStringParaDate(aux[4]), //dataCadastro
                    cf.converteDeStringParaDate(aux[5]), //dataAtualizacao
                    cf.converteDeStringParaDate(aux[6]) //dataExclusao
            );
            list.add(mercadoria);
        }
    }
}