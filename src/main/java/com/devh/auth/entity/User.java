package com.devh.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;


/**
 * <pre>
 * Description :
 *     USER table entity
 *     AUTHORITY, DEPARTMENT (M:N for each)
 *     => (USER_AUTHORITY, USER_DEPARTMENT mapping table)
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
@Table(name = "USER")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20, nullable = false, unique = true)
    private String username;
    @Column(length = 255, nullable = false)
    private String password;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 50, nullable = false, unique = true)
    private String nickname;
    @Column(length = 255, unique = true)
    @ColumnDefault("NULL")
    private String email;
    @Column(length = 13, unique = true)
    @ColumnDefault("NULL")
    private String phone;
//    @Column(length = 15, nullable = false)
    @Column(length = 15)
    @ColumnDefault("'*.*.*.*'")
    private String accessIp;
//    @Column(length = 15, nullable = false)
    @Column(length = 15)
    @ColumnDefault("''")
    private String loginFailIp;
//    @Column(nullable = false)
    @Column
    @ColumnDefault("0")
    private Integer loginFailCount;
//    @Column(length = 255, nullable = false)
    @Column(length = 255)
    @ColumnDefault("''")
    private String statusMessage;
//    @Column(length = 255, nullable = false)
    @Column(length = 255)
    @ColumnDefault("''")
    private String backgroundImage;
//    @Column(length = 255, nullable = false)
    @Column(length = 255)
    @ColumnDefault("''")
    private String profileImage;
//    @Column(nullable = false)
    @Column
    @ColumnDefault("NOW()")
    private LocalDateTime loginAt;
    @Column(nullable = true)
    private LocalDateTime loginFailedAt;
//    @Column(nullable = false)
    @Column
    @ColumnDefault("NOW()")
    private LocalDateTime passwordChangedAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @ToString.Exclude
    private final Set<UserAuthority> authorities = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @ToString.Exclude
    private final Set<UserDepartment> departments = new LinkedHashSet<>();
}
