package top.forcebing.borrowhome.userend.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import top.forcebing.borrowhome.shopend.model.GoodsDetails;

import javax.persistence.Entity;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/3/3  16:28
 **/
@Data
@Entity
@NoArgsConstructor
public class AgencyGoodsDetails extends GoodsDetails {


    private long agencyId;

    private double newPrice;

    private long agencyGoodsId;

    public AgencyGoodsDetails(GoodsDetails goodsDetails) {
        BeanUtils.copyProperties(goodsDetails, this);
    }
}
