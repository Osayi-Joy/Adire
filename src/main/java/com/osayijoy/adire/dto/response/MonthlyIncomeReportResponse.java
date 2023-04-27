package com.osayijoy.adire.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class MonthlyIncomeReportResponse {

    private HashMap<String,Report> chartDetails;
}
