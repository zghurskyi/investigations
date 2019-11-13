package com.example.demo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "greeting1")
public class GreetingTwo {
    @Id
    private Long id;
    private String value;
}
