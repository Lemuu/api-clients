package info.lemuu.apiclients.client.dto;

import info.lemuu.apiclients.client.address.entity.Address;
import info.lemuu.apiclients.client.entity.Client;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lemuel Brenner
 */
@Getter
public class ClientDTO {

    private final long id;
    private final String name, email, RG, CPF, birthday;
    private final Address address;
    private int old;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.email = client.getEmail();
        this.RG = client.getRG();
        this.CPF = client.getCPF();
        this.birthday = client.getBirthday();
        this.address = client.getAddress();
        this.setOld();
    }

    private void setOld() {
        String yearBirthday = this.birthday.split("/")[0];
        try {
            int year = Integer.parseInt(yearBirthday);
            this.old = LocalDateTime.now().getYear() - year;
        } catch(Exception exception) {
            throw new RuntimeException("Error at create old of Client. Please check the birthday date.");
        }
    }

    public static List<ClientDTO> convert(List<Client> clients) {
        return clients.stream().map(ClientDTO::new).collect(Collectors.toList());
    }

}
