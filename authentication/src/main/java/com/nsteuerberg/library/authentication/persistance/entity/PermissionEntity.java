package com.nsteuerberg.library.authentication.persistance.entity;

import com.nsteuerberg.library.authentication.util.constants.Permissions;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "permissions")
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(nullable = false, unique = true)
    @Enumerated(value = EnumType.STRING)
    private Permissions permission;

}
