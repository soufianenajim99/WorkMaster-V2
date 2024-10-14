package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name="admins")
public class Admin extends User {
    public Admin() {
    }
    public Admin(String address, String password, String username) {
        super(address, password, username);
    }
    public Admin(UUID id, String username, String password, String address) {
        super(id, username, password, address);
    }
}
