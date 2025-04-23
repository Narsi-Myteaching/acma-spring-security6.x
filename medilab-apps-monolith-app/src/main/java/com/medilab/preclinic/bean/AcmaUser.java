package com.medilab.preclinic.bean;

import lombok.Data;

import java.io.Serializable;


@Data
public class AcmaUser implements Serializable {

    private String userName;
    private String password;
}
