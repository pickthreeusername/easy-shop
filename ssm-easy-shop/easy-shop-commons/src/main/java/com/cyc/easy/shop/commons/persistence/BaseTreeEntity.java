package com.cyc.easy.shop.commons.persistence;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public abstract class BaseTreeEntity<T extends BaseEntity> extends BaseEntity {
    private T parent;
    @JsonProperty(value = "isParent")
    private boolean ifParent;
}
