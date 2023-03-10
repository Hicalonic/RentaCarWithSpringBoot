package academy.mindswap.rentacar.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private LocalDate rentalDate;
    @Column(nullable = false)
    private LocalDate deliveryDate;
    @ManyToOne(targetEntity = Car.class)
    @JoinColumn(name = "car_id")
    private Car car;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;


}
