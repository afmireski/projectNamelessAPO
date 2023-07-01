package daos;

import functions.ConvertToEnum;
import java.util.ArrayList;
import java.util.List;
import tools.CaixaDeFerramentas;
import tools.ManipulaArquivo;

/**
 *
 * @author afmireski
 */
public abstract class DAOGeneric<T> {

    protected final ConvertToEnum convert = new ConvertToEnum();
    protected final CaixaDeFerramentas cf = new CaixaDeFerramentas();

    protected List<T> list = new ArrayList<T>();
    
    protected DAOManager manager;

    public DAOGeneric() {        
    }

    public void clearList() {
        //Limpa a lista;
        list.clear();
    }

    public void create(T element) {
        //Adiciona um Objeto T a lista;
        list.add(element);
    }

    public List<T> listar() {
        return list;
    }

    public abstract T retrieve(String id);

    public void update(T oldT, T newT) {
        list.set(list.indexOf(oldT), newT);
    }

    public void delete(T element) {
        list.remove(element);
    }

    public abstract List<String> getFkList();

    public void saveData(String path) {
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        List<String> stringList = new ArrayList<>();
        for (T element : list) {
            stringList.add(element.toString());
        }
        manipulaArquivo.salvarArquivo(path, stringList);
    }

    public abstract void loadData(String path);
}
