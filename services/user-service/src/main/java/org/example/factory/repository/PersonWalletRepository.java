package org.example.factory.repository;

import org.example.factory.entity.PersonWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonWalletRepository extends JpaRepository<PersonWallet, Long> {
}
