package controllers;

import daos.DAOFuncionario;
import daos.DAOMercadoria;
import daos.DAOSaidaMercadoria;
import java.util.Date;
import java.util.List;
import models.Funcionario;
import models.Mercadoria;
import models.SaidaMercadoria;

/**
 *
 * @author afmireski
 */
public class SaidaMercadoriaController extends ControllerGeneric<SaidaMercadoria> {
    
    private String mercadoriaPath = "Mercadoria.csv";
    private String funcionarioPath = "Funcionario.csv";

    @Override
    public void clearList() {
        manager.getDAO(DAOSaidaMercadoria.class).clearList();
    }

    @Override
    public void create(SaidaMercadoria element) throws Exception {
        try {
            if (element.getQuantidadeSaida() < 1) {                
                throw new Exception("A quantidade da saida deve ser maior que 1!");
            }
            
            this.manager.getDAO(DAOMercadoria.class).loadData(mercadoriaPath);
            Mercadoria mercadoria = (Mercadoria) this.manager.getDAO(DAOMercadoria.class).retrieve(element.getIdMercadoria());

            if (mercadoria == null) {
                throw new Exception("A mercadoria não existe!");
            }

            this.manager.getDAO(DAOFuncionario.class).loadData(funcionarioPath);
            Funcionario funcionario = (Funcionario) this.manager.getDAO(DAOFuncionario.class).retrieve(element.getIdCriador());

            if (funcionario == null) {
                throw new Exception("O funcionario não existe!");
            } else if (element.getQuantidadeSaida() > mercadoria.getQuantidadeEmEstoque()) {                
                throw new Exception("Não é possível retirar mais do que há em estoque!");
            }

            //Adiciona um Objeto T a lista;
            element.setDataSaida(new Date());
            manager.getDAO(DAOSaidaMercadoria.class).create(element);
            ((DAOMercadoria) manager.getDAO(DAOMercadoria.class))
                    .decrementarEstoque(element.getIdMercadoria(), element.getQuantidadeSaida());
            ((DAOMercadoria) manager.getDAO(DAOMercadoria.class)).saveData(mercadoriaPath);
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
