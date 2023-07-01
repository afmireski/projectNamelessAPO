package daos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author afmireski
 */
public class DAOManager {
    private List<DAOGeneric> daos = new ArrayList<DAOGeneric>();

    public DAOManager() {
        daos.add(new DAOFuncionario());
        daos.add(new DAOMercadoria());
        daos.add(new DAOSaidaMercadoria());
    }
    
    public DAOGeneric getDAO(Class cls) {
        for (DAOGeneric dao : daos) {
            if (dao.getClass().equals(cls)) {
                return dao;
            }
        }
        
        return null;
    }
    
    
}
