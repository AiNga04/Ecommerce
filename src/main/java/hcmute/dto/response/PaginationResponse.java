package hcmute.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PaginationResponse {
    private int totalPages;
    private int totalItems;
    private int page;
    private int limit;
    private List<?> items;
}
