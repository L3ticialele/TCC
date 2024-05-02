
package br.cefetmg.space.model.service;

import br.cefetmg.space.model.dao.LocalizacaoDAO;
import br.cefetmg.space.model.dto.LocalizacaoDTO;
import br.cefetmg.space.model.idao.ILocalizacaoDAO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;

public class ManterLocalizacao implements IManterLocalizacao{
    private final ILocalizacaoDAO localizacaoDAO;
     
     public ManterLocalizacao(){
         localizacaoDAO = new LocalizacaoDAO();
     }
    
    @Override
    public void cadastrar(LocalizacaoDTO localizacao) throws PersistenciaException {
       localizacaoDAO.inserir(localizacao);
    }
    
    @Override
    public boolean alterar(LocalizacaoDTO localizacao) throws PersistenciaException {
        boolean result = localizacaoDAO.atualizar(localizacao);
        return result;
    }

    @Override
    public boolean excluir(int idLocalizacao) throws PersistenciaException {
       boolean result = localizacaoDAO.delete(idLocalizacao);
       return result;
    }

    @Override
    public List<LocalizacaoDTO> pesquisarTodos() throws PersistenciaException {
       List<LocalizacaoDTO> result = localizacaoDAO.listarTodos();
       return result;
    }
}
