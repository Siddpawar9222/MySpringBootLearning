package com.learning.audit.entity;

import com.learning.audit.config.AuditListener;
import com.learning.audit.config.AuditableField;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "spring_health_student")
@Setter
@Getter
@NoArgsConstructor
@EntityListeners(AuditListener.class)
public class Student implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @AuditableField
    private String name;
    @AuditableField
    private String email;


    @Transient
    private Student originalState;

    @PostLoad
    public void saveOriginalState() {
        this.originalState = this.clone();
    }


    @Override
    public Student clone() {
        Student studentDeepCopy = new Student();
        studentDeepCopy.setId(id);
        studentDeepCopy.setName(name);
        studentDeepCopy.setEmail(email);
        return studentDeepCopy;
    }
}
