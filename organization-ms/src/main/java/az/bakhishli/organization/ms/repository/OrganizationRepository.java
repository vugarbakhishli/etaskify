package az.bakhishli.organization.ms.repository;

import az.bakhishli.organization.ms.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    Optional<Organization> findOrganizationByUserId(Long id);
}
