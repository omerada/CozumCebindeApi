package com.omerada.cozumcebinde.core.security.models;

import lombok.Data;
import com.omerada.cozumcebinde.core.domain.BaseDTO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDTO extends BaseDTO<User> {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Long tcKimlikNo;
    private String ad;
    private String soyad;
    private Date guncellemeZamani;
    private boolean aktif;
    private Set<Role> roles = new HashSet<>();

    @Override
    public User toEntity() {
        return getModelMapper().map(this,User.class);
    }
}
