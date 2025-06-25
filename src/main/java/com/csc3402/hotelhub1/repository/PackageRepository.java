package com.csc3402.hotelhub1.repository;

import com.csc3402.hotelhub1.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package, Long> {
}
