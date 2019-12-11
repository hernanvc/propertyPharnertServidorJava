package com.wingsoft.propertyp.dao;

import com.wingsoft.propertyp.model.Comuna;
import com.wingsoft.propertyp.model.Propiedad;


import com.wingsoft.propertyp.model.Region;
import com.wingsoft.propertyp.model.Vendedor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;


public interface PropiedadDao extends CrudRepository<Propiedad, Long> {

    <S extends Propiedad> Iterable<S>   saveAll(Iterable<S> entities);


    @Query(value = "SELECT max(valor) from Propiedad")
    double getMaxValor();

    @Query("SELECT p.comuna FROM Propiedad p")
    List<String> findComuna();


    Iterable<Propiedad> findAll(Pageable pageable);

    @Query("SELECT p.direccion FROM Propiedad p")
    List<String> findSector();

    @Query(value = "SELECT p from Propiedad p  , Comuna c where (p.description  like %:description%) And (p.objetivo = :objetivo) And (p.valor >= :min And p.valor <= :max) And (c.id = :region) And (p.comuna = c.id) And (p.tipo = :tipo) and p.disponible =1")
    List<Propiedad> findFilter(@Param("description") String description,@Param("objetivo") String objetivo,@Param("min") Double min,@Param("max") Double max,@Param("region") long  region,@Param("tipo") String tipo,Pageable pageable);

    @Query(value = "SELECT COUNT(p) from Propiedad p  , Comuna c where (p.description  like %:description%) And (p.objetivo = :objetivo) And (p.valor >= :min And p.valor <= :max) And (c.id = :region) And (p.comuna = c.id) And (p.tipo = :tipo) and p.disponible =1")
    Long countFilter(@Param("description") String description,@Param("objetivo") String objetivo,@Param("min") Double min,@Param("max") Double max,@Param("region") long  region,@Param("tipo") String tipo);


    @Query(value = "SELECT p from Propiedad p  , Comuna c where (p.description  like %:description%) And (p.objetivo = :objetivo) And (p.valor >= :min And p.valor <= :max) And (c.region = :region) And (p.comuna = c.id) And (p.tipo = :tipo) and p.disponible =1")
    List<Propiedad> findFilterOnlyRegion(@Param("description") String description,@Param("objetivo") String objetivo,@Param("min") Double min,@Param("max") Double max,@Param("region") Region region,@Param("tipo") String tipo,Pageable pageable);

    @Query(value = "SELECT COUNT(p) from Propiedad p  , Comuna c where (p.description  like %:description%) And (p.objetivo = :objetivo) And (p.valor >= :min And p.valor <= :max) And (c.region = :region) And (p.comuna = c.id) And (p.tipo = :tipo) and p.disponible =1")
    Long  countFilterOnlyRegion(@Param("description") String description,@Param("objetivo") String objetivo,@Param("min") Double min,@Param("max") Double max,@Param("region") Region region,@Param("tipo") String tipo);



    @Query("SELECT p FROM Propiedad p , Comuna c where ((p.direccion = :busqueda) or (c.nombre = :busqueda and c.id = p.comuna)) and p.disponible =1")
    List<Propiedad>findByComunaAndSector(@Param("busqueda") String busqueda,Pageable pageable);

    List<Propiedad> findByDireccionAndObjetivoAndDisponible(String direcion,String Objectivo,Pageable pageable,int disponible);
    List<Propiedad> findByComunaAndObjetivoAndDisponible(Comuna comuna,String Objectivo,Pageable pageable,int disponible);


    Long countByDireccionAndObjetivoAndDisponible(String direcion,String Objectivo,int disponible);
    Long countByComunaAndObjetivoAndDisponible(Comuna comuna,String Objectivo,int disponible);


    List<Propiedad> findByVendedorsAndObjetivoAndDisponible(List<Vendedor> vendedors,String Objectivo,Pageable pageable,int disponible);

    List<Propiedad> findByEtiquetaContainingAndObjetivoAndDisponible(String tipo ,String Objectivo,Pageable pageable,int disponible);


    Long countByEtiquetaContainingAndObjetivoAndDisponible(String tipo ,String Objectivo,int disponible);

    @Query("SELECT DISTINCT p.tipo FROM Propiedad p")
    List<String> findTipo();

    /*
        @Query("SELECT AVG(u.age) from User u")
        int getAverageAge();

        @Query(value = "SELECT max(age) from User where first_name <> ?1", nativeQuery = true)
        int getMaxAgeMinus(String name);

        @Query("select p from Person p where p.country like ?1 order by country")
        List<Person> findByCountryContains(String country);

    */
}