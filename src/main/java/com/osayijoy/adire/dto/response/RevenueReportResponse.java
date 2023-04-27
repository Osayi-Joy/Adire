package com.osayijoy.adire.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RevenueReportResponse {

    private Double totalRevenue;
    private Integer numberOfOrders;
}
