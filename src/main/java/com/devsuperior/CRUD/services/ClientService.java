package com.devsuperior.CRUD.services;

import com.devsuperior.CRUD.dto.ClientDto;
import com.devsuperior.CRUD.entities.Client;
import com.devsuperior.CRUD.repositories.ClientRepository;
import com.devsuperior.CRUD.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public ClientDto findById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cliente inexistente")
                );
        return new ClientDto(client);
    }

    @Transactional
    public ClientDto update(Long id, ClientDto dto) {
        try {
            Client entity = clientRepository.getReferenceById(id);
            copyDtoToClient(dto, entity);
            entity = clientRepository.save(entity);
            return new ClientDto(entity);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Cliente inexistente");
        }
    }

    @Transactional(readOnly = true)
    public Page<ClientDto> findAll(Pageable pageable) {
        Page<Client> result = clientRepository.findAll(pageable);
        return result.map(x-> new ClientDto(x));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente inexistente");
        }
        else  {
            clientRepository.deleteById(id);
        }
    }

    @Transactional
    public ClientDto insert(ClientDto dto) {
        Client entity = new Client();
        copyDtoToClient(dto, entity);
        clientRepository.save(entity);
        return new ClientDto(entity);
    }

    private void copyDtoToClient(ClientDto dto, Client entity) {

        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setBirthDate(dto.getBirthDate());
        entity.setIncome(dto.getIncome());
        entity.setChildren(dto.getChildren());

    }
}
