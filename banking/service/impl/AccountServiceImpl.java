package dips.firstproject.banking.service.impl;

import dips.firstproject.banking.dto.AccountDto;
import dips.firstproject.banking.entity.Account;
import dips.firstproject.banking.mapper.AccountMapper;
import dips.firstproject.banking.repository.AccountRepository;
import dips.firstproject.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.maptoAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.maptoAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
       Account account = accountRepository
               .findById(id)
               .orElseThrow(()-> new RuntimeException("Account does not exists"));
        return AccountMapper.maptoAccountDto(account);

    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exists"));

     double total= account.getBalance() + amount;
     account.setBalance(total);
     Account savedAccount = accountRepository.save(account);
        return AccountMapper.maptoAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exists"));

        if(account.getBalance() < amount){
            throw new RuntimeException("Insufficient Amount");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);
       Account savedAccount= accountRepository.save(account);

        return AccountMapper.maptoAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {

        List<Account> accounts = accountRepository.findAll();
       return  accounts.stream().map(account -> AccountMapper.maptoAccountDto(account))
                .collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account does not exists"));

        accountRepository.deleteById(id);

    }
}
