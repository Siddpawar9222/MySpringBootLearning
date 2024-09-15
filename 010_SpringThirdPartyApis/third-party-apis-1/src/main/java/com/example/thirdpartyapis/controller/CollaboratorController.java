package com.example.thirdpartyapis.controller;

import com.example.thirdpartyapis.model.Response;
import com.example.thirdpartyapis.service.CollaboratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@CrossOrigin("*")
@RestController
@RequestMapping("/collaborator")
@Slf4j
@RequiredArgsConstructor
public class CollaboratorController {

    private final CollaboratorService collaboratorService;


    @GetMapping("/getAllCollaboratorsRawData")
    public ResponseEntity<Response> getAllCollaboratorsRawData() {
        log.info("getAllCollaboratorsRawData controller has called ::: {} ", new Date());
        Response response = this.collaboratorService.getAllCollaboratorsRawData();
        log.info("getAllCollaboratorsRawData controller has ended ::: {} ", new Date());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAllCollaboratorsFilteredInfo")
    public ResponseEntity<Response> getAllCollaboratorsInfo() {
        log.info("getAllCollaboratorsFilteredInfo controller has called ::: {} ", new Date());
        Response response = this.collaboratorService.getAllCollaboratorsFilteredInfo();
        log.info("getAllCollaboratorsFilteredInfo controller has ended ::: {} ", new Date());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
