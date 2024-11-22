package com.wellsfargo.counselor.entity;

import java.time.LocalDate;
import java.util.*;
import jakarta.persistence.*;

@Entity
public class Advisor {

    @Id
    @GeneratedValue()
    private long advisorId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "advisorId")
    private List<Client> clients = new ArrayList<>();

    protected Advisor() {

    }

    public Advisor(String firstName, String lastName, String address, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public Long getAdvisorId() {
        return advisorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public Client createClient(String userName, String firstName, String lastName, String address, String phone, String email) {
        Client client = new Client(userName, firstName, lastName, address, phone, email);
        this.addClient(client);
        return client;
    }

    public boolean updateClient(int clientId, String firstName, String lastName, String address, String phone, String email) {
        for (Client client : clients) {
            if (client.getClientId() == clientId) {
                client.setFirstName(firstName);
                client.setFirstName(lastName);
                client.setFirstName(address);
                client.setFirstName(phone);
                client.setEmail(email);
                return true;
            }
        }
        return false;
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }

    public void createSecurityForClient(Client client, String securityName, String category, LocalDate purchaseDate, double purchasePrice, int quantity) {
        Portfolio portfolio = client.getPortfolio();
        portfolio.addSecurity(securityName, category, purchaseDate, purchasePrice, quantity);
    }

    public boolean updateSecurityForClient(Client client, int securityId, String securityName, String category, LocalDate purchaseDate, double purchasePrice, int quantity) {
        Portfolio portfolio = client.getPortfolio();
        for (Security security : portfolio.getSecurities()) {
            if (security.getSecurityId() == securityId) {
                security.setSecurityName(securityName);
                security.setCategory(category);
                security.setPurchaseDate(purchaseDate);
                security.setPurchasePrice(purchasePrice);
                security.setQuantity(quantity);
                return true;
            }
        }
        return false;
    }
    
    public boolean removeSecurityFromClient(Client client, int securityId) {
        Portfolio portfolio = client.getPortfolio();
        return portfolio.removeSecurityById(securityId);
    }

}
