package gasobp.repository;


import gasobp.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image,Integer> {

    List<Image> findAll();
    Optional<Image> findById(int id);

    List<Image> findByGenero(String genero);

    List<Image> findByStock(boolean stock);


}
