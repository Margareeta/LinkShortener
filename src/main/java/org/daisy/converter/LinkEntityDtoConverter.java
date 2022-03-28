package org.daisy.converter;

import org.daisy.dto.LinkEntityDto;
import org.daisy.model.LinkEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LinkEntityDtoConverter {
    private ModelMapper mapper = new ModelMapper();

    public LinkEntityDto toDto(LinkEntity linkEntity) {
        return mapper.map(linkEntity, LinkEntityDto.class);
    }
}
