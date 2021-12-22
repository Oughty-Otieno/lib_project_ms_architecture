package com.libproject.sc.web.rest;

import com.libproject.sc.domain.Borrowing;
import com.libproject.sc.repository.BorrowingRepository;
import com.libproject.sc.service.BorrowingService;
import com.libproject.sc.service.dto.BorrowingDTO;
import com.libproject.sc.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.libproject.sc.domain.Borrowing}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BorrowingResource {

    private final Logger log = LoggerFactory.getLogger(BorrowingResource.class);

    private static final String ENTITY_NAME = "libprojectBorrowing";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BorrowingRepository borrowingRepository;
    private final BorrowingService borrowingService;

    public BorrowingResource(BorrowingRepository borrowingRepository, BorrowingService borrowingService) {
        this.borrowingRepository = borrowingRepository;
        this.borrowingService = borrowingService;
    }

    /**
     * {@code POST  /borrowings} : Create a new borrowing.
     *
     * @param borrowing the borrowing to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new borrowing, or with status {@code 400 (Bad Request)} if the borrowing has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/borrowings")
    public ResponseEntity<Borrowing> createBorrowing(@RequestBody Borrowing borrowing) throws URISyntaxException {
        log.debug("REST request to save Borrowing : {}", borrowing);
        if (borrowing.getId() != null) {
            throw new BadRequestAlertException("A new borrowing cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Borrowing result = borrowingRepository.save(borrowing);
        return ResponseEntity
            .created(new URI("/api/borrowings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /borrowings/:id} : Updates an existing borrowing.
     *
     * @param id the id of the borrowing to save.
     * @param borrowing the borrowing to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated borrowing,
     * or with status {@code 400 (Bad Request)} if the borrowing is not valid,
     * or with status {@code 500 (Internal Server Error)} if the borrowing couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/borrowings/{id}")
    public ResponseEntity<Borrowing> updateBorrowing(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Borrowing borrowing
    ) throws URISyntaxException {
        log.debug("REST request to update Borrowing : {}, {}", id, borrowing);
        if (borrowing.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, borrowing.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!borrowingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Borrowing result = borrowingRepository.save(borrowing);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, borrowing.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /borrowings/:id} : Partial updates given fields of an existing borrowing, field will ignore if it is null
     *
     * @param id the id of the borrowing to save.
     * @param borrowing the borrowing to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated borrowing,
     * or with status {@code 400 (Bad Request)} if the borrowing is not valid,
     * or with status {@code 404 (Not Found)} if the borrowing is not found,
     * or with status {@code 500 (Internal Server Error)} if the borrowing couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/borrowings/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Borrowing> partialUpdateBorrowing(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Borrowing borrowing
    ) throws URISyntaxException {
        log.debug("REST request to partial update Borrowing partially : {}, {}", id, borrowing);
        if (borrowing.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, borrowing.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!borrowingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Borrowing> result = borrowingRepository
            .findById(borrowing.getId())
            .map(existingBorrowing -> {
                if (borrowing.getDate_borrowed() != null) {
                    existingBorrowing.setDate_borrowed(borrowing.getDate_borrowed());
                }
                if (borrowing.getDue_date() != null) {
                    existingBorrowing.setDue_date(borrowing.getDue_date());
                }
                if (borrowing.getReturn_date() != null) {
                    existingBorrowing.setReturn_date(borrowing.getReturn_date());
                }
                if (borrowing.getUser_id() != null) {
                    existingBorrowing.setUser_id(borrowing.getUser_id());
                }

                return existingBorrowing;
            })
            .map(borrowingRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, borrowing.getId().toString())
        );
    }

    /**
     * {@code GET  /borrowings} : get all the borrowings.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of borrowings in body.
     */
    @GetMapping("/borrowings")
    public ResponseEntity<List<BorrowingDTO>> getAllBorrowings(Pageable pageable) {
        log.debug("REST request to get a page of Borrowings");
        Page<BorrowingDTO> page = borrowingService.getAllBorrowings(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /borrowings/:id} : get the "id" borrowing.
     *
     * @param id the id of the borrowing to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the borrowing, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/borrowings/{id}")
    public ResponseEntity<Borrowing> getBorrowing(@PathVariable Long id) {
        log.debug("REST request to get Borrowing : {}", id);
        Optional<Borrowing> borrowing = Optional.ofNullable(borrowingService.getBorrowing(id));
        return ResponseUtil.wrapOrNotFound(borrowing);
    }

    /**
     * {@code DELETE  /borrowings/:id} : delete the "id" borrowing.
     *
     * @param id the id of the borrowing to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/borrowings/{id}")
    public ResponseEntity<Void> deleteBorrowing(@PathVariable Long id) {
        log.debug("REST request to delete Borrowing : {}", id);
        borrowingRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
