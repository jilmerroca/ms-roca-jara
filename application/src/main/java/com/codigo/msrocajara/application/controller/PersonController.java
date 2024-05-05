package com.codigo.msrocajara.application.controller;

import com.codigo.msrocajara.domain.aggregates.dto.PersonDto;
import com.codigo.msrocajara.domain.aggregates.request.PersonRequest;
import com.codigo.msrocajara.domain.ports.in.PersonServiceIn;
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
        name = "API Rest de mantenimiento de persona.",
        description = "Incluye EndPoints para realizar el amntenimiento de una persona."
)
@RestController
@RequestMapping("/ms-roca-jara/v1/persona")
@AllArgsConstructor
public class PersonController {

    private final PersonServiceIn personServiceIn;

    @Operation(summary = "Crear una persona.",
            description = "Para usar este EndPoint, debes enviar un objeto persona que será guardado en base de datos, previa validacion.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Persona creada con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDto.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = { @Content(schema = @Schema()) })
    })
    @PostMapping("/crearPersona")
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonRequest personRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personServiceIn.createPersonIn(personRequest));
    }


    @Operation(summary = "Buscar una persona por su Id.",
            description = "Para usar este EndPoint, debes enviar el Id de la persona a través de un PathVariable.",
            parameters = {
                    @Parameter(name = "id", description = "Id de búsqueda.", required = true, example = "1")
            })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Persona encontrada con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDto.class))}),
            @ApiResponse(responseCode = "404", description = "Persona no encontrada.", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/buscarxId/{id}")
    public ResponseEntity<PersonDto> findById(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personServiceIn.findByIdIn(id).get());
    }


    @Operation(summary = "Buscar todos los registros de persona.",
            description = "EndPoint que lista todos los registros persona de la base de datos.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Personas encontradas con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDto.class))}),
            @ApiResponse(responseCode = "404", description = "Personas no encontradas.", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/buscartodos")
    public ResponseEntity<List<PersonDto>> findAll(){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personServiceIn.getAllIn());
    }


    @Operation(summary = "Actualizar una persona.",
            description = "Para usar este EndPoint, debes enviar un objeto persona (sus cambios) que será guardado en base de datos, previa validacion.",
            parameters = {
                    @Parameter(name = "id", description = "Id de persona.", required = true, example = "1"),
            })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Persona actualizada con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDto.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = { @Content(schema = @Schema()) })
    })
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PersonDto> update(@PathVariable Long id, @RequestBody PersonRequest personRequest){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personServiceIn.updateIn(id, personRequest));
    }


    @Operation(summary = "Eliminar una persona por su Id.",
            description = "Para usar este EndPoint, enviar el Id de la persona a través de un PathVariable.",
            parameters = {
                    @Parameter(name = "id", description = "Id para eliminarción.", required = true, example = "1")
            })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Persona eliminada con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PersonDto.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = { @Content(schema = @Schema()) })
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<PersonDto> delete(@PathVariable Long id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(personServiceIn.deleteIn(id));
    }
}
