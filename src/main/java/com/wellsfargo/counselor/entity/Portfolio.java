package com.wellsfargo.counselor.entity;

import java.time.LocalDate;
import java.util.*;

import jakarta.persistence.*;

@Entity
public class Portfolio {
    @Id
    @GeneratedValue()
    private int id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "portfolio", orphanRemoval = true)
    private List<Security> securities = new ArrayList<>();

    public List<Security> getSecurities() {
        return this.securities;
    };

    public void addSecurity(String securityName, String category, LocalDate purchaseDate, double purchasePrice, int quantity) {
        Security security = new Security(securityName, category, purchaseDate, purchasePrice, quantity);
        securities.add(security);
    }

    public boolean removeSecurityById(int securityId) {
        return securities.removeIf(security -> security.getSecurityId() == securityId);
    }
}
