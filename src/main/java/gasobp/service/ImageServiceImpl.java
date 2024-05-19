package gasobp.service;

import gasobp.model.Image;
import gasobp.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {

    private static final String BASE_URL = "./src/main/resources/images/";

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public InputStreamResource findById(int id) throws Exception {
        Optional<Image> image = imageRepository.findById(id);
        if (image.isEmpty())
            throw new Exception("Imagen no encontrada");

        File file = new File(BASE_URL + image.get().getName());
        InputStream inputStream = new FileInputStream(file);
        return new InputStreamResource(inputStream);
    }

    @Override
    public List<Image> findAllFromProduct(int productId) {
        return null;
    }


    public List<Image> obtenerTodasLasImagenes() {
        return imageRepository.findAll();
    }

    @Override
    public List<Image> findByGenero(String genero) {
        return imageRepository.findByGenero(genero);
    }

    @Override
    public void cambiarStock(int id) {
        Optional<Image> optionalImage = imageRepository.findById(id);
        optionalImage.ifPresent(image -> {
            image.setStock(true);
            imageRepository.save(image);
        });
    }

    @Override
    public List<Image> obtenerProductosFueraDeStock() {
        return imageRepository.findByStock(false);
    }


}
