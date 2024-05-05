package com.codigo.msrocajara.domain.ports.out;

import com.codigo.msrocajara.domain.aggregates.dto.PersonDto;
import com.codigo.msrocajara.domain.aggregates.request.PersonRequest;

import java.util.List;
import java.util.Optional;

public interface PersonServiceOut {
    PersonDto createPersonOut(PersonRequest personRequest);
    Optional<PersonDto> findByIdOut(Long id);
    List<PersonDto> obtenerTodosOut();
    PersonDto updateOut(Long id, PersonRequest personRequest);
    PersonDto deleteOut(Long id);
}
