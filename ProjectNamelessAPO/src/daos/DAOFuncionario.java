package daos;

import java.util.ArrayList;
import java.util.List;
import tools.ManipulaArquivo;
import models.Funcionario;

/**
 *
 * @author afmireski
 */
public class DAOFuncionario extends DAOGeneric<Funcionario> {

    public DAOFuncionario() {
    }

    @Override
    public Funcionario retrieve(String id) {
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
        for (Funcionario funcionario : list) {
            String fk = funcionario.toFk();
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
        //converter de CSV para Funcionario
        Funcionario funcionario;
        for (String string : stringList) {
            String aux[] = string.split(";");
            funcionario = new Funcionario(
                    aux[0], //id
                    aux[1], //nome
                    aux[2], //departamento
                    cf.converteDeStringParaDate(aux[3]), //dataCadastro
                    cf.converteDeStringParaDate(aux[4]), //dataAtualizacao
                    cf.converteDeStringParaDate(aux[5]) //dataExclusao
            );
            System.out.println(funcionario.toString());
            list.add(funcionario);
        }
    }
}
