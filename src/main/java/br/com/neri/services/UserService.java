package br.com.neri.services;


import br.com.neri.exceptions.UserNotFoundException;
import br.com.neri.persistence.dao.UserDao;
import br.com.neri.persistence.dto.UserDto;
import br.com.neri.persistence.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserService {

    @Inject
    UserDao userDao;

    public void addUser(UserDto userData) throws InvocationTargetException, IllegalAccessException {
      User user = new User();
      BeanUtils.copyProperties(user, userData);
      this.userDao.save(user);

    }

    public List<User> getUsers(){
        return this.userDao.getAll();
    }

    public Optional<User> getUser(Long id){
        return this.userDao.get(id);
    }

    public User updateUser(Long id, UserDto userData) throws Exception {
        User user = this.getUser(id).orElseThrow(() -> new Exception(String.format("Usuario com codigo %s nao encontrado ", id.toString())));
        BeanUtils.copyProperties(user, userData);
        this.userDao.update(user);
        return user;
    }

    public void deleteUser(Long id) throws Exception {
        try {
            this.userDao.delete(id);
        }
        catch (IllegalArgumentException e){
            throw new UserNotFoundException(String.format("Usuario com codigo %s nao encontrado ", id.toString()));
        }
        catch (Exception e){
            throw new Exception("Erro ao deletar usuário: " + e.getMessage());
        }


    }
}
