package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Center;
import com.mycompany.myapp.repository.CenterRepository;
import com.mycompany.myapp.service.dto.CenterDTO;
import com.mycompany.myapp.service.mapper.CenterMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Center}.
 */
@Service
@Transactional
public class CenterService {

    private final Logger log = LoggerFactory.getLogger(CenterService.class);

    private final CenterRepository centerRepository;

    private final CenterMapper centerMapper;

    public CenterService(CenterRepository centerRepository, CenterMapper centerMapper) {
        this.centerRepository = centerRepository;
        this.centerMapper = centerMapper;
    }

    /**
     * Save a center.
     *
     * @param centerDTO the entity to save.
     * @return the persisted entity.
     */
    public CenterDTO save(CenterDTO centerDTO) {
        log.debug("Request to save Center : {}", centerDTO);
        Center center = centerMapper.toEntity(centerDTO);
        center = centerRepository.save(center);
        return centerMapper.toDto(center);
    }

    /**
     * Update a center.
     *
     * @param centerDTO the entity to save.
     * @return the persisted entity.
     */
    public CenterDTO update(CenterDTO centerDTO) {
        log.debug("Request to update Center : {}", centerDTO);
        Center center = centerMapper.toEntity(centerDTO);
        center = centerRepository.save(center);
        return centerMapper.toDto(center);
    }

    /**
     * Partially update a center.
     *
     * @param centerDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CenterDTO> partialUpdate(CenterDTO centerDTO) {
        log.debug("Request to partially update Center : {}", centerDTO);

        return centerRepository
            .findById(centerDTO.getId())
            .map(existingCenter -> {
                centerMapper.partialUpdate(existingCenter, centerDTO);

                return existingCenter;
            })
            .map(centerRepository::save)
            .map(centerMapper::toDto);
    }

    /**
     * Get all the centers.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CenterDTO> findAll() {
        log.debug("Request to get all Centers");
        return centerRepository.findAll().stream().map(centerMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one center by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CenterDTO> findOne(Long id) {
        log.debug("Request to get Center : {}", id);
        return centerRepository.findById(id).map(centerMapper::toDto);
    }

    /**
     * Delete the center by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Center : {}", id);
        centerRepository.deleteById(id);
    }
}
