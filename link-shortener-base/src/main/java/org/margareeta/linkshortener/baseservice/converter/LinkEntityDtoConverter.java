package org.margareeta.linkshortener.baseservice.converter;

import org.margareeta.linkshortener.baseservice.dto.LinkEntityDto;
import org.margareeta.linkshortener.baseservice.model.LinkEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LinkEntityDtoConverter {
    private ModelMapper mapper = new ModelMapper();

    public LinkEntityDto toDto(LinkEntity linkEntity) {
        return mapper.map(linkEntity, LinkEntityDto.class);
    }
}
