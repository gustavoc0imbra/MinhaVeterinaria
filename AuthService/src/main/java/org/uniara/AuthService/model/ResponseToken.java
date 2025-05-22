package org.uniara.AuthService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class ResponseToken {
    private String token;
    private String message;
}
