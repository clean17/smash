//package com.example.springbreaking.accessingDataJpa;
//
//
//import org.springframework.data.repository.PagingAndSortingRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//
//import java.util.List;
//
///**
// * Spring Data JPA에서 제공하는 인터페이스 - PagingAndSortingRepository
// * PagingAndSortingRepository 는 CURDRepository를 상속함
// *
// * < JPA 페이징 구현 방법 >
// *
// * JPA의 Query와 TypedQuery 사용:
// * TypedQuery<MyEntity> query = entityManager.createQuery("SELECT e FROM MyEntity e", MyEntity.class);
// * query.setFirstResult((pageNumber - 1) * pageSize);
// * query.setMaxResults(pageSize);
// * List<MyEntity> results = query.getResultList();
// *
// * JdbcTemplate 사용:
// * String sql = "SELECT * FROM my_table LIMIT ? OFFSET ?";
// * List<MyEntity> results = jdbcTemplate.query(sql, new Object[]{pageSize, (pageNumber - 1) * pageSize}, new MyEntityRowMapper());
// *
// * Native SQL 사용:
// * Query query = entityManager.createNativeQuery("SELECT * FROM my_table LIMIT ? OFFSET ?", MyEntity.class);
// * query.setParameter(1, pageSize);
// * query.setParameter(2, (pageNumber - 1) * pageSize);
// * List<MyEntity> results = query.getResultList();
// *
// * WEB에서 ?page=1&size=20&sort=name,asc&sort=age,desc 같은 요청을 서비스 로직에서 처리
// * 이때 Page<>객체를 반환하는데 구현체인 PageImpl을 반환한다.
// * Page<MyEntity> page = new PageImpl<>(content, pageable, total);
// *
// *
// * ----------------------------------------
// *
// * @RepositoryRestResource  Spring Data REST 에서 제공
// * 해당 Repository 인터페이스를 RESTful 웹 서비스로 노출
// * collectionResourceRel - HAL 형식의 응답에 사용, _embedded 섹션 내에서 이 이름으로 리소스의 컬렉션을 참조
// * path - CRUD 응답 경로
// */
//@RepositoryRestResource(collectionResourceRel = "people", path = "people")
//public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {
//
//  List<Person> findByLastName(@Param("name") String name);
//}