package controllers;

import daos.DAOFuncionario;
import daos.DAOMercadoria;
import daos.DAOSaidaMercadoria;
import java.util.List;
import models.Funcionario;
import models.Mercadoria;
import models.SaidaMercadoria;

/**
 *
 * @author afmireski
 */
public class SaidaMercadoriaController extends ControllerGeneric<SaidaMercadoria> {

    @Override
    public void clearList() {
        manager.getDAO(DAOSaidaMercadoria.class).clearList();
    }

    @Override
    public void create(SaidaMercadoria element) throws Exception {
        try {
            Mercadoria mercadoria = (Mercadoria) this.manager.getDAO(DAOMercadoria.class).retrieve(element.getIdMercadoria());

            if (mercadoria == null) {
                throw new Exception("A mercadoria não existe!");
            }

            Funcionario funcionario = (Funcionario) this.manager.getDAO(DAOFuncionario.class).retrieve(element.getIdCriador());

            if (funcionario == null) {
                throw new Exception("O funcionario não existe!");
            }

            //Adiciona um Objeto T a lista;
            manager.getDAO(DAOSaidaMercadoria.class).create(element);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<SaidaMercadoria> listar() {
        return manager.getDAO(DAOSaidaMercadoria.class).listar();
    }

    @Override
    public SaidaMercadoria retrieve(String id) {
        return (SaidaMercadoria) manager.getDAO(DAOSaidaMercadoria.class).retrieve(id);
    }

    @Override
    public void update(SaidaMercadoria oldT, SaidaMercadoria newT) throws Exception {
        throw new UnsupportedOperationException("Update não é uma operação disponível");        
    }

    @Override
    public void delete(SaidaMercadoria element) throws Exception {
        throw new UnsupportedOperationException("Delete não é uma operação disponível");
    }

    @Override
    public List<String> getFkList() {
        return (List<String>) manager.getDAO(DAOSaidaMercadoria.class).getFkList();
    }

    @Override
    public void saveData(String path) {
        manager.getDAO(DAOSaidaMercadoria.class).saveData(path);
    }

    @Override
    public void loadData(String path) {
        manager.getDAO(DAOSaidaMercadoria.class).loadData(path);
    }

}
