package com.spring.marketplace.model;

import com.spring.marketplace.model.enums.Categories;
import jakarta.persistence.Version;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "products")
@ToString(exclude = "category")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @Column(name = "version")
    @Version
    private Integer version;

    @Column(name="name")
    private String name;

    @Column(name = "description")
    @NotBlank
    private String description;

    @Column(name = "category")
    @JdbcType(PostgreSQLEnumJdbcType.class)
    private Categories category;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "quantity")
    private BigInteger quantity;

    @Column(name = "sku")
    private String sku;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "quantity_update")
    private LocalDateTime updatedAt;

    @Column(name = "is_available")
    private boolean isAvailable;
}
