package dips.firstproject.banking.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDto {

    private Long id;
    private String accountHolderName;
    private double balance;

}
