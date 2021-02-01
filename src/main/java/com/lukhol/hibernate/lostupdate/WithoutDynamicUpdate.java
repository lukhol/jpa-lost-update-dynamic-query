package com.lukhol.hibernate.lostupdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class WithoutDynamicUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstField;
    private String secondField;

    public WithoutDynamicUpdate(String firstField, String secondField) {
        this.firstField = firstField;
        this.secondField = secondField;
    }
}
