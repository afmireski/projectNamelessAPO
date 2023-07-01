package controllers;

import daos.DAOManager;
import java.util.List;

/**
 *
 * @author afmireski
 */
public abstract class ControllerGeneric<T> {
    
    protected DAOManager manager;

    public ControllerGeneric() {
        manager = new DAOManager();
    }
    
    public abstract void clearList();

    public abstract void create(T element) throws Exception;

    public abstract List<T> listar();

    public abstract T retrieve(String id);

    public abstract void update(T oldT, T newT) throws Exception;

    public abstract void delete(T element) throws Exception;

    public abstract List<String> getFkList();

    public abstract void saveData(String path);

    public abstract void loadData(String path);
    
    
    
}
