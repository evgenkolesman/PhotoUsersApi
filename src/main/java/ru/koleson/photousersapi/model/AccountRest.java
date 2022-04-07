package ru.koleson.photousersapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "accountrest")
public class AccountRest {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "accountId")
    private String accountId;

    private String firstName;

    private String lastName;

    private String email;

    public static AccountRest of(AccountDetailRequestModel account) {
        AccountRest accountRest = new AccountRest();
        accountRest.setFirstName(account.getFirstName());
        accountRest.setLastName(account.getLastName());
        accountRest.setEmail(account.getEmail());
        return accountRest;
    }
}
