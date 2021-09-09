package info.lemuu.apiclients.client.presenter;

import info.lemuu.apiclients.client.model.Client;
import info.lemuu.apiclients.client.repository.ClientRepository;
import info.lemuu.apiclients.presenters.IPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Lemuel Brenner
 */
@RestController
@RequestMapping("/api/client")
public class ClientPresenter implements IPresenter<Client> {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    @GetMapping
    public List<ResponseEntity<Client>> index(HttpServletRequest request) {
        if (Objects.isNull(request.getQueryString())) {
            return List.of(ResponseEntity.noContent().build());
        }

        var params = request.getQueryString().split("&");

        var clients = new ArrayList<Client>();
        for (String param : params) {
            String[] map = param.split("=");
            var key = map[0];
            var value = map[1];

            switch (key) {
                case "email":
                    this.clientRepository.findByEmail(value).ifPresent(clients::add);
                case "rg":
                    this.clientRepository.findByRG(value).ifPresent(clients::add);
                case "cpf":
                    this.clientRepository.findByCPF(value).ifPresent(clients::add);
            }
        }

        return clients.stream()
                .map(ResponseEntity::ok)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Client> show(@PathVariable Long id) {
        return this.clientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    @Transactional
    public ResponseEntity<Client> store(@RequestBody @Valid Client newClient) {
        Client client = this.clientRepository.save(newClient);



        return ResponseEntity.ok(client);
    }

    @Override
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Client> update(
            @PathVariable Long id,
            @RequestBody @Valid Client newClientData
    ) {
        var client = this.clientRepository.findById(id).orElse(null);

        if (Objects.isNull(client)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(client.update(newClientData));
    }

    @Override
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Client> optional = this.clientRepository.findById(id);

        if (optional.isPresent()) {
            this.clientRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}