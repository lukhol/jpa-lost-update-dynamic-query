package com.lukhol.hibernate.lostupdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@DynamicUpdate
@NoArgsConstructor
public class WithDynamicUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstField;
    private String secondField;

    public WithDynamicUpdate(String firstField, String secondField) {
        this.firstField = firstField;
        this.secondField = secondField;
    }
}
