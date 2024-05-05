package com.codigo.msrocajara.application.controller;

import com.codigo.msrocajara.domain.aggregates.dto.CompanyDto;
import com.codigo.msrocajara.domain.aggregates.request.CompanyRequest;
import com.codigo.msrocajara.domain.ports.in.CompanyServiceIn;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "API Rest de mantenimiento de empresa.",
        description = "Incluye EndPoints para realizar el amntenimiento de una empresa."
)
@RestController
@RequestMapping("/ms-roca-jara/v1/empresa")
@AllArgsConstructor
public class CompanyController {

    private final CompanyServiceIn companyServiceIn;

    @Operation(summary = "Crear una empresa.",
            description = "Para usar este EndPoint, debes enviar un objeto empresa que será guardado en base de datos, previa validacion.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa creada con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyDto.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = { @Content(schema = @Schema()) })
    })
    @PostMapping("/crearEmpresa")
    public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyRequest companyRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(companyServiceIn.createCompanyIn(companyRequest));
    }


    @Operation(summary = "Buscar una empresa por su Id.",
            description = "Para usar este EndPoint, debes enviar el Id de la empresa a través de un PathVariable.",
            parameters = {
                    @Parameter(name = "id", description = "Id de búsqueda.", required = true, example = "1")
            })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa encontrada con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyDto.class))}),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada.", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/buscarxId/{id}")
    public ResponseEntity<CompanyDto> findById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(companyServiceIn.findByIdIn(id).get());
    }


    @Operation(summary = "Buscar todos los registros de empresa.",
            description = "EndPoint que lista todos los registros empresa de la base de datos.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresas encontradas con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyDto.class))}),
            @ApiResponse(responseCode = "404", description = "Empresas no encontradas.", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/buscartodos")
    public ResponseEntity<List<CompanyDto>> findAll(){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(companyServiceIn.getAllIn());
    }


    @Operation(summary = "Actualizar una empresa.",
            description = "Para usar este EndPoint, debes enviar un objeto empresa (sus cambios) que será guardado en base de datos, previa validacion.",
            parameters = {
                    @Parameter(name = "id", description = "Id de empresa.", required = true, example = "1"),
            })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa actualizada con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyDto.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = { @Content(schema = @Schema()) })
    })
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<CompanyDto> update(@PathVariable Long id, @RequestBody CompanyRequest companyRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(companyServiceIn.updateIn(id, companyRequest));
    }


    @Operation(summary = "Eliminar una empresa por su Id.",
            description = "Para usar este EndPoint, enviar el Id de la empresa a través de un PathVariable.",
            parameters = {
                    @Parameter(name = "id", description = "Id para eliminarción.", required = true, example = "1")
            })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa eliminada con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CompanyDto.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = { @Content(schema = @Schema()) })
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<CompanyDto> delete(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(companyServiceIn.deleteIn(id));
    }
}
