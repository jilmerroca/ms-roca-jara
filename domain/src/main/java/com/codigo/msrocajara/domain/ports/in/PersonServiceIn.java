package com.codigo.msrocajara.domain.ports.in;

import com.codigo.msrocajara.domain.aggregates.dto.PersonDto;
import com.codigo.msrocajara.domain.aggregates.request.PersonRequest;

import java.util.List;
import java.util.Optional;

public interface PersonServiceIn {
    PersonDto createPersonIn(PersonRequest personRequest);
    Optional<PersonDto> findByIdIn(Long id);
    List<PersonDto> getAllIn();
    PersonDto updateIn(Long id, PersonRequest personRequest);
    PersonDto deleteIn(Long id);
}
