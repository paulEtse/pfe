package org.chatbot.app.web.rest;
import org.chatbot.app.domain.UserInfo;
import org.chatbot.app.repository.UserInfoRepository;
import org.chatbot.app.service.UserService;
import org.chatbot.app.web.rest.errors.BadRequestAlertException;
import org.chatbot.app.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing UserInfo.
 */
@RestController
@RequestMapping("/api")
public class UserInfoResource {

    private final Logger log = LoggerFactory.getLogger(UserInfoResource.class);

    private static final String ENTITY_NAME = "userInfo";

    private final UserInfoRepository userInfoRepository;
    private final UserService userService;

    public UserInfoResource(UserInfoRepository userInfoRepository,UserService userService) {
        this.userInfoRepository = userInfoRepository;
        this.userService=userService;
    }

    /**
     * POST  /user-infos : Create a new userInfo.
     *
     * @param userInfo the userInfo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userInfo, or with status 400 (Bad Request) if the userInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-infos")
    public ResponseEntity<UserInfo> createUserInfo(@RequestBody UserInfo userInfo) throws URISyntaxException {
        log.debug("REST request to save UserInfo : {}", userInfo);
        if (userInfo.getId() != null) {
            throw new BadRequestAlertException("A new userInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserInfo result = userInfoRepository.save(userInfo);
        return ResponseEntity.created(new URI("/api/user-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-infos : Updates an existing userInfo.
     *
     * @param userInfo the userInfo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userInfo,
     * or with status 400 (Bad Request) if the userInfo is not valid,
     * or with status 500 (Internal Server Error) if the userInfo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-infos")
    public ResponseEntity<UserInfo> updateUserInfo(@RequestBody UserInfo userInfo) throws URISyntaxException {
        log.debug("REST request to update UserInfo : {}", userInfo);
        if (userInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserInfo result = userInfoRepository.save(userInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userInfo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-infos : get all the userInfos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of userInfos in body
     */
    @GetMapping("/user-infos")
    public List<UserInfo> getAllUserInfos() {
        log.debug("REST request to get all UserInfos");
        Long id=userService.getUserWithAuthorities().get().getId();
        return userInfoRepository.findByUserId(id);
    }

    /**
     * GET  /user-infos/:id : get the "id" userInfo.
     *
     * @param id the id of the userInfo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userInfo, or with status 404 (Not Found)
     */
    @GetMapping("/user-infos/{id}")
    public ResponseEntity<UserInfo> getUserInfo(@PathVariable Long id) {
        log.debug("REST request to get UserInfo : {}", id);
        Optional<UserInfo> userInfo = userInfoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(userInfo);
    }

    /**
     * DELETE  /user-infos/:id : delete the "id" userInfo.
     *
     * @param id the id of the userInfo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-infos/{id}")
    public ResponseEntity<Void> deleteUserInfo(@PathVariable Long id) {
        log.debug("REST request to delete UserInfo : {}", id);
        userInfoRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
