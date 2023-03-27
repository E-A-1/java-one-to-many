package com.example.springboot.application.entitiy;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "dummy_user")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name")
    private String name;
/*
 * I am using the  --  private List<UserRole> roles, because the relation here is OnetoMany
 * 
 * If it is ManyToOne , just use -- private UserRole role
 * 
 * you can set the fetch policy in manyToOne annotation , the fetch type is set to eager , it will fetch records from
 * the userRoles table too. if the fetch policy is lazy , it wont fetch the records 
 * 
 */

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "roleId",nullable = true)  
    private List<UserRole> roles;
    @Column(name = "start_date",columnDefinition = "date")
private Date startDate;

    private String userPhoneNumber;
}
