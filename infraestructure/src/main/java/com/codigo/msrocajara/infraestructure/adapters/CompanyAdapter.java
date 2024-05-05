package com.codigo.msrocajara.infraestructure.adapters;

import com.codigo.msrocajara.domain.aggregates.constants.Constant;
import com.codigo.msrocajara.domain.aggregates.dto.CompanyDto;
import com.codigo.msrocajara.domain.aggregates.dto.SunatDto;
import com.codigo.msrocajara.domain.aggregates.request.CompanyRequest;
import com.codigo.msrocajara.domain.ports.out.CompanyServiceOut;
import com.codigo.msrocajara.infraestructure.client.ClientSunat;
import com.codigo.msrocajara.infraestructure.dao.CompanyRepository;
import com.codigo.msrocajara.infraestructure.entity.CompanyEntity;
import com.codigo.msrocajara.infraestructure.mapper.CompanyMapper;
import com.codigo.msrocajara.infraestructure.redis.RedisService;
import com.codigo.msrocajara.infraestructure.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyAdapter implements CompanyServiceOut {
    private final CompanyRepository companyRepository;
    private final ClientSunat clientSunat;
    private final RedisService redisService;

    @Value("${token.api_peru}")
    private String tokenApiPeru;

    @Value("${ms.redis.expiration_time}")
    private int redisExpirationTime;

    @Override
    public CompanyDto createCompanyOut(CompanyRequest companyRequest) {
        CompanyEntity companyEntity = getEntity(new CompanyEntity(), companyRequest,false, null);
        return CompanyMapper.fromEntity(companyRepository.save(companyEntity));
    }

    @Override
    public CompanyDto updateOut(Long id, CompanyRequest companyRequest) {
        Optional<CompanyEntity> extractedData = companyRepository.findById(id);
        if(extractedData.isPresent()){
            CompanyEntity CompanyEntity = getEntity(extractedData.get(), companyRequest,true, id);
            return CompanyMapper.fromEntity(companyRepository.save(CompanyEntity));
        }else {
            throw new RuntimeException();
        }
    }

    private CompanyEntity getEntity(CompanyEntity entity, CompanyRequest companyRequest, boolean updateIf, Long id){
        SunatDto SunatDto = getExecSunat(companyRequest.getIdentificationNumber());
        entity.setIdentificationType(SunatDto.getTipoDocumento());
        entity.setIdentificationNumber(SunatDto.getNumeroDocumento());
        entity.setSocialName(SunatDto.getRazonSocial());
        entity.setCondition(SunatDto.getCondicion());
        entity.setAddress(SunatDto.getDireccion());
        entity.setDistrict(SunatDto.getDistrito());
        entity.setProvince(SunatDto.getProvincia());
        entity.setDepartament(SunatDto.getDepartamento());
        entity.setWithHoldingAgent(SunatDto.isEsAgenteRetencion());
        entity.setStatus(Constant.STATUS_ACTIVE);

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

    private SunatDto getExecSunat(String identificationNumber){
        String authorization = "Bearer " + tokenApiPeru;
        return clientSunat.getInfoSunat(identificationNumber, authorization);
    }

    private Timestamp getTimestamp(){
        long currenTime = System.currentTimeMillis();
        return new Timestamp(currenTime);
    }
    @Override
    public Optional<CompanyDto> findByIdOut(Long id) {
        String redisInfo = redisService.getFromRedis(Constant.REDIS_KEY_GET_COMPANY + id);
        CompanyDto companyDto;
        if(redisInfo != null){
            companyDto = Util.convertFromString(redisInfo, CompanyDto.class);
        }else{
            companyDto = CompanyMapper.fromEntity(companyRepository.findById(id).get());
            String dataForRedis = Util.convertToString(companyDto);
            redisService.saveInRedis(Constant.REDIS_KEY_GET_COMPANY + id, dataForRedis, redisExpirationTime);
        }
        return Optional.of(companyDto);
    }

    @Override
    public List<CompanyDto> obtenerTodosOut() {
        List<CompanyDto> dtoList = new ArrayList<>();
        List<CompanyEntity> entities = companyRepository.findAll();
        for (CompanyEntity data : entities){
            dtoList.add(CompanyMapper.fromEntity(data));
        }
        return dtoList;
    }

    @Override
    public CompanyDto deleteOut(Long id) {
        Optional<CompanyEntity> extractedData = companyRepository.findById(id);
        if(extractedData.isPresent()){
            extractedData.get().setStatus(0);
            extractedData.get().setDeletedUser(Constant.USU_ADMIN);
            extractedData.get().setDeletedDate(getTimestamp());
            return CompanyMapper.fromEntity(companyRepository.save(extractedData.get()));
        }else {
            throw new RuntimeException();
        }
    }
}
