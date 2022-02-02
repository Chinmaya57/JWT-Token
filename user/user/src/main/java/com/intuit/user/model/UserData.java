package com.intuit.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
public class UserData {

    @Id
    private String userid;
    private String name;
    private String emailId;
    private String accessLevel;
    private int status;


}
