package boxdemo.home.learn.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateBoxFolder {

    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ParentBoxFolder parent;
}
