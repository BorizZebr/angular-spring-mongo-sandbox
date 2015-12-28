package com.whitesoft.pinmap.converters;

import com.whitesoft.pinmap.domain.Sub;
import com.whitesoft.pinmap.dto.SubDTO;
import org.springframework.stereotype.Service;

/**
 * Created by borisbondarenko on 28.12.15.
 *
 * Converter to convert Sub domain objects to their DTO representation.
 *
 * @author brzzbr
 */
@Service
public class SubDTOConverter implements Converter<Sub, SubDTO> {
    @Override
    public SubDTO convert(Sub fromObj) {
        return null;
    }
}
