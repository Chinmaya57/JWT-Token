package com.intuit.user.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.sql.DataSourceDefinition;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {

    private String jwtToken;

}
