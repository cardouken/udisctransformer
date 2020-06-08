package ee.uustal.udisctransformer.configuration;

import com.opencsv.bean.BeanVerifier;
import ee.uustal.udisctransformer.pojo.udisc.UDiscDataHolder;
import org.springframework.stereotype.Component;

@Component
public class BeanNameFieldVerified implements BeanVerifier<UDiscDataHolder> {

    @Override
    public boolean verifyBean(UDiscDataHolder bean) {
        return !"Par".equals(bean.getPlayerName());
    }
}
