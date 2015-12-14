package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SystemUser")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private String password;  //Pleeeeease dont store me in plain text

    @Id
    private String userName;

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String city;
    private String country;
    private int zipCode;
    private int phone;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "SystemUser_USERROLE", joinColumns = {
        @JoinColumn(name = "userName", referencedColumnName = "userName")}, inverseJoinColumns = {
        @JoinColumn(name = "roleName")})
    private List<Role> roles = new ArrayList();

    public User() {
    }

    public User(String password, String userName, String firstName, String lastName, String email, String address, String city, String country, int zipCode, int phone) {
        this.password = password;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
        this.phone = phone;
    }
    
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JoinColumn
    List<Booking> listbook;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn
//    List<Booking> listbook;
//
    public void addBooking(Booking cc) {
        if (listbook == null) {
            listbook = new ArrayList<>();
            
        }
        listbook.add(cc);
        cc.setUser(this);
    }

    public List<String> getRolesAsStrings() {
        List<String> rolesAsStrings = new ArrayList();
        for (Role role : roles) {
            rolesAsStrings.add(role.getRoleName());
        }
        return rolesAsStrings;
    }

    public List<Booking> getListbook() {
        return listbook;
    }

    
    
    public void AddRole(Role role) {
        roles.add(role);
        role.addUser(this);
    }

    public List<Role> getRoles() {
        return roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
