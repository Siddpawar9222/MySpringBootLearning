package com.example.PaginationAndSorting.service;

import com.example.PaginationAndSorting.exception.CaughtException;
import com.example.PaginationAndSorting.model.Manufacturer;
import com.example.PaginationAndSorting.model.Response;
import com.example.PaginationAndSorting.repository.ManufacturerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;


    //Pagination
//    @Transactional
//    public Response searchSupplier(String location, String natureOfBusiness, String manufacturingProcesses, String pageNumber, String pageSize) {
//        log.info("searchSupplier service called ::: {} ", new Date());
//        log.info("location : {} , natureOfBusiness : {} , manufacturingProcesses : {} ", location, natureOfBusiness, manufacturingProcesses);
//        log.info("pageNumber : {} , pageSize : {}", pageNumber, pageSize);
//        Response response = new Response();
//
//        try {
//            int pageNumberInInt = Integer.parseInt(pageNumber);
//            int pageSizeInInt = Integer.parseInt(pageSize);
//
//            Pageable pageable = PageRequest.of(pageNumberInInt, pageSizeInInt);
//            Page<Manufacturer> manufacturerPage = manufacturerRepository.findByLocationAndNatureOfBusinessAndManufacturingProcesses(location, natureOfBusiness, manufacturingProcesses, pageable);
//
//            Map<Object, Object> mPagination = new HashMap<>();
//
//
//            mPagination.put("content", manufacturerPage.getContent());
//            mPagination.put("totalElements", manufacturerPage.getTotalElements());
//            mPagination.put("totalPages", manufacturerPage.getTotalPages());
//            mPagination.put("pageSize", manufacturerPage.getPageable().getPageSize());
//            mPagination.put("pageNumber", manufacturerPage.getPageable().getPageNumber());
//            mPagination.put("isFirstPage", manufacturerPage.isFirst());
//            mPagination.put("isLastPage", manufacturerPage.isLast());
//
//            response.setData(mPagination);
//            response.setMessage("Data fetched successfully");
//            response.setResultCode(200);
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//            log.error("Exception : {}", e.getMessage());
//            throw new NumberFormatException("Page Number or Page Size should be number");
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("Exception : {}", e.getMessage());
//            throw new CaughtException(e.getMessage());
//        }
//
//        log.info("searchSupplier service ended ::: {} ", new Date());
//
//        return response;
//    }


    //pagination with sorting
    @Transactional
    public Response searchSupplier(String location, String natureOfBusiness, String manufacturingProcesses, String pageNumber, String pageSize, String sortBy, String sortOrder) {
        log.info("searchSupplier service called ::: {} ", new Date());
        log.info("location : {} , natureOfBusiness : {} , manufacturingProcesses : {} ", location, natureOfBusiness, manufacturingProcesses);
        log.info("pageNumber : {} , pageSize : {}, sortBy: {} ", pageNumber, pageSize, sortBy);
        Response response = new Response();

        try {
            int pageNumberInInt = Integer.parseInt(pageNumber);
            int pageSizeInInt = Integer.parseInt(pageSize);

//            Pageable pageable = PageRequest.of(pageNumberInInt, pageSizeInInt, Sort.by(sortBy));

            Sort.Direction direction = ("asc".equalsIgnoreCase(sortOrder)) ? Sort.Direction.ASC : Sort.Direction.DESC;

            Pageable pageable = PageRequest.of(pageNumberInInt, pageSizeInInt, Sort.by(direction, sortBy));

            Page<Manufacturer> manufacturerPage = manufacturerRepository.findByLocationAndNatureOfBusinessAndManufacturingProcesses(location, natureOfBusiness, manufacturingProcesses, pageable);

            Map<Object, Object> mPagination = new HashMap<>();


            mPagination.put("content", manufacturerPage.getContent());
            mPagination.put("totalElements", manufacturerPage.getTotalElements());
            mPagination.put("totalPages", manufacturerPage.getTotalPages());
            mPagination.put("pageSize", manufacturerPage.getPageable().getPageSize());
            mPagination.put("pageNumber", manufacturerPage.getPageable().getPageNumber());
            mPagination.put("isFirstPage", manufacturerPage.isFirst());
            mPagination.put("isLastPage", manufacturerPage.isLast());

            response.setData(mPagination);
            response.setMessage("Data fetched successfully");
            response.setResultCode(200);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            log.error("Exception : {}", e.getMessage());
            throw new NumberFormatException("Page Number or Page Size should be number");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Exception : {}", e.getMessage());
            throw new CaughtException(e.getMessage());
        }

        log.info("searchSupplier service ended ::: {} ", new Date());

        return response;
    }

    public Response searchSupplierByCompanyName(String companyName) {
        log.info("searchSupplierByCompanyName service called ::: {} ", new Date());
        log.info("companyName : {} ", companyName);
        Response response = new Response();
        List<Manufacturer> manufacturerList = manufacturerRepository.findByCompanyNameContainingIgnoreCase(companyName);

        response.setData(manufacturerList);
        response.setMessage("Data fetched successfully");
        response.setResultCode(200);
        log.info("searchSupplierByCompanyName service ended ::: {} ", new Date());
        return response;
    }


}

/*
  Pageable pageable = PageRequest.of(pageNumberInInt, pageSizeInInt, Sort.by(sortBy));

  Sort.by() :
  Takes the name of the field to sort by and the sort direction(overloaded method).
 */