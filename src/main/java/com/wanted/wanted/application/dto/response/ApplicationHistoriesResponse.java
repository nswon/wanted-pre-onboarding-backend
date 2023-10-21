package com.wanted.wanted.application.dto.response;

import java.util.List;

public record ApplicationHistoriesResponse(
    List<ApplicationHistoryResponse> applicationHistoryResponses
) {
}
