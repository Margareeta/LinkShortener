package org.daisy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkEntityDto {
    private String shortLink;
    private String fullLink;
    private Long counter;
}
