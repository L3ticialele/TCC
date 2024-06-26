
package br.cefetmg.space.model.dao;

import br.cefetmg.space.model.dto.EquipeDTO;
import br.cefetmg.space.model.idao.IEquipeDAO;
import br.cefetmg.space.model.idao.exception.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

public class EquipeDAO implements IEquipeDAO{
    @Override
    public void inserir(EquipeDTO equipe) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(equipe);
            entityManager.getTransaction().commit();
            System.out.println("Equipe cadastrada!");
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            throw ex;
        }finally{
            entityManager.close();
        }
    }
    
    @Override
    public List<EquipeDTO> listarTodos() throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        CriteriaQuery<EquipeDTO> criteria = 
        entityManager.getCriteriaBuilder().createQuery(EquipeDTO.class);
        criteria.select(criteria.from(EquipeDTO.class));
        List<EquipeDTO> equipes = entityManager.createQuery(criteria).getResultList();
        
        if(!equipes.isEmpty()){
            for(EquipeDTO equipe : equipes){
                System.out.println("Id equipe: " + equipe.getId() 
                        + " Nome: " + equipe.getNome() 
                        + " CubeSats feitos/em andamento: " + equipe.quantCubeSat() 
                        + " Quantidade de Integrantes: " + equipe.quantIntegrantes() 
                        + " Quantidade de administradores: " + equipe.quantAdministradores());
        }
        }
        entityManager.close();
        return equipes;
    }
       
    @Override
    public boolean delete(int idEquipe) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            EquipeDTO equipe = entityManager.find(EquipeDTO.class, idEquipe);
            
            if(equipe != null){
                entityManager.remove(equipe);
                entityManager.getTransaction().commit();
                return true;
            }else{
                System.out.println("Não foi possível encontrar a equipe com o id: " + idEquipe);
                return false;
            }
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            throw ex;
        }finally{
            entityManager.close();
        }
    }
    
    @Override
    public boolean atualizar(EquipeDTO equipe) throws PersistenciaException{
        EntityManagerFactory entityManagerFactory = 
        Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        try{
            entityManager.getTransaction().begin();
            EquipeDTO equipePersistida = entityManager.find(EquipeDTO.class, equipe.getId());
            
            if(equipePersistida != null){
                equipePersistida.setAdministrador(equipe.getAdministrador());
                equipePersistida.setCubeSat(equipe.getCubeSat());
                equipePersistida.setIntegrante(equipePersistida.getIntegrantes());
                equipePersistida.setEmail(equipe.getEmail());
                equipePersistida.setId(equipe.getId());
                equipePersistida.setNome(equipe.getNome());
                equipePersistida.setSenha(equipe.getSenha());
                equipePersistida.setUserName(equipe.getUserName());
                entityManager.getTransaction().commit();
                return true;
            }else{
                System.out.println("Não foi possível encontrar a equipe com o id: " + equipe.getId());
                return false;
            }
        }catch(Exception ex){
            entityManager.getTransaction().rollback();
            throw ex;
        }finally{
            entityManager.close();
        }
    }
}
