package gasobp.controller;

import gasobp.model.Image;
import gasobp.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping
    public List<Image> obtenerTodasLasImagenes() {
        return imageService.obtenerTodasLasImagenes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InputStreamResource> obtenerImagen(@PathVariable("id") Integer id) {
        try {
            InputStreamResource inputStreamResource = imageService.findById(id);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(inputStreamResource, headers, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<Image>> obtenerProductosPorGenero(@PathVariable("genero") String genero) {
        try {
            List<Image> difGeneros = imageService.findByGenero(genero);
            return new ResponseEntity<>(difGeneros, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/cambiarStock")
    public ResponseEntity<?> cambiarStock(@PathVariable int id) {
        try {
            imageService.cambiarStock(id);
            return ResponseEntity.ok("Stock cambiado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al cambiar el stock");
        }
    }

    @GetMapping("/nostock")
    public ResponseEntity<List<Image>> obtenerProductosFueraDeStock() {
        try {
            List<Image> productosFueraDeStock = imageService.obtenerProductosFueraDeStock();
            return new ResponseEntity<>(productosFueraDeStock, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
