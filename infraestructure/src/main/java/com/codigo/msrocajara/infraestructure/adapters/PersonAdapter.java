package com.codigo.msrocajara.infraestructure.adapters;

import com.codigo.msrocajara.domain.aggregates.constants.Constant;
import com.codigo.msrocajara.domain.aggregates.dto.PersonDto;
import com.codigo.msrocajara.domain.aggregates.dto.ReniecDto;
import com.codigo.msrocajara.domain.aggregates.request.PersonRequest;
import com.codigo.msrocajara.domain.ports.out.PersonServiceOut;
import com.codigo.msrocajara.infraestructure.client.ClientReniec;
import com.codigo.msrocajara.infraestructure.dao.CompanyRepository;
import com.codigo.msrocajara.infraestructure.dao.PersonRepository;
import com.codigo.msrocajara.infraestructure.entity.CompanyEntity;
import com.codigo.msrocajara.infraestructure.entity.PersonEntity;
import com.codigo.msrocajara.infraestructure.redis.RedisService;
import com.codigo.msrocajara.infraestructure.util.Util;
import com.codigo.msrocajara.infraestructure.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonAdapter implements PersonServiceOut {
    private final PersonRepository personRepository;
    private final CompanyRepository companyRepository;
    private final ClientReniec clientReniec;
    private final RedisService redisService;

    @Value("${token.api_peru}")
    private String tokenApiPeru;

    @Value("${ms.redis.expiration_time}")
    private int redisExpirationTime;

    @Override
    public PersonDto createPersonOut(PersonRequest personRequest) {
        PersonEntity personEntity = getEntity(new PersonEntity(), personRequest,false, null);
        return PersonMapper.fromEntity(personRepository.save(personEntity));
    }

    @Override
    public PersonDto updateOut(Long id, PersonRequest personRequest) {
        Optional<PersonEntity> extractedData = personRepository.findById(id);
        if(extractedData.isPresent()){
            PersonEntity personEntity = getEntity(extractedData.get(), personRequest,true, id);
            return PersonMapper.fromEntity(personRepository.save(personEntity));
        }else {
            throw new RuntimeException();
        }
    }

    private PersonEntity getEntity(PersonEntity entity, PersonRequest personRequest, boolean updateIf, Long id){

        ReniecDto reniecDto = getExecReniec(personRequest.getIdentificationNumber());

        entity.setIdentificationType(reniecDto.getTipoDocumento());
        entity.setIdentificationNumber(reniecDto.getNumeroDocumento());
        entity.setName(reniecDto.getNombres());
        entity.setLastname(reniecDto.getApellidoPaterno());
        entity.setStatus(Constant.STATUS_ACTIVE);
        entity.setEmail(personRequest.getEmail());
        entity.setPhone(personRequest.getPhone());
        entity.setAddress(personRequest.getAddress());

        CompanyEntity company = companyRepository.findByIdentificationNumber(personRequest.getCompany());
        entity.setCompany(company);

        if (updateIf) {
            entity.setId(id);
            entity.setModifiedUser(Constant.USU_ADMIN);
            entity.setModifiedDate(getTimestamp());
        } else {
            entity.setCreatedUser(Constant.USU_ADMIN);
            entity.setCreatedDate(getTimestamp());
        }
        return entity;
    }

    private ReniecDto getExecReniec(String identificationNumber){
        String authorization = "Bearer " + tokenApiPeru;
        return clientReniec.getInfoReniec(identificationNumber, authorization);
    }

    private Timestamp getTimestamp(){
        long currenTime = System.currentTimeMillis();
        return new Timestamp(currenTime);
    }
    @Override
    public Optional<PersonDto> findByIdOut(Long id) {
        String redisInfo = redisService.getFromRedis(Constant.REDIS_KEY_GET_PERSON + id);
        PersonDto personDto;
        if(redisInfo != null){
            personDto = Util.convertFromString(redisInfo, PersonDto.class);
        }else{
            personDto = PersonMapper.fromEntity(personRepository.findById(id).get());
            String dataForRedis = Util.convertToString(personDto);
            redisService.saveInRedis(Constant.REDIS_KEY_GET_PERSON + id, dataForRedis, redisExpirationTime);
        }
        return Optional.of(personDto);
    }

    @Override
    public List<PersonDto> obtenerTodosOut() {
        List<PersonDto> dtoList = new ArrayList<>();
        List<PersonEntity> entities = personRepository.findAll();
        for (PersonEntity data : entities){
            dtoList.add(PersonMapper.fromEntity(data));
        }
        return dtoList;
    }

    @Override
    public PersonDto deleteOut(Long id) {
        Optional<PersonEntity> extractedData = personRepository.findById(id);
        if(extractedData.isPresent()){
            extractedData.get().setStatus(0);
            extractedData.get().setDeletedUser(Constant.USU_ADMIN);
            extractedData.get().setDeletedDate(getTimestamp());
            return PersonMapper.fromEntity(personRepository.save(extractedData.get()));
        }else {
            throw new RuntimeException();
        }
    }
}
