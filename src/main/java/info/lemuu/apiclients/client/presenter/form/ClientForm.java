package info.lemuu.apiclients.client.presenter.form;

import info.lemuu.apiclients.client.model.Client;

/**
 * @author Lemuel Brenner
 */
public class ClientForm extends Client {

    public Client update(Client client) {
        client.setName(this.getName());
        client.setEmail(this.getEmail());
        client.setRG(this.getRG());
        client.setCPF(this.getCPF());
        client.setAddress(this.getAddress());
        return client;
    }

}
