package kr.ac.hansung.cse.SpringDataJpaAndSecurity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product 이름은 필수입니다.")
    private String name;

    @NotBlank(message = "브랜드는 필수입니다.")
    private String brand;

    @NotBlank(message = "제조국가는 필수입니다.")
    private String madeIn;

    @Min(value = 100, message = "Price는 100원 이상이어야 합니다.")
    private Long price;

    public Product(String name, String brand, String madeIn, long price) {
        this.name = name;
        this.brand = brand;
        this.madeIn = madeIn;
        this.price = price;
    }
}