package cn.cote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PeopleService {

    @Autowired
    private PeoPleRepository peoPleRepository;

//   事务回滚，需要修改数据库引擎  ALTER TABLE tableName ENGINE=innodb
    @Transactional
    public void insertTwo(){

        people this_people1=new people();
        this_people1.setIq("A");
        this_people1.setEq(1);
        peoPleRepository.save(this_people1);

        people this_people2=new people();
        this_people2.setIq("B");
        this_people2.setEq(9);
        peoPleRepository.save(this_people2);

    }
}
