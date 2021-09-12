package info.lemuu.apiclients.client.presenter;

import info.lemuu.apiclients.client.dto.ClientDTO;
import info.lemuu.apiclients.client.entity.Client;
import info.lemuu.apiclients.client.repository.ClientRepository;
import info.lemuu.apiclients.presenters.IPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Lemuel Brenner
 */
@RestController
@RequestMapping("/api/client")
public class ClientPresenter implements IPresenter<Client, ClientDTO> {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    @GetMapping
    public ResponseEntity<Page<ClientDTO>> index(@PageableDefault(size = 5) Pageable pageable) {
        List<ClientDTO> clients = ClientDTO.convert(this.clientRepository.findAll(pageable).toList());
        return new ResponseEntity<>(
                new PageImpl<>(clients, pageable, clients.size()),
                HttpStatus.OK
        );
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<List<ClientDTO>> show(HttpServletRequest request) {
        if (Objects.isNull(request.getQueryString())) {
            return ResponseEntity.noContent().build();
        }
        var params = request.getQueryString().split("&");

        var clients = new ArrayList<Client>();
        for (String param : params) {
            String[] map = param.split("=");
            var key = map[0];
            var value = map[1];

            switch (key) {
                case "email":
                    this.clientRepository.findAllByEmail(value).stream().distinct().forEach(clients::add);
                case "rg":
                    this.clientRepository.findByRG(value).stream().distinct().forEach(clients::add);
                case "cpf":
                    this.clientRepository.findByCPF(value).stream().distinct().forEach(clients::add);
            }
        }

        return ResponseEntity.ok(ClientDTO.convert(clients));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> show(@PathVariable Long id) {
        return this.clientRepository.findById(id)
                .map(client -> ResponseEntity.ok(new ClientDTO(client)))
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