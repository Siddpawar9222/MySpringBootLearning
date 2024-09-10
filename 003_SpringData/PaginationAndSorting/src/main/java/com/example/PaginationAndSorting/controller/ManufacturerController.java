package com.example.PaginationAndSorting.controller;

import com.example.PaginationAndSorting.model.Response;
import com.example.PaginationAndSorting.service.ManufacturerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/supplier/")
@RequiredArgsConstructor
@Slf4j
public class ManufacturerController {

    private final ManufacturerService manufacturerService;


    // pagination
//    @GetMapping("/searchSupplier")
//    public ResponseEntity<Response> getSupplier(
//            @RequestParam(value = "location", required = true) String location,
//            @RequestParam(value = "nature_of_business" ,required = true) String natureOfBusiness,
//            @RequestParam(value = "manufacturing_processes",required = true) String manufacturingProcesses,
//            @RequestParam(value = "pageNumber", defaultValue = "0" ,required = false) String pageNumber,
//            @RequestParam(value = "pageSize", defaultValue = "5" ,required = false) String pageSize
//    ) {
//        log.info("getSupplier controller called ::: {} ", new Date());
//        Response response = new Response();
//        if(location.isEmpty() || natureOfBusiness.isEmpty() || manufacturingProcesses.isEmpty()) {
//            response.setResultCode(400);
//            response.setMessage("Invalid Request. Location, nature of business or manufacturing processes cannot be empty");
//        }else{
//             response = manufacturerService.searchSupplier(location, natureOfBusiness, manufacturingProcesses, pageNumber, pageSize);
//        }
//
//        log.info("getSupplier controller ended ::: {} ", new Date());
//        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getResultCode()));
//    }

    // Pagination with sorting
    @GetMapping("/searchSupplier")
    public ResponseEntity<Response> getSupplier(
            @RequestParam(value = "location", required = true) String location,
            @RequestParam(value = "nature_of_business" ,required = true) String natureOfBusiness,
            @RequestParam(value = "manufacturing_processes",required = true) String manufacturingProcesses,
            @RequestParam(value = "pageNumber", defaultValue = "0" ,required = false) String pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5" ,required = false) String pageSize,
            @RequestParam(value = "sortBy", defaultValue = "companyName" ,required = false) String sortBy,
            @RequestParam(value = "sortOrder", defaultValue = "asc" ,required = false) String sortOrder
    ) {
        log.info("getSupplier controller called ::: {} ", new Date());
        Response response = new Response();
        if(location.isEmpty() || natureOfBusiness.isEmpty() || manufacturingProcesses.isEmpty()) {
            response.setResultCode(400);
            response.setMessage("Invalid Request. Location, nature of business or manufacturing processes cannot be empty");
        }else{
            response = manufacturerService.searchSupplier(location, natureOfBusiness, manufacturingProcesses, pageNumber, pageSize, sortBy,sortOrder);
        }

        log.info("getSupplier controller ended ::: {} ", new Date());
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getResultCode()));
    }

    @GetMapping("/search")
    public Response searchManufacturers(@RequestParam("companyName") String companyName) {
        return manufacturerService.searchSupplierByCompanyName(companyName);
    }

}
