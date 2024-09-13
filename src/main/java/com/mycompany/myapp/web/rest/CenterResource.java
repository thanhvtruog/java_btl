package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.CenterRepository;
import com.mycompany.myapp.service.CenterService;
import com.mycompany.myapp.service.dto.CenterDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Center}.
 */
@RestController
@RequestMapping("/api")
public class CenterResource {

    private final Logger log = LoggerFactory.getLogger(CenterResource.class);

    private static final String ENTITY_NAME = "center";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CenterService centerService;

    private final CenterRepository centerRepository;

    public CenterResource(CenterService centerService, CenterRepository centerRepository) {
        this.centerService = centerService;
        this.centerRepository = centerRepository;
    }

    /**
     * {@code POST  /centers} : Create a new center.
     *
     * @param centerDTO the centerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new centerDTO, or with status {@code 400 (Bad Request)} if the center has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/centers")
    public ResponseEntity<CenterDTO> createCenter(@Valid @RequestBody CenterDTO centerDTO) throws URISyntaxException {
        log.debug("REST request to save Center : {}", centerDTO);
        if (centerDTO.getId() != null) {
            throw new BadRequestAlertException("A new center cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CenterDTO result = centerService.save(centerDTO);
        return ResponseEntity
            .created(new URI("/api/centers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /centers/:id} : Updates an existing center.
     *
     * @param id the id of the centerDTO to save.
     * @param centerDTO the centerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated centerDTO,
     * or with status {@code 400 (Bad Request)} if the centerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the centerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/centers/{id}")
    public ResponseEntity<CenterDTO> updateCenter(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CenterDTO centerDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Center : {}, {}", id, centerDTO);
        if (centerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, centerDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!centerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CenterDTO result = centerService.update(centerDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, centerDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /centers/:id} : Partial updates given fields of an existing center, field will ignore if it is null
     *
     * @param id the id of the centerDTO to save.
     * @param centerDTO the centerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated centerDTO,
     * or with status {@code 400 (Bad Request)} if the centerDTO is not valid,
     * or with status {@code 404 (Not Found)} if the centerDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the centerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/centers/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CenterDTO> partialUpdateCenter(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CenterDTO centerDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Center partially : {}, {}", id, centerDTO);
        if (centerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, centerDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!centerRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CenterDTO> result = centerService.partialUpdate(centerDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, centerDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /centers} : get all the centers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of centers in body.
     */
    @GetMapping("/centers")
    public List<CenterDTO> getAllCenters() {
        log.debug("REST request to get all Centers");
        return centerService.findAll();
    }

    /**
     * {@code GET  /centers/:id} : get the "id" center.
     *
     * @param id the id of the centerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the centerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/centers/{id}")
    public ResponseEntity<CenterDTO> getCenter(@PathVariable Long id) {
        log.debug("REST request to get Center : {}", id);
        Optional<CenterDTO> centerDTO = centerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(centerDTO);
    }

    /**
     * {@code DELETE  /centers/:id} : delete the "id" center.
     *
     * @param id the id of the centerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/centers/{id}")
    public ResponseEntity<Void> deleteCenter(@PathVariable Long id) {
        log.debug("REST request to delete Center : {}", id);
        centerService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
