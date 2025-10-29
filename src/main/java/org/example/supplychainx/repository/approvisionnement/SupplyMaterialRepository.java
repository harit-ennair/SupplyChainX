package org.example.supplychainx.repository.approvisionnement;

import org.example.supplychainx.model.approvisionnement.SupplyMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplyMaterialRepository extends JpaRepository<SupplyMaterial, Long> {
}
