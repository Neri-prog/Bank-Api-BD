package br.com.neri.services;

import br.com.neri.exceptions.AccountBadRequestException;
import br.com.neri.exceptions.AccountNotFoundException;
import br.com.neri.persistence.dao.AccountDao;
import br.com.neri.persistence.dto.AccountDto;
import br.com.neri.persistence.model.Account;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AccountService {
    @Inject
    AccountDao accountDao;
    @Inject
    UserService userService ;

    public void addAccount(AccountDto accountData, Long userId){
        Account account = new Account();
        account.setAccountType(accountData.getAccountType());
        account.setUser(userService.getUser(userId).get());
        this.accountDao.save(account);

    }

    public List<Account> getAccounts(){
        return this.accountDao.getAll();
    }

    public Optional<Account> getAccount(Long id) throws Exception {
        return Optional.ofNullable(this.accountDao.get(id).orElseThrow(() -> new Exception(String.format("Conta com codigo %s nao encontrado ", id.toString()))));
    }



    public Account depositAccount(Long id, Double value) throws Exception {
        if (!(value >0) ){
            throw new AccountBadRequestException("O valor do deposito precisar ser maior que R$0");
        }
        Account account = this.getAccount(id).get();
        if (account.getAccountType() == 2){
            value +=0.5;
        }
        account.setBalance(account.getBalance()+value);
        this.accountDao.update(account);
        return account;
    }

    public Account withdrawAccount(Long id, Double value) throws Exception {
        if (!(value >0) ){
            throw new AccountBadRequestException("O valor do saque precisa ser maior que R$0");
        }
        Account account = this.getAccount(id).get();
        if (account.getBalance() < value){
            throw new AccountBadRequestException("Saldo Insuficiente para saque");
        }
        account.setBalance(account.getBalance()-value);
        this.accountDao.update(account);
        return account;
    }

    public void deleteAccount(Long id) throws Exception {
        try {
            this.accountDao.delete(id);
        }
        catch (IllegalArgumentException e){
            throw new AccountNotFoundException(String.format("Conta com codigo %s nao encontrado ", id.toString()));
        }
        catch (Exception e){
            throw new Exception("Erro ao deletar usuário: " + e.getMessage());
        }
    }
}

