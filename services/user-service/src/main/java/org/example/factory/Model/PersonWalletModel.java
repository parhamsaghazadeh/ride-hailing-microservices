package org.example.factory.Model;

import lombok.Data;

@Data
public class PersonWalletModel {
    private Long id;
    private String balance;
    private String currency;
    private String created_at;
    private String updated_at;
}
