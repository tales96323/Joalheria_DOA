package doa_jewelry.service;

import doa_jewelry.entity.Jewelry;
import doa_jewelry.exception.EntityAlreadyExistsException;
import doa_jewelry.exception.EntityNotFoundException;
import doa_jewelry.repository.JewelryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JewelryService {

    @Autowired
    private JewelryRepository jewelryRepository;

    public Jewelry saveJewelry(Jewelry jewelry) throws EntityAlreadyExistsException {
        if (jewelry.getId() != null && jewelryRepository.existsById(jewelry.getId())) {
            throw new EntityAlreadyExistsException(Jewelry.class);
        }
        if (jewelryRepository.findByName(jewelry.getName()).isPresent()) {
            throw new EntityAlreadyExistsException(Jewelry.class);
        }
        return jewelryRepository.save(jewelry);
    }

    public Optional<Jewelry> findJewelryById(Long jewelryId) {
        return jewelryRepository.findById(jewelryId);
    }

    public Jewelry updateJewelry(Jewelry jewelry) throws EntityNotFoundException {
        if (!jewelryRepository.existsById(jewelry.getId())) {
            throw new EntityNotFoundException(Jewelry.class);
        }
        return jewelryRepository.save(jewelry);
    }

    public List<Jewelry> getAllJewelry() {
        return jewelryRepository.findAll();
    }

    public Jewelry getJewelryById(Long id) {
        return jewelryRepository.findById(id).orElse(null);
    }

    public void deleteJewelry(Long id) {
        jewelryRepository.deleteById(id);
    }
}
