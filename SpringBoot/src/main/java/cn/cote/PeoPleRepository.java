package cn.cote;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeoPleRepository extends JpaRepository<people,Integer>{

    public List<people>  findByIq(String iq); //自定义根据iq查询的方法
}
