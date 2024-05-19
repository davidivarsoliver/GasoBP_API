package gasobp.service;

import gasobp.model.Image;
import org.springframework.core.io.InputStreamResource;

import java.util.List;

public interface ImageService {

    List<Image> obtenerTodasLasImagenes();

    InputStreamResource findById(int id) throws Exception;

    List<Image> findByGenero(String genero);

    void cambiarStock(int id);

    List<Image> obtenerProductosFueraDeStock();

    List<Image> findAllFromProduct(int productId);


}
