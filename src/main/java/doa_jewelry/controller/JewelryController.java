package doa_jewelry.controller;

import doa_jewelry.dto.JewelryDTO;
import doa_jewelry.entity.Jewelry;
import doa_jewelry.exception.EntityAlreadyExistsException;
import doa_jewelry.exception.EntityNotFoundException;
import doa_jewelry.service.JewelryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/jewelry")
public class JewelryController {

    @Autowired
    private final JewelryService jewelryService;

    public JewelryController(JewelryService jewelryService) {
        this.jewelryService = jewelryService;
    }

    // Criar um novo item de joia
    @PostMapping
    public ResponseEntity<JewelryDTO> criarJewelry(@RequestBody JewelryDTO jewelryDTO) {
        Jewelry jewelry = new Jewelry();
        BeanUtils.copyProperties(jewelryDTO, jewelry);

        try {
            Jewelry savedJewelry = jewelryService.saveJewelry(jewelry);
            JewelryDTO responseDTO = new JewelryDTO(savedJewelry);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (EntityAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }

    // Obter joia por ID
    @GetMapping("/{id}")
    public ResponseEntity<JewelryDTO> obterJewelryPorId(@PathVariable Long id) {
        try {
            Jewelry jewelry = jewelryService.getJewelryById(id);
            JewelryDTO responseDTO = new JewelryDTO(jewelry);
            return ResponseEntity.ok(responseDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Listar todas as joias
    @GetMapping
    public ResponseEntity<List<JewelryDTO>> listarJewelry() {
        List<Jewelry> jewelryList = jewelryService.getAllJewelry();
        List<JewelryDTO> responseDTOs = jewelryList.stream()
                .map(JewelryDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    // Atualizar um item de joia existente
    @PutMapping("/{id}")
    public ResponseEntity<JewelryDTO> atualizarJewelry(@PathVariable Long id, @RequestBody JewelryDTO jewelryDTO) {
        Jewelry jewelry = new Jewelry();
        BeanUtils.copyProperties(jewelryDTO, jewelry);

        try {
            Jewelry updatedJewelry = jewelryService.updateJewelry(jewelry);
            JewelryDTO responseDTO = new JewelryDTO(updatedJewelry);
            return ResponseEntity.ok(responseDTO);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Deletar joia por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarJewelryPorId(@PathVariable Long id) {
        try {
            jewelryService.deleteJewelry(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
