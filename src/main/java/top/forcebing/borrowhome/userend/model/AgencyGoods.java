package top.forcebing.borrowhome.userend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import top.forcebing.borrowhome.shopend.model.Goods;

import javax.persistence.Entity;
import java.util.Date;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/3  15:08
 **/
@Data
@Entity
@NoArgsConstructor
public class AgencyGoods extends Goods {

    // TODO 2019/3/3 15:12 liliangbin  定时任务，一直和商铺端同步  同时商铺端出现修改得时候我们这里也需要修改一下。
    private boolean shown; //未修改定价

    private boolean exited = Boolean.TRUE;
    private long agencyId;

    private long goodsId;
    private Date createTime;
    private Date changeTime;
    private Date doneTime;

    public AgencyGoods(Goods goods) {

        BeanUtils.copyProperties(goods, this);
    }
}
