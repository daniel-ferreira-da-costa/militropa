package unitins.tp1.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import unitins.tp1.dto.ArmaDTO;
import unitins.tp1.dto.ArmaResponseDTO;
import unitins.tp1.model.Arma;
import unitins.tp1.model.TipoArma;
import unitins.tp1.repository.ArmaRepository;

@ApplicationScoped
public class ArmaServiceImpl implements ArmaService {
    @Inject
    ArmaRepository repository;

    @Override
    @Transactional
    public ArmaResponseDTO insert(ArmaDTO dto) {
        Arma novaArma = new Arma();
            novaArma.setNome(dto.getNome());
            novaArma.setDescricao(dto.getDescricao());
            novaArma.setPreco(dto.getPreco());
            novaArma.setQtdNoEstoque(dto.getQtdNoEstoque());
            novaArma.setTipo(TipoArma.valueOf(dto.getTipo()));
            novaArma.setAcabamento(dto.getAcabamento());
            novaArma.setCalibre(dto.getCalibre());
            novaArma.setCapacidadeDeTiro(dto.getCapacidadeDeTiro());
            novaArma.setComprimentoDoCano(dto.getComprimentoDoCano());
            novaArma.setMarca(dto.getMarca());
            novaArma.setModelo(dto.getModelo());
            novaArma.setNumeroDaArma(dto.getNumeroDaArma());
            novaArma.setNumeroSigma(dto.getNumeroSigma());
            novaArma.setRna(dto.getRna());

        repository.persist(novaArma);

        return ArmaResponseDTO.valueOf(novaArma);
    }

    @Override
    @Transactional
    public ArmaResponseDTO update(ArmaDTO dto, Long id) {

        Arma arma = (Arma) repository.findById(id);
        if (arma != null) {
            arma.setNome(dto.getNome());
            arma.setDescricao(dto.getDescricao());
            arma.setPreco(dto.getPreco());
            arma.setQtdNoEstoque(dto.getQtdNoEstoque());
            arma.setTipo(TipoArma.valueOf(dto.getTipo()));
            arma.setAcabamento(dto.getAcabamento());
            arma.setCalibre(dto.getCalibre());
            arma.setCapacidadeDeTiro(dto.getCapacidadeDeTiro());
            arma.setComprimentoDoCano(dto.getComprimentoDoCano());
            arma.setMarca(dto.getMarca());
            arma.setModelo(dto.getModelo());
            arma.setNumeroDaArma(dto.getNumeroDaArma());
            arma.setNumeroSigma(dto.getNumeroSigma());
            arma.setRna(dto.getRna());
        } else
            throw new NotFoundException();
        return ArmaResponseDTO.valueOf(arma);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) 
        throw new NotFoundException();
    }

    @Override
    public ArmaResponseDTO findById(Long id) {
        return ArmaResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<ArmaResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
            .map(p -> ArmaResponseDTO.valueOf(p)).toList();
    }

    @Override
    public List<ArmaResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(p -> ArmaResponseDTO.valueOf(p)).toList();
    }
    
}
