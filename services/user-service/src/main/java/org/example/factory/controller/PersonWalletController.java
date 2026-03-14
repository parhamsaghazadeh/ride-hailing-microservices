package org.example.factory.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.factory.Model.Converter;
import org.example.factory.Model.PersonModel;
import org.example.factory.Model.PersonWalletModel;
import org.example.factory.entity.PersonWallet;
import org.example.factory.service.PersonWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Wallet")
@Slf4j
public class PersonWalletController {
    @Autowired
    private PersonWalletService personWalletService;
    @Autowired
    private Converter converter;

    @GetMapping
    public ResponseEntity<List<PersonWalletModel>> getWallet(){
        try {
            List<PersonWallet> personWallet = personWalletService.getAllWallets();
            List<PersonWalletModel> personWalletModel = personWallet.stream()
                    .map(converter::toPersonWalletModel)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(personWalletModel);
        }
        catch (Exception e){
            log.error(e.getMessage());
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<PersonWalletModel> searchWallet(@RequestParam long id) {
        try {
            PersonWallet personWallet = personWalletService.getWalletById(id);
            PersonWalletModel personWalletModel = converter.toPersonWalletModel(personWallet);
            return ResponseEntity.ok(personWalletModel);
        }
        catch (Exception e){
            log.error(e.getMessage());
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<PersonWalletModel> createWallet(@RequestBody PersonWallet personWallet ) {
        try {
            PersonWallet addPersonWallet = personWalletService.saveWallet(personWallet);
            PersonWalletModel personWalletModel = converter.toPersonWalletModel(addPersonWallet);
            return ResponseEntity.ok(personWalletModel);
        }catch (Exception e){
            log.error(e.getMessage());
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity<PersonWalletModel> updateWallet(@RequestBody PersonWallet personWallet) {
        try {
            PersonWallet updatePersonWallet = personWalletService.updateWallet(personWallet);
            PersonWalletModel personWalletModel = converter.toPersonWalletModel(updatePersonWallet);
            return ResponseEntity.ok(personWalletModel);
        }
        catch (Exception e){
            log.error(e.getMessage());
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteWallet(Long id) {
        try {
            personWalletService.deleteWallet(id);
            return ResponseEntity.ok("Deleted");
        }
        catch (Exception e){
            log.error(e.getMessage());
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
