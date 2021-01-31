package es.bnext.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "contact")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Contact {
    @Id
    @GeneratedValue
    private int id;

    @NotBlank
    @NotNull
    @Column(name = "user_id", nullable = false)
    private int userId;

    @NotBlank
    @NotNull
    @Column(name = "contact_name", nullable = false)
    private String contactName;

    @NotBlank
    @NotNull
    @Column(name = "phone", nullable = false)
    private String phone;
}
