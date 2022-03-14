package com.devh.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <pre>
 * Description :
 *     DEPARTMENT 테이블 엔티티
 *     자기참조테이블
 *     USER 와 M:N 관계를 갖는다.
 *     (매핑 테이블: USER_DEPARTMENT)
 * ===============================================
 * Member fields :
 *
 * ===============================================
 *
 * Author : HeonSeung Kim
 * Date   : 2022. 1. 19.
 * </pre>
 */
@Entity
@Table(name = "DEPARTMENT")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false, unique = true)
    private String name;
    @Column(length = 255, nullable = false)
    @ColumnDefault("''")
    private String description;
    @ManyToOne(/*cascade = CascadeType.ALL*/)
    @JoinColumn(name = "DEPARTMENT_ID")
    @ColumnDefault("NULL")
    private Department department;
}