package org.example.factory.service;

import jakarta.transaction.Transactional;
import org.example.factory.repository.PersonWalletRepository;
import org.example.factory.entity.PersonWallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    public PersonWallet findWalletById(Long id) {
        return personWalletRepository.findById(id).orElseGet(() -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ride not found");
                }
        );
    }


    @Transactional
    public PersonWallet deposit(Long id, BigDecimal amount) {
        PersonWallet personWallet = findWalletById(id);

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Amount must be greater than 0");
        }

        personWallet.setBalance(personWallet.getBalance().add(amount));

        personWallet.setUpdateAt(LocalDateTime.now());

        return personWalletRepository.save(personWallet);
    }


    @Transactional
    public PersonWallet withdraw(Long id, BigDecimal amount) {
        PersonWallet personWallet = findWalletById(id);

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Amount must be greater than 0");
        }

        if (personWallet.getBalance().compareTo(amount) < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient balance");
        }

        personWallet.setBalance(personWallet.getBalance().subtract(amount));

        personWallet.setUpdateAt(LocalDateTime.now());

        return personWalletRepository.save(personWallet);
    }
}
