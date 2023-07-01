package controllers;

import daos.DAOFuncionario;
import java.util.Date;
import java.util.List;
import models.Funcionario;

/**
 *
 * @author afmireski
 */
public class FuncionarioController extends ControllerGeneric<Funcionario> {

    @Override
    public void clearList() {
        manager.getDAO(DAOFuncionario.class).clearList();
    }

    @Override
    public void create(Funcionario element) throws Exception {
        element.setDataCadastro(new Date());
        element.setDataAtualizacao(new Date());
        manager.getDAO(DAOFuncionario.class).create(element);
    }

    @Override
    public List<Funcionario> listar() {
        return (List<Funcionario>) manager.getDAO(DAOFuncionario.class).listar();
    }

    @Override
    public Funcionario retrieve(String id) {
        return (Funcionario) manager.getDAO(DAOFuncionario.class).retrieve(id);
    }

    @Override
    public void update(Funcionario oldT, Funcionario newT) throws Exception {
        manager.getDAO(DAOFuncionario.class).update(oldT, newT);
    }

    @Override
    public void delete(Funcionario element) throws Exception {
        throw new UnsupportedOperationException("Delete não é uma operação suportada");
    }

    @Override
    public List<String> getFkList() {
        return (List<String>) manager.getDAO(DAOFuncionario.class).getFkList();
    }

    @Override
    public void saveData(String path) {
        manager.getDAO(DAOFuncionario.class).saveData(path);
    }

    @Override
    public void loadData(String path) {
        manager.getDAO(DAOFuncionario.class).loadData(path);
    }
    
}
