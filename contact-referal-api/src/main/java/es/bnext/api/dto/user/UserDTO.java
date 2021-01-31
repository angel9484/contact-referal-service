package es.bnext.api.dto.user;

import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String name;
    private String lastName;
    private String phone;
}
