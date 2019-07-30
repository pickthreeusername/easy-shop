package com.cyc.easy.shop.commons.persistence;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public abstract class BaseEntity implements Serializable {
    private Long id;
    private Date created;
    private Date updated;


}
