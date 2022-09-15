package peaksoft.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaginationResponse {

    private List<PersonResponse> personResponses;
    private int currentPage;
    private int totalPage;
}
