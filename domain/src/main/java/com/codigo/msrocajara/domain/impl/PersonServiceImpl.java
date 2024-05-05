package com.codigo.msrocajara.domain.impl;

import com.codigo.msrocajara.domain.aggregates.request.PersonRequest;
import com.codigo.msrocajara.domain.aggregates.dto.PersonDto;
import com.codigo.msrocajara.domain.ports.in.PersonServiceIn;
import com.codigo.msrocajara.domain.ports.out.PersonServiceOut;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonServiceIn {

    private final PersonServiceOut personServiceOut;

    @Override
    public PersonDto createPersonIn(PersonRequest personRequest) {
        return personServiceOut.createPersonOut(personRequest);
    }

    @Override
    public Optional<PersonDto> findByIdIn(Long id) {
        return personServiceOut.findByIdOut(id);
    }

    @Override
    public List<PersonDto> getAllIn() {
        return personServiceOut.obtenerTodosOut();
    }

    @Override
    public PersonDto updateIn(Long id, PersonRequest personRequest) {
        return personServiceOut.updateOut(id, personRequest);
    }

    @Override
    public PersonDto deleteIn(Long id) {
        return personServiceOut.deleteOut(id);
    }
}
