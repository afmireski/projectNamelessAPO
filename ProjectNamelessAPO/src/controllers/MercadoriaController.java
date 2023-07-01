
package controllers;

import daos.DAOFuncionario;
import daos.DAOMercadoria;
import daos.DAOSaidaMercadoria;
import java.util.List;
import models.Funcionario;
import models.Mercadoria;

/**
 *
 * @author afmireski
 */
public class MercadoriaController extends ControllerGeneric<Mercadoria> {

    @Override
    public void clearList() {
        manager.getDAO(DAOSaidaMercadoria.class).clearList();
    }

    @Override
    public void create(Mercadoria element) throws Exception {
         try {
            if (element.getQuantidadeEmEstoque() < 0) {                
                throw new Exception("O estoque não pode ser negativo!");
            }

            Funcionario funcionario = (Funcionario) this.manager.getDAO(DAOFuncionario.class).retrieve(element.getIdCriador());

            if (funcionario == null) {
                throw new Exception("O funcionario não existe!");
            } 

            //Adiciona um Objeto T a lista;
            manager.getDAO(DAOMercadoria.class).create(element);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<Mercadoria> listar() {
        return (List<Mercadoria>) manager.getDAO(DAOMercadoria.class).listar();
    }

    @Override
    public Mercadoria retrieve(String id) {
        return (Mercadoria) manager.getDAO(DAOMercadoria.class).retrieve(id);
    }

    @Override
    public void update(Mercadoria oldT, Mercadoria newT) throws Exception {
        try {
            if (newT.getQuantidadeEmEstoque() < 0) {                
                throw new Exception("O estoque não pode ser negativo!");
            }

            Funcionario funcionario = (Funcionario) this.manager.getDAO(DAOFuncionario.class).retrieve(newT.getIdCriador());

            if (funcionario == null) {
                throw new Exception("O funcionario não existe!");
            } 

            //Adiciona um Objeto T a lista;
            manager.getDAO(DAOMercadoria.class).update(oldT, newT);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void delete(Mercadoria element) throws Exception {
        throw new UnsupportedOperationException("Delete não é uma operação suportada");
    }

    @Override
    public List<String> getFkList() {
        return (List<String>) manager.getDAO(DAOMercadoria.class).getFkList();
    }

    @Override
    public void saveData(String path) {
        manager.getDAO(DAOMercadoria.class).saveData(path);
    }

    @Override
    public void loadData(String path) {        
        manager.getDAO(DAOMercadoria.class).loadData(path);
    }
    
}
