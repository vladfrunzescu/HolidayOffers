
package socialnetwork.service;

import socialnetwork.domain.Doi;
import socialnetwork.domain.DoiDTO;
import socialnetwork.domain.Unu;
import socialnetwork.repository.Repository;
import socialnetwork.utils.observer.Observable;
import socialnetwork.utils.observer.Observer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ServiceSchelet implements Observable {
    private Repository<Long, Unu> unuRepo;
    private Repository<Long, Doi> doiRepo;

    public ServiceSchelet(Repository<Long, Unu> unuRepo, Repository<Long, Doi> doiRepo) {
        this.unuRepo = unuRepo;
        this.doiRepo = doiRepo;
    }

    @Override
    public void addObserver(Observer e){
        observers.add(e);
    }
    @Override
    public void removeObserver(Observer e){
        observers.remove(e);
    }
    @Override
    public void notifyObservers(){
        observers.stream().forEach(x->x.update());
    }

    //exp: hotels by location
    //     doi       unu
    public List<Doi> doiByUnu(Long ID){
        List<Doi> list = new ArrayList<>();
        for(Doi e : this.getAllDoi()){
            if(e.getUnu_id().equals(ID))
                list.add(e);
        }
        return list;
    }

    public List<Unu> getAllUnu(){
        List<Unu> list = new ArrayList<Unu>();
        unuRepo.findAll().forEach(list::add);
        return list;
    }

    public List<Doi> getAllDoi(){
        List<Doi> list = new ArrayList<Doi>();
        doiRepo.findAll().forEach(list::add);
        return list;
    }

    //exp: hotels by location
    //     doi       unu
    public List<Doi> doiByUnuDates(Long ID, LocalDateTime start, LocalDateTime end){
        List<Doi> list = new ArrayList<>();
        for(Doi e : this.getAllDoi()){
            if(e.getUnu_id().equals(ID) && e.getFirst_date().isAfter(start) && e.getSecond_date().isBefore(end))
                list.add(e);
        }
        return list;
    }

    public List<DoiDTO> personalizate(Unu entity){
        List<DoiDTO> list = new ArrayList<>();
        for(Doi e : this.getAllDoi()){
            if(true){//e.getFirst_date().isAfter(LocalDateTime.now()) && e.getUnu_id().equals(entity.getId())){
                Unu entitate = unuRepo.findOne(e.getUnu_id());
                DoiDTO dto = new DoiDTO(e.getFirst_myenum(), e.getSecond_myenum(), e.getId(), entitate, e.getFirst_string(), e.getSecond_string(), e.getFirst_Integer(), e.getSecond_Integer(), e.getFirst_integer(), e.getSecond_integer(), e.getFirst_Long(), e.getSecond_Long(), e.getFirst_long(), e.getSecond_long(), e.getFirst_Double(), e.getSecond_Double(), e.getFirst_double(), e.getSecond_double(), e.getFirst_date(), e.getSecond_date());
                list.add(dto);
            }
        }
        return list;
    }

}

