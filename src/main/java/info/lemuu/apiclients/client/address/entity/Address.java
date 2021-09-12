package info.lemuu.apiclients.client.address.entity;

import info.lemuu.apiclients.model.IModel;
import info.lemuu.apiclients.validation.contraints.CEP;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author Lemuel Brenner
 */
@Data
@Entity(name = "clients_address")
public class Address implements IModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Can't use Street in black.")
    private String street;

    @Min(value = 0)
    @Max(value = Integer.MAX_VALUE)
    private int number;

    @NotBlank(message = "Can't use District in black.")
    private String district;

    @Nullable
    private String complement;

    @NotBlank(message = "Can't use CEP in black.")
    @CEP
    private String CEP;

    @Override
    public Address update(IModel newModelData) {
        var address = (Address) newModelData;

        this.setStreet(address.getStreet());
        this.setNumber(address.getNumber());
        this.setDistrict(address.getDistrict());
        this.setComplement(address.getComplement());
        this.setCEP(address.getCEP());

        return this;
    }

}