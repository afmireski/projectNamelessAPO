package daos;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;
import models.SaidaMercadoria;

/**
 *
 * @author afmireski
 */
public class DAOSaidaMercadoria extends DAOGeneric<SaidaMercadoria>{
    
    public DAOSaidaMercadoria() {
    }

    @Override
    public SaidaMercadoria retrieve(String id) {
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
        for (SaidaMercadoria saidaMercadoria : list) {
            String fk = saidaMercadoria.toFk();
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
        //converter de CSV para SaidaMercadoria
        SaidaMercadoria saidaMercadoria;
        for (String string : stringList) {
            String aux[] = string.split(";");
            saidaMercadoria = new SaidaMercadoria(
                    aux[0], //id
                    aux[1], //idMercadoria
                    aux[2], //idCriador
                    Integer.valueOf(aux[3]), //quantidadeSaida
                    cf.converteDeStringParaDate(aux[4]) //dataSaida
            );
            list.add(saidaMercadoria);
        }
    }
}
