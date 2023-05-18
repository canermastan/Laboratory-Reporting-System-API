package com.canermastan.laboratoryreportingsystemapi.controller.report;

import com.canermastan.laboratoryreportingsystemapi.entity.dtos.ReportDto;

import java.util.List;

public record ReportPaginationResponse(List<ReportDto> data, int totalPages) {
}