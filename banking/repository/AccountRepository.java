package dips.firstproject.banking.repository;

import dips.firstproject.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account , Long> {


}
