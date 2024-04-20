package com.codigo.rocajara.controller;

import com.codigo.rocajara.entity.EmpresaEntity;
import com.codigo.rocajara.service.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/examen/v1/empresa")
@AllArgsConstructor
@Tag(
        name = "API Rest de mantenimiento de empresas.",
        description = "Esta API Rest incluye EndPoints para realizar el amntenimiento de la entidad Empresa."
)
public class EmpresaController {

    private final EmpresaService empresaService;

    @Operation(summary = "Crear una Empresa nueva.",
            description = "Para usar este EndPoint debes enviar una objeto Empresa que será guardado en Base de datos, previa validacion.",
            parameters = {
                    @Parameter(name = "razonSocial", description = "Razón social de la empresa.", required = true, example = "Ripley SA"),
                    @Parameter(name = "tipoDocumento", description = "Tipo de documento de la empresa.", required = true, example = "6"),
                    @Parameter(name = "numeroDocumento", description = "Número de documento de la empresa.", required = true, example = "20700468795"),
                    @Parameter(name = "condicion", description = "Condición de la empresa.", required = true, example = "Habido"),
                    @Parameter(name = "direccion", description = "Dirección de la empresa.", required = true, example = "Av. La Mar 123"),
                    @Parameter(name = "distrito", description = "Distrito de la empresa.", required = true, example = "San Miguel"),
                    @Parameter(name = "provincia", description = "Provincia de la empresa.", required = true, example = "Lima"),
                    @Parameter(name = "departamento", description = "Departamento de la empresa.", required = true, example = "Lima"),
                    @Parameter(name = "esAgenteRetencion", description = "Indicar si la empresa es agente de retención.", required = true, example = "true"),
                    @Parameter(name = "estado", description = "Empresa activa o no", required = true, example = "1")
            })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa creada con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = EmpresaEntity.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = { @Content(schema = @Schema()) })
    })
    @PostMapping("/crear")
    public ResponseEntity<EmpresaEntity> crear(@RequestBody EmpresaEntity empresaEntity){
        return ResponseEntity.ok(empresaService.crear(empresaEntity));
    }


    @Operation(summary = "Buscar una Empresa por su Id.",
            description = "Para usar este EndPoint debes el Id de la Empresa a través de un PathVariable.",
            parameters = {
                    @Parameter(name = "Id", description = "Id de búsqueda.", required = true, example = "1"),
            })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa encontrada con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = EmpresaEntity.class))}),
            @ApiResponse(responseCode = "404", description = "Empresa no encontrada.", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/buscarxId/{id}")
    public ResponseEntity<Optional<EmpresaEntity>> buscarxId(@PathVariable Long id){
        return ResponseEntity.ok(empresaService.buscarPorId(id));
    }


    @Operation(summary = "Buscar una todos los registros de Empresa.",
            description = "EndPoint que lista todos los registros Empresa de la base de datos.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresas encontradas con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = EmpresaEntity.class))}),
            @ApiResponse(responseCode = "404", description = "Empresas no encontradas.", content = { @Content(schema = @Schema()) })
    })
    @GetMapping("/buscarall")
    public ResponseEntity<List<EmpresaEntity>> buscarAll(){
        return ResponseEntity.ok(empresaService.buscarTodos());
    }


    @Operation(summary = "Actualizar una Empresa.",
            description = "Para usar este EndPoint debes enviar una objeto Empresa (sus modificaciones) que será guardado en Base de datos, previa validacion.",
            parameters = {
                    @Parameter(name = "Id", description = "Id de la empresa.", required = true, example = "1"),
                    @Parameter(name = "razonSocial", description = "Razón social de la empresa.", required = true, example = "Falabella SA"),
                    @Parameter(name = "tipoDocumento", description = "Tipo de documento de la empresa.", required = true, example = "6"),
                    @Parameter(name = "numeroDocumento", description = "Número de documento de la empresa.", required = true, example = "20460178549"),
                    @Parameter(name = "condicion", description = "Condición de la empresa.", required = true, example = "Habido"),
                    @Parameter(name = "direccion", description = "Dirección de la empresa.", required = true, example = "Av. Independencia 456"),
                    @Parameter(name = "distrito", description = "Distrito de la empresa.", required = true, example = "Los Olivos"),
                    @Parameter(name = "provincia", description = "Provincia de la empresa.", required = true, example = "Lima"),
                    @Parameter(name = "departamento", description = "Departamento de la empresa.", required = true, example = "Lima"),
                    @Parameter(name = "esAgenteRetencion", description = "Indicar si la empresa es agente de retención.", required = true, example = "true"),
                    @Parameter(name = "estado", description = "Empresa activa o no", required = true, example = "1")
            })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa actualizada con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = EmpresaEntity.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = { @Content(schema = @Schema()) })
    })
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<EmpresaEntity> actualizar(@PathVariable Long id, @RequestBody EmpresaEntity empresaEntity){
        return ResponseEntity.ok(empresaService.actualizar(id, empresaEntity));
    }


    @Operation(summary = "Eliminar una Empresa por su Id.",
            description = "Para usar este EndPoint enviar el Id de la Empresa a través de un PathVariable.",
            parameters = {
                    @Parameter(name = "Id", description = "Id para eliminarción.", required = true, example = "1"),
            })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empresa eliminada con éxito.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = EmpresaEntity.class))}),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor.", content = { @Content(schema = @Schema()) })
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<EmpresaEntity> eliminar(@PathVariable Long id){
        return ResponseEntity.ok(empresaService.borrar(id));
    }
}
