package dips.firstproject.banking.controller;


import dips.firstproject.banking.dto.AccountDto;
import dips.firstproject.banking.entity.Account;
import dips.firstproject.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private AccountService accountService;

   public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    // Add account REST API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {
        AccountDto saved = accountService.createAccount(accountDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    // Get account REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }


    // Deposit REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,
                                              @RequestBody Map<String, Double> request) {

        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id, amount);
        return ResponseEntity.ok(accountDto);


        // Withdraw REST API
   /*  @PutMapping("/{id}/withdraw")
     public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,
                                                @RequestBody Map<String, Double> request){

         double amountWithdraw = request.get("amount");
         AccountDto accountDto = accountService.withdraw(id, amount);
         return ResponseEntity.ok(accountDto);
        }
*/

      /*  // Get all account REST API
        @GetMapping
        public ResponseEntity<List<AccountDto>> getAllAccounts(){
            List<AccountDto>accounts = accountService.getAllAccounts();
            return  ResponseEntity.ok(accounts);
        }
*/

       /* // Delete account REST API
        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteAccount(@PathVariable Long id){
            accountService.deleteAccount(id);
            return ResponseEntity.ok("Accounts is deleted Successfully");
        }*/
    }
}


