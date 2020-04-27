package edu.sjsu.cmpe275.cartpool.service;

import edu.sjsu.cmpe275.cartpool.exceptions.MembershipException;
import edu.sjsu.cmpe275.cartpool.exceptions.PoolNotFoundException;
import edu.sjsu.cmpe275.cartpool.exceptions.UserNotFoundException;
import edu.sjsu.cmpe275.cartpool.pojos.Pool;
import edu.sjsu.cmpe275.cartpool.pojos.Pooler;
import edu.sjsu.cmpe275.cartpool.repository.PoolRepository;
import edu.sjsu.cmpe275.cartpool.repository.PoolerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PoolServiceImpl implements PoolService {

    @Autowired
    private PoolRepository<Pool> poolRepository;

    @Autowired
    private PoolerRepository<Pooler> poolerRepository;

    @Transactional
    @Override
    public Pool save(Pool pool) {
        return poolRepository.save(pool);
    }

    @Transactional
    @Override
    public void delete(Long poolId) {
        Pool pool = poolRepository.findById(poolId).orElseThrow(() -> new PoolNotFoundException());
        Pooler poolLeader = pool.getPoolLeader();
        if (pool.getMembers().size() == 1 && pool.getMembers().get(0) == poolLeader) {
            poolLeader.setPool(null);
            poolRepository.delete(pool);
        } else
            throw new MembershipException("Unable to delete pool!! :: Members exist in pool");
    }

    @Transactional
    @Override
    public boolean chceckMembership(Long poolerId) {
        Pooler pooler = poolerRepository.findById(poolerId).orElseThrow(() -> new UserNotFoundException());
        return pooler.getPool() == null;
    }

    @Transactional
    @Override
    public List<Pool> searchPool(String searchParam) {
        List<Pool> poolList = poolRepository.findByNameOrNeighborhoodNameOrZipAllIgnoreCase(searchParam, searchParam, searchParam);
        return poolList;
    }

    @Transactional
    @Override
    public Pool joinPool(Long poolId, Long poolerId, String screenName) {

        Pooler pooler = poolerRepository.findById(poolerId).orElseThrow(() -> new UserNotFoundException());
        Pool pool = poolRepository.findById(poolId).orElseThrow(() -> new PoolNotFoundException());

        for(Pooler member: pool.getMembers()){
            if(member.getScreenname() == screenName){

                /////// send email to the reference pooler /////////
            }
        }

        ///// throw exception -> no pooler with given ScreenName found
        return null;
    }
}
