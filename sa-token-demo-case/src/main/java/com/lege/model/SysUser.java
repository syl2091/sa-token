package com.lege.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lege
 * @Description
 * @create 2022-11-25 10:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements Serializable {
    private static final long serialVersionUID = -2853125262828437774L;
    private Integer id;
    private String name;
    private Integer age;
}
