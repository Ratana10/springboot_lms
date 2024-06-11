package com.kongsun.leanring.system.category;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
    @NotNull(message = "name is required")
    private String name;
}
