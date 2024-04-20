package com.codigo.rocajara.service.impl;

import com.codigo.rocajara.dao.EmpresaRepository;
import com.codigo.rocajara.entity.EmpresaEntity;
import com.codigo.rocajara.service.Constantes.Constantes;
import com.codigo.rocajara.service.EmpresaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;

    // Guardar
    @Override
    public EmpresaEntity crear(EmpresaEntity empresaEntity) {
        empresaEntity.setUsuaCreate(Constantes.USER001);
        empresaEntity.setDateCreate(getTimestamp());
        return empresaRepository.save(empresaEntity) ;
    }

    // Buscar por Id
    @Override
    public Optional<EmpresaEntity> buscarPorId(Long id) {
        return empresaRepository.findById(id);
    }

    // Buscar todos
    @Override
    public List<EmpresaEntity> buscarTodos() {
        return empresaRepository.findAll();
    }

    // Actualizar
    @Override
    public EmpresaEntity actualizar(Long id, EmpresaEntity empresaEntity) {
        // Se busca a la empresa en DB
        boolean existe = empresaRepository.existsById(id);
        if(existe){
            // Se obtienen la empresas por Id
            Optional empresa = empresaRepository.findById(id);

            // Se settean los datos a atualizar
            EmpresaEntity usersEntity = empresaRepository.save(getUpdate((EmpresaEntity) empresa.get(), empresaEntity));
            return usersEntity;
        }
        return null;
    }

    // Método de apoyo: Setteo de los datos a modificar
    private EmpresaEntity getUpdate(EmpresaEntity update, EmpresaEntity request){
        update.setRazonSocial(request.getRazonSocial());
        update.setTipoDocumento(request.getTipoDocumento());
        update.setNumeroDocumento(request.getNumeroDocumento());
        update.setCondicion(request.getCondicion());
        update.setDireccion(request.getDireccion());
        update.setDistrito(request.getDistrito());
        update.setProvincia(request.getProvincia());
        update.setDepartamento(request.getDepartamento());
        update.setEsAgenteRetencion(request.isEsAgenteRetencion());
        update.setEstado(request.getEstado());

        update.setUsuaModif(Constantes.USER001);
        update.setDateModif(getTimestamp());

        return update;
    }

    // Método de apoyo: para retornar a la fecha actual
    private Timestamp getTimestamp(){
        long currenTIme = System.currentTimeMillis();
        return new Timestamp(currenTIme);
    }

    // Eliminación de forma lógica
    @Override
    public EmpresaEntity borrar(Long id) {
        Optional<EmpresaEntity> empresaDB = empresaRepository.findById(id);
        if(empresaDB.isPresent()){
            empresaDB.get().setUsuaDelet("");
            empresaDB.get().setUsuaDelet(Constantes.USER001);
            empresaDB.get().setDateDelet(getTimestamp());
            empresaDB.get().setEstado(0);
            return empresaRepository.save(empresaDB.get());
        }
        return null;
    }
}
