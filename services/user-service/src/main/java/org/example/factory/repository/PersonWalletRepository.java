package org.example.factory.repository;

import org.example.factory.entity.PersonWallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonWalletRepository extends JpaRepository<PersonWallet, Long> {
}
