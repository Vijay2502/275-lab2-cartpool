package edu.sjsu.cmpe275.cartpool.service;

import edu.sjsu.cmpe275.cartpool.exceptions.UserNotFoundException;
import edu.sjsu.cmpe275.cartpool.pojos.Pooler;
import edu.sjsu.cmpe275.cartpool.repository.PoolerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PoolerService {
    @Autowired
    PoolerRepository<Pooler> poolerRepository;

    @Transactional
    public Pooler findById(Long id) throws Exception {
        return poolerRepository.findById(id).orElseThrow(() -> new Exception(""));
    }

    @Transactional
    public Pooler login(String email, String password) {
        List<Pooler> result = poolerRepository.findByEmailAndPassword(email, password);
        if (result.size() >= 1) {
            return result.get(0);
        }

        throw new UserNotFoundException();
    }

    @Transactional
    public Pooler save(Pooler pooler) {
        return poolerRepository.save(pooler);
    }
}