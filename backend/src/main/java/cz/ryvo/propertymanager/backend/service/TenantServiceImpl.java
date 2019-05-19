package cz.ryvo.propertymanager.backend.service;

import com.querydsl.core.support.QueryBase;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import cz.ryvo.propertymanager.backend.api.SearchTenantsCriteria;
import cz.ryvo.propertymanager.backend.domain.*;
import cz.ryvo.propertymanager.backend.exception.NotFoundException;
import cz.ryvo.propertymanager.backend.repository.TenantRepository;
import cz.ryvo.propertymanager.backend.util.AuthUtils;
import cz.ryvo.propertymanager.backend.util.TenantUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

import static cz.ryvo.propertymanager.backend.domain.PersonType.LEGAL_PERSON;
import static cz.ryvo.propertymanager.backend.domain.PersonType.NATURAL_PERSON;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
class TenantServiceImpl implements TenantService {

  private final PortfolioService portfolioService;
  private final TenantRepository repository;
  private final EntityManager entityManager;

  @Autowired
  public TenantServiceImpl(
      PortfolioService portfolioService,
      TenantRepository repository,
      EntityManager entityManager) {
    this.portfolioService = portfolioService;
    this.repository = repository;
    this.entityManager = entityManager;
  }

  @Override
  public List<Tenant> searchTenants(SearchTenantsCriteria criteria) {
    return createQuery(criteria).fetch();
  }

  @Override
  public Tenant createTenant(Tenant tenant) {
    Portfolio portfolio = portfolioService.getPortfolio();
    tenant.setPortfolio(portfolio);
    return repository.save(tenant);
  }

  @Override
  public Tenant getTenant(long id) {
    return repository.findById(id)
        .orElseThrow(() -> new NotFoundException("Tenant not found"));
  }

  @Override
  public Tenant updateTenant(long id, Tenant tenant) {
    return repository.findById(id).map(persisted -> {
      TenantUtils.merge(tenant, persisted);
      return repository.save(persisted);
    }).orElseThrow(() -> new NotFoundException("Tenant not found"));
  }

  private JPAQuery<Tenant> createQuery(SearchTenantsCriteria criteria) {
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    QTenant tenant = QTenant.tenant;

    BooleanExpression predicate = tenant.portfolio.id.eq(AuthUtils.getPortfolioId());
    if (!isBlank(criteria.getName())) {
      BooleanExpression namePredicate =
          tenant.type.eq(NATURAL_PERSON).and(tenant.lastName.contains(criteria.getName()).or(tenant.firstName.contains(criteria.getName())))
          .or(tenant.type.eq(LEGAL_PERSON).and(tenant.companyName.contains(criteria.getName()));
      predicate = predicate.and(namePredicate);
    }

    JPAQuery<Tenant> query = queryFactory.selectFrom(tenant);
    if (criteria.getBuildingId() != null) {
      QLease lease = QLease.lease;
      query.innerJoin(lease.tenant, tenant).on(lease.buildingUnit.id.eq(criteria.getBuildingId()));
    }

    return query.where(predicate).orderBy(tenant.companyName.asc(), tenant.lastName.asc(), tenant.firstName.asc()).limit(10);
  }
}