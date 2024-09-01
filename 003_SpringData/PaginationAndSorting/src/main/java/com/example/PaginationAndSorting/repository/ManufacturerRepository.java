package com.example.PaginationAndSorting.repository;
import com.example.PaginationAndSorting.model.Manufacturer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {
     //List<Manufacturer> findByLocation(String location);
     //Page<Manufacturer> findByLocation(String location, Pageable pageable);
     Page<Manufacturer> findByLocationAndNatureOfBusinessAndManufacturingProcesses
     (String location,String natureOfBusiness,String manufacturingProcesses, Pageable pageable);


   List<Manufacturer> findByCompanyNameContainingIgnoreCase(String companyName);
}