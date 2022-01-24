package com.alkemy.DisneyAPIMickaelaTarazaga.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;


public class ListOfLongDto {

    @JsonAlias({"movies", "genres"})
    @NotEmpty
    private List<Long> list;

    public List<Long> getList() {
        return list;
    }

    public void setList(List<Long> list) {
        this.list = list;
    }

    

}
