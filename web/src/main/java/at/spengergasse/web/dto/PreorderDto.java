package at.spengergasse.web.dto;

import java.time.LocalDateTime;
import java.util.List;

public record PreorderDto(
        LocalDateTime placedAt,
        Long totalAmountInCents,
        List<PreorderItemDto> preorderItems
) {}
