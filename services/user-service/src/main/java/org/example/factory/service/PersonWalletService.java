package org.example.factory.service;

import org.example.factory.repository.PersonWalletRepository;
import org.example.factory.entity.PersonWallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonWalletService {
    private PersonWalletRepository personWalletRepository;

    @Autowired
    public PersonWalletService(PersonWalletRepository personWalletRepository) {
        this.personWalletRepository = personWalletRepository;
    }

    public PersonWallet getWalletById(Long id) {
        return personWalletRepository.findById(id).orElse(null);
    }

    public List<PersonWallet> getAllWallets() {
        return personWalletRepository.findAll();
    }

    public PersonWallet saveWallet(PersonWallet personWallet) {
        return personWalletRepository.save(personWallet);
    }

    public void deleteWallet(long id) {
        personWalletRepository.deleteById(id);
    }

    public PersonWallet updateWallet(PersonWallet personWallet) {
        return personWalletRepository.save(personWallet);
    }
}
