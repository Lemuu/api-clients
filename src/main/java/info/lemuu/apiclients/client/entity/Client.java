package info.lemuu.apiclients.client.entity;

import info.lemuu.apiclients.client.address.entity.Address;
import info.lemuu.apiclients.model.IModel;
import info.lemuu.apiclients.validation.contraints.RG;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author Lemuel Brenner
 */
@Data
@Entity(name = "clients")
public class Client implements IModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Can't use name in blank.")
    @Pattern(regexp = "^[A-Z]+(.)*", message = "Name need start with the first letter uppercase")
    @Column(nullable = false)
    private String name;

    @Email(message = "Email invalid!")
    @NotBlank(message = "Can't use email in blank.")
    private String email;

    @RG
    @NotBlank(message = "Can't use RG in blank.")
    @Column(unique = true, nullable = false)
    private String RG;

    @CPF(message = "CPF invalid!")
    @NotBlank(message = "Can't use CPF in blank.")
    @Column(unique = true, nullable = false)
    private String CPF;

    @NotNull(message = "Date can't be null.")
    @Pattern(regexp = "^\\d{4}/\\d{2}/\\d{2}$", message = "The birthday is invalid!")
    private String birthday;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Override
    public Client update(IModel newModelData) {
        var client = (Client) newModelData;

        this.setName(client.getName());
        this.setEmail(client.getEmail());
        this.setRG(client.getRG());
        this.setCPF(client.getCPF());
        this.getAddress().update(client.getAddress());

        return this;
    }

}