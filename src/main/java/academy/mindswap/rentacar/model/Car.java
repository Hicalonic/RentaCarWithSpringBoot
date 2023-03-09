package academy.mindswap.rentacar.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private Integer costPerHour;
    @Column(nullable = false, unique = true)
    private String licensePlate;
    @Column(nullable = false)
    private Integer manufacturingYear;

    @OneToMany(mappedBy = "cars")
    private List<Rental> rentalList;


}