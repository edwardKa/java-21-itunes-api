package com.itunes.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchRequest {

    @Min(value = 1)
    @Max(value = 200)
    private Integer limit;

    @NotBlank
    private String term;
    private EntityType entityType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchRequest that = (SearchRequest) o;
        return Objects.equals(limit, that.limit) &&
                Objects.equals(term, that.term) &&
                entityType == that.entityType;
    }

    @Override
    public int hashCode() {

        return Objects.hash(limit, term, entityType);
    }
}
