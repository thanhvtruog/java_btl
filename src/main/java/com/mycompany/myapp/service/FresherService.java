package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Fresher;
import com.mycompany.myapp.repository.FresherRepository;
import com.mycompany.myapp.service.dto.FresherDTO;
import com.mycompany.myapp.service.mapper.FresherMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Fresher}.
 */
@Service
@Transactional
public class FresherService {

    private final Logger log = LoggerFactory.getLogger(FresherService.class);

    private final FresherRepository fresherRepository;

    private final FresherMapper fresherMapper;

    public FresherService(FresherRepository fresherRepository, FresherMapper fresherMapper) {
        this.fresherRepository = fresherRepository;
        this.fresherMapper = fresherMapper;
    }

    /**
     * Save a fresher.
     *
     * @param fresherDTO the entity to save.
     * @return the persisted entity.
     */
    public FresherDTO save(FresherDTO fresherDTO) {
        log.debug("Request to save Fresher : {}", fresherDTO);
        Fresher fresher = fresherMapper.toEntity(fresherDTO);
        fresher = fresherRepository.save(fresher);
        return fresherMapper.toDto(fresher);
    }

    /**
     * Update a fresher.
     *
     * @param fresherDTO the entity to save.
     * @return the persisted entity.
     */
    public FresherDTO update(FresherDTO fresherDTO) {
        log.debug("Request to update Fresher : {}", fresherDTO);
        Fresher fresher = fresherMapper.toEntity(fresherDTO);
        fresher = fresherRepository.save(fresher);
        return fresherMapper.toDto(fresher);
    }

    /**
     * Partially update a fresher.
     *
     * @param fresherDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FresherDTO> partialUpdate(FresherDTO fresherDTO) {
        log.debug("Request to partially update Fresher : {}", fresherDTO);

        return fresherRepository
            .findById(fresherDTO.getId())
            .map(existingFresher -> {
                fresherMapper.partialUpdate(existingFresher, fresherDTO);

                return existingFresher;
            })
            .map(fresherRepository::save)
            .map(fresherMapper::toDto);
    }

    /**
     * Get all the freshers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FresherDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Freshers");
        return fresherRepository.findAll(pageable).map(fresherMapper::toDto);
    }

    /**
     * Get all the freshers with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<FresherDTO> findAllWithEagerRelationships(Pageable pageable) {
        return fresherRepository.findAllWithEagerRelationships(pageable).map(fresherMapper::toDto);
    }

    /**
     * Get one fresher by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FresherDTO> findOne(Long id) {
        log.debug("Request to get Fresher : {}", id);
        return fresherRepository.findOneWithEagerRelationships(id).map(fresherMapper::toDto);
    }

    /**
     * Delete the fresher by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Fresher : {}", id);
        fresherRepository.deleteById(id);
    }
}
