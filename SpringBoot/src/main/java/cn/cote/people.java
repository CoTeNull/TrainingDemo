package cn.cote;

import javax.persistence.*;

@Entity
@Table(name = "t_people") //映射的表名称
public class people {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//可以避免生成hibernate_sequence数据表
    private Integer id;

    private String iq;

    private Integer eq;

    public people() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIq() {
        return iq;
    }

    public void setIq(String iq) {
        this.iq = iq;
    }

    public Integer getEq() {
        return eq;
    }

    public void setEq(Integer eq) {
        this.eq = eq;
    }
}
