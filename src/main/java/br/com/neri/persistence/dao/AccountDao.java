package br.com.neri.persistence.dao;

import br.com.neri.persistence.model.Account;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AccountDao implements Dao<Account> {
    
    @Inject
    EntityManager em;
    
    @Override
    public Optional<Account> get(Long id) {
        try {
            var query = this.em.createQuery("SELECT a FROM Account a WHERE id = :id", Account.class);
            return Optional.of(query.setParameter("id", id).getSingleResult());
        }
        catch (Exception ex){
            return Optional.empty();

        }

    }

    @Override
    public List<Account> getAll(){
        var query = this.em.createQuery("SELECT a From Account a", Account.class);
        return query.getResultList();
        
    }

    @Override
    @Transactional
    public void save(Account account) {
        this.em.persist(account);

    }


    @Override
    @Transactional
    public void update(Object object) {
        this.em.merge(object);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Account a = em.find(Account.class,id);
        this.em.remove(a);

    }
}
