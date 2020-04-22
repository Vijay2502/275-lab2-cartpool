package edu.sjsu.cmpe275.cartpool.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.persistence.criteria.Order;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Embedded
    private Address address;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    @JsonIgnoreProperties("stores")
    @XmlTransient
    private Admin admin;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @JsonIgnoreProperties("orders")
    @XmlTransient
    private Set<Order> order;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    @JsonIgnoreProperties({"store"})
    @XmlTransient
    private Set<Product> products;

    public Store() {
    }

    public Store(StoreBuilder storeBuilder) {
        this.name = storeBuilder.name;
        this.address = storeBuilder.address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public static class StoreBuilder {
        private String name;
        private Address address;

        public StoreBuilder name(String name) {
            this.name = name;
            return this;
        }

        public StoreBuilder address(Address address) {
            this.address = address;
            return this;
        }


        public Store build() {
            return new Store(this);
        }
    }
}
