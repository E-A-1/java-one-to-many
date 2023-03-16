package com.example.springboot.application.entitiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "dummy_user_role")
public class UserRole {
/*
 *  The exception we faced yesterday below, 
 * 
 * Table [dummy_user_role] contains physical column name [role_id] referred to by multiple logical column names: [roleId], [role_id]
 * 
 * The above exception is because  we explicitly tried to set column name for the primary column in snake_case , looks like only camelCasing naming is supported for primary column . 
 * 
 * Either we don't explicitly set the column name as it takes variable name as default or just use camelCase . note it's only for primary column
 * 
 * 
 */


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "roleId")
    private int roleId;
    @Column(name = "dummy_role")
    private String dummyRole;

}
