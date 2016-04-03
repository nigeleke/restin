package com.nigeleke.restin.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Entity
public @Data @XmlRootElement class AnEntity implements Serializable {

    @Override
    public String toString() {
        return "com.nigeleke.restin.AnEntity[ id=" + id + ", name=" + name + "]";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    private static final long serialVersionUID = 1L;

}
